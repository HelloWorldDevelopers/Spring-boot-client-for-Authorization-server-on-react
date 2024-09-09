package ai.rnt.customerFeedback.service;

import java.util.EnumMap;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.enums.ApiResponse;

public interface VendorStateGSTCodeService {

	ResponseEntity<EnumMap<ApiResponse, Object>> findStateByGSTCode(Integer id);

	ResponseEntity<EnumMap<ApiResponse, Object>> findAll();

	ResponseEntity<EnumMap<ApiResponse, Object>> findAllStateByGSTCode();
}
