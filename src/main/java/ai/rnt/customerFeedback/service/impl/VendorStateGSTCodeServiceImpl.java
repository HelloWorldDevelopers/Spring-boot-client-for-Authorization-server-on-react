package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.dto_mapper.VendorStateGSTCodeDTOMapper.TO_COUNTRY_MASTER_DTO_LIST;
import static ai.rnt.customerFeedback.dto_mapper.VendorStateGSTCodeDTOMapper.TO_VENDOR_STATE_GST_DTO;
import static ai.rnt.customerFeedback.dto_mapper.VendorStateGSTCodeDTOMapper.TO_VENDOR_STATE_GST_DTO_LIST;

import java.util.EnumMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.entity.CountryMaster;
import ai.rnt.customerFeedback.entity.VendorStateGSTCode;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.CountryMasterRepository;
import ai.rnt.customerFeedback.repository.VendorStateGSTCodeRepository;
import ai.rnt.customerFeedback.service.VendorStateGSTCodeService;

@Service
public class VendorStateGSTCodeServiceImpl implements VendorStateGSTCodeService {

	@Autowired
	VendorStateGSTCodeRepository gstCodeRepository;

	@Autowired
	CountryMasterRepository countryMasterRepository;

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> findStateByGSTCode(Integer id) {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		try {
			VendorStateGSTCode findByStateGstCode = gstCodeRepository.findByStateGstCode(id);
			responseMap.put(ApiResponse.SUCCESS, true);
			responseMap.put(ApiResponse.DATA, TO_VENDOR_STATE_GST_DTO.apply(findByStateGstCode));
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> findAllStateByGSTCode() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		try {
			List<VendorStateGSTCode> findAll = gstCodeRepository.findAll();
			responseMap.put(ApiResponse.SUCCESS, true);
			responseMap.put(ApiResponse.DATA, TO_VENDOR_STATE_GST_DTO_LIST.apply(findAll));
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> findAll() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		try {
			List<CountryMaster> countryMasters = countryMasterRepository
					.findAll(Sort.by(Sort.Direction.ASC, "country"));
			responseMap.put(ApiResponse.SUCCESS, true);
			responseMap.put(ApiResponse.DATA, TO_COUNTRY_MASTER_DTO_LIST.apply(countryMasters));
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

}
