/**
 * 
 */
package com.atanu.java.springboot.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.atanu.java.springboot.entity.AncillaryDetailsEntity;
import com.atanu.java.springboot.entity.PreferredAncillaryEntity;
import com.atanu.java.springboot.model.AncillaryDetails;
import com.atanu.java.springboot.model.PreferredAncillaryRequest;
import com.atanu.java.springboot.model.PreferredAncillaryResponse;

/**
 * @author ATANU
 *
 */

@Component
public class EntityConverter {
	
	/**
	 * @param prefferedAncillaryEntities
	 * @return PreferredAncillaryResponse
	 */
	public PreferredAncillaryResponse convertPreferredAncillaryEntityToPreferredAncillaryResponse(List<PreferredAncillaryEntity> prefferedAncillaryEntities){
		PreferredAncillaryResponse suggestedAncillary= null;
		if(null != prefferedAncillaryEntities && !prefferedAncillaryEntities.isEmpty()){
			suggestedAncillary = new PreferredAncillaryResponse();
			
			suggestedAncillary.setOriginAirportCode(prefferedAncillaryEntities.get(0).getOriginAirportCode());
			suggestedAncillary.setDestAirportCode(prefferedAncillaryEntities.get(0).getDestAirportCode());
			
			for(PreferredAncillaryEntity prefferedAncillaryEntity : prefferedAncillaryEntities){
				suggestedAncillary
						.getPreferredAncillaries()
						.add(convertAncillaryDetailsEntityToAncillaryDetails(prefferedAncillaryEntity
								.getAncillaryDetails()));
			}
		}
		return suggestedAncillary;
	}
	
	/**
	 * @param ancillaryDetailsEntity
	 * @return AncillaryDetails
	 */
	public AncillaryDetails convertAncillaryDetailsEntityToAncillaryDetails(AncillaryDetailsEntity ancillaryDetailsEntity){
		AncillaryDetails ancillaryDetails = null;
		
		if(null != ancillaryDetailsEntity){
			ancillaryDetails = new AncillaryDetails();
			if(0 != ancillaryDetailsEntity.getAncillaryId()) {
				ancillaryDetails.setAncillaryId(Integer.toString(ancillaryDetailsEntity.getAncillaryId()));
			}
			ancillaryDetails.setAncillaryName(ancillaryDetailsEntity.getAncillaryName());
			ancillaryDetails.setAncillaryDesc(ancillaryDetailsEntity.getAncillaryDesc());
		}
		return ancillaryDetails;
	}
	
	/**
	 * @param ancillaryRequest
	 * @return PreferredAncillaryEntity
	 */
	public PreferredAncillaryEntity convertPreferredAncillaryRequestToPreferredAncillaryEntity(PreferredAncillaryRequest ancillaryRequest){
		PreferredAncillaryEntity prefferedAncillaryEntity= null;
		if(null != ancillaryRequest){
			prefferedAncillaryEntity = new PreferredAncillaryEntity();
			prefferedAncillaryEntity.setId(ancillaryRequest.getOriginAirportCode() + ancillaryRequest.getDestAirportCode() + ancillaryRequest.getAncillaryId());
			prefferedAncillaryEntity.setOriginAirportCode(ancillaryRequest.getOriginAirportCode());
			prefferedAncillaryEntity.setDestAirportCode(ancillaryRequest.getDestAirportCode());
			prefferedAncillaryEntity.setAncillaryCount(ancillaryRequest.getAncillaryCount());
			
			AncillaryDetailsEntity ancillaryDetailsEntity = new AncillaryDetailsEntity();
			ancillaryDetailsEntity.setAncillaryId(ancillaryRequest.getAncillaryId());
			ancillaryDetailsEntity.setAncillaryName(ancillaryRequest.getAncillaryName());
			ancillaryDetailsEntity.setAncillaryDesc(ancillaryRequest.getAncillaryDesc());
			ancillaryDetailsEntity.getPreferredAncillaries().add(prefferedAncillaryEntity);
			prefferedAncillaryEntity.setAncillaryDetails(ancillaryDetailsEntity);
		}
		return prefferedAncillaryEntity;
	}
	
	/**
	 * @param ancillaryRequest
	 * @return AncillaryDetailsEntity
	 */
	public AncillaryDetailsEntity convertPreferredAncillaryRequestToAncillaryDetailsEntity(PreferredAncillaryRequest ancillaryRequest){
		AncillaryDetailsEntity ancillaryDetailsEntity = null;
		if(null != ancillaryRequest){
			
			ancillaryDetailsEntity = new AncillaryDetailsEntity();
			ancillaryDetailsEntity.setAncillaryId(ancillaryRequest.getAncillaryId());
			ancillaryDetailsEntity.setAncillaryName(ancillaryRequest.getAncillaryName());
			ancillaryDetailsEntity.setAncillaryDesc(ancillaryRequest.getAncillaryDesc());
			
			PreferredAncillaryEntity prefferedAncillaryEntity = new PreferredAncillaryEntity();
			prefferedAncillaryEntity.setId(ancillaryRequest.getOriginAirportCode() + ancillaryRequest.getDestAirportCode() + ancillaryRequest.getAncillaryId());
			prefferedAncillaryEntity.setOriginAirportCode(ancillaryRequest.getOriginAirportCode());
			prefferedAncillaryEntity.setDestAirportCode(ancillaryRequest.getDestAirportCode());
			prefferedAncillaryEntity.setAncillaryCount(ancillaryRequest.getAncillaryCount());
			
			prefferedAncillaryEntity.setAncillaryDetails(ancillaryDetailsEntity);
			ancillaryDetailsEntity.getPreferredAncillaries().add(prefferedAncillaryEntity);
		}
		return ancillaryDetailsEntity;
	}
}
