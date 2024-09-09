package ai.rnt.customerFeedback.service;

import java.util.EnumMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentUploadDTO;
import ai.rnt.customerFeedback.dto.VendorKYCDto;
import ai.rnt.customerFeedback.dto.VendorOnboardingMailBoxDTO;
import ai.rnt.customerFeedback.dto.VendorRegistrationDto;
import ai.rnt.customerFeedback.enums.ApiResponse;

public interface VendorKYCService {
	ResponseEntity<EnumMap<ApiResponse, Object>> saveVendorKYCForm(VendorKYCDto vendorKYCDto);

	ResponseEntity<EnumMap<ApiResponse, Object>> saveVendorRegistration(VendorRegistrationDto vendorRegistrationDto);

	ResponseEntity<EnumMap<ApiResponse, Object>> findAll();

	ResponseEntity<EnumMap<ApiResponse, Object>> findById(Integer id);

	ResponseEntity<EnumMap<ApiResponse, Object>> getFullForm(Integer id);

	ResponseEntity<EnumMap<ApiResponse, Object>> updateVendorRegistration(VendorRegistrationDto vendorRegistrationDto);

	ResponseEntity<EnumMap<ApiResponse, Object>> uploadDocument(
			VendorDocumentAttachmentUploadDTO vendorDocumentAttachmentUploadDTO);

	ResponseEntity<EnumMap<ApiResponse, Object>> saveVendoremail(VendorOnboardingMailBoxDTO vendorOnboardingMailBoxDTO);

	ResponseEntity<EnumMap<ApiResponse, Object>> getSavedEmail(int kycId);

	void getPdfFile(HttpServletResponse response, String id, String docId);

	void getBugImage(String imangeName, HttpServletResponse response);

}
