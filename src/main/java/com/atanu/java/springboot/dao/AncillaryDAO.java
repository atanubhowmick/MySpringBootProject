/**
 * 
 */
package com.atanu.java.springboot.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atanu.java.springboot.constant.Constants;
import com.atanu.java.springboot.entity.AncillaryDetailsEntity;
import com.atanu.java.springboot.entity.PreferredAncillaryEntity;
import com.atanu.java.springboot.exception.DataSvcException;

/**
 * @author ATANU
 *
 */

@Transactional
@Repository
public class AncillaryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @param originAirportCode
	 * @param destAirportCode
	 * @return List
	 * @throws DataSvcException 
	 */
	@SuppressWarnings("unchecked")
	public List<PreferredAncillaryEntity> getPreferredAncillariesByAirports(String originAirportCode, String destAirportCode) throws DataSvcException{
		List<PreferredAncillaryEntity> preferredAncilaries = null;
		try {
		Session session = sessionFactory.getCurrentSession();
		preferredAncilaries = (List<PreferredAncillaryEntity>) session.createQuery("FROM PreferredAncillaryEntity WHERE originAirportCode =:originAirportCode AND destAirportCode =:destAirportCode").setParameter("originAirportCode", originAirportCode)
				.setParameter("destAirportCode", destAirportCode).list();
		} catch (Exception e) {
			throw new DataSvcException(Constants.ERROR_CODE_2001, Constants.ERROR_MSG_2001, e);
		}
		
		return preferredAncilaries;
	}
	
	/**
	 * @param count
	 * @param id
	 * @return int
	 */
	public void updatePreferredAncillary(PreferredAncillaryEntity ancillaryEntity){
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(ancillaryEntity);
		session.flush();
		session.clear();
	}
	
	public void updateAncillaryDetails(AncillaryDetailsEntity ancillaryEntity){
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(ancillaryEntity);
		session.flush();
		session.clear();
	}
	
	/**
	 * @param ancillaryId
	 * @return AncillaryDetailsEntity
	 */
	public AncillaryDetailsEntity getAncillaryDetailsById(int ancillaryId){
		Session session = sessionFactory.getCurrentSession();
		return (AncillaryDetailsEntity) session.load(AncillaryDetailsEntity.class, ancillaryId);
	}
	
	/**
	 * @param ancillaryId
	 * @return AncillaryDetailsEntity
	 */
	@SuppressWarnings("unchecked")
	public AncillaryDetailsEntity getAncillaryDetailsByName(String ancillaryName){
		Session session = sessionFactory.getCurrentSession();
		AncillaryDetailsEntity ancillaryDetailsEntity = null;
		List<AncillaryDetailsEntity> list =(List<AncillaryDetailsEntity>) session.createQuery("FROM AncillaryDetailsEntity WHERE ancillaryName =:ancillaryName").setParameter("ancillaryName", ancillaryName).list();
		if(list != null && !list.isEmpty()){
			ancillaryDetailsEntity = list.get(0);
		}
		return ancillaryDetailsEntity;
	}	
	
	/**
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<AncillaryDetailsEntity> getAllAncillary() {
		Session session = sessionFactory.getCurrentSession();
		return (List<AncillaryDetailsEntity>) session.createQuery("FROM AncillaryDetailsEntity").list();
	}
}
