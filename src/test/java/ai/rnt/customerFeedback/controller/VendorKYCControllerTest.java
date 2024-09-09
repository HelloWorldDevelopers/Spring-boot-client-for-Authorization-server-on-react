package ai.rnt.customerFeedback.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentUploadDTO;
import ai.rnt.customerFeedback.dto.VendorKYCDto;
import ai.rnt.customerFeedback.dto.VendorOnboardingMailBoxDTO;
import ai.rnt.customerFeedback.dto.VendorRegistrationDto;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.service.VendorKYCService;
import ai.rnt.customerFeedback.service.VendorStateGSTCodeService;
import ai.rnt.customerFeedback.util.EmailUtil;
import ai.rnt.customerFeedback.util.GeneratePdf1;

@ExtendWith(MockitoExtension.class)
class VendorKYCControllerTest {

	@Mock
	private VendorKYCService vendorKYCService;

	@Mock
	VendorStateGSTCodeService gstCodeService;

	@Mock
	private GeneratePdf1 generatePdf;

	@Mock
	private EmailUtil emailUtil;

	@InjectMocks
	private VendorKYCController vendorKYCController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveKycVendorForm() {
		VendorKYCDto vendorKYCDto = createSampleVendorKYCDto();
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(vendorKYCService.saveVendorKYCForm(vendorKYCDto)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = vendorKYCController.saveKycVendorForm(vendorKYCDto);
		assertEquals(expectedResult, response);
	}

	@Test
	void testSaveKycVendorFormRegistration() {
		VendorRegistrationDto vendorRegistrationDto = createSampleVendorRegistrationDto();
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(vendorKYCService.saveVendorRegistration(vendorRegistrationDto)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = vendorKYCController
				.saveKycVendorFormRegistration(vendorRegistrationDto);
		assertEquals(expectedResult, response);
	}

	@Test
	void testGetVendorKYCForm() {
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(vendorKYCService.findAll()).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = vendorKYCController.getVendorKYCForm();
		assertEquals(expectedResult, response);
	}

	@Test
	void testGetVendorKYCFormById() {
		int id = 123;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(vendorKYCService.findById(id)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = vendorKYCController.getVendorKYCForm(id);
		assertEquals(expectedResult, response);
	}

	@Test
	void testGetVendorKYCFullForm() {
		int id = 123;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(vendorKYCService.getFullForm(id)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = vendorKYCController.getVendorKYCFullForm(id);
		assertEquals(expectedResult, response);
	}

	@Test
	void testUpdateVendorRegistration() {
		VendorRegistrationDto vendorRegistrationDto = createSampleVendorRegistrationDto();
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(vendorKYCService.updateVendorRegistration(vendorRegistrationDto)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = vendorKYCController
				.updateVendorRegistration(vendorRegistrationDto);
		assertEquals(expectedResult, response);
	}

	private VendorKYCDto createSampleVendorKYCDto() {
		return new VendorKYCDto();
	}

	private VendorRegistrationDto createSampleVendorRegistrationDto() {
		return new VendorRegistrationDto();
	}

	private ResponseEntity<EnumMap<ApiResponse, Object>> createSampleResponseEntity() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		responseMap.put(ApiResponse.SUCCESS, true);
		responseMap.put(ApiResponse.DATA, "Sample Data");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	private VendorKYCDto createSampleVendorKYCMainDto() {
		return new VendorKYCDto();
	}

	@Test
	void testGenerattePdf() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		EnumMap<ApiResponse, Object> responseMap1 = new EnumMap<>(ApiResponse.class);
		VendorKYCDto vendorKYCMainDto = new VendorKYCDto();
		responseMap.put(ApiResponse.DATA, Optional.of(vendorKYCMainDto));

		when(vendorKYCService.findById(1)).thenReturn(new ResponseEntity<>(responseMap, HttpStatus.OK));
		vendorKYCController.generatePdf(1);

		responseMap1 = null;
		when(vendorKYCService.findById(1)).thenReturn(new ResponseEntity<>(responseMap1, HttpStatus.OK));
		vendorKYCController.generatePdf(1);

		when(vendorKYCService.findById(anyInt())).thenThrow(NullPointerException.class);
		assertThrows(CRMException.class, () -> vendorKYCController.generatePdf(1));
	}

	@Test
	void testSendEmail() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(emailUtil.sendEmailVedor(anyInt())).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCController.sendEmailVendor(anyInt());
		assertEquals(mockResponse, responseEntity.getBody());
	}

	@Test
	void testuploadsignedDocument() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		VendorDocumentAttachmentUploadDTO vendorDTO = new VendorDocumentAttachmentUploadDTO();
		when(vendorKYCService.uploadDocument(vendorDTO)).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCController
				.uploadsignedDocument(vendorDTO);
		assertEquals(mockResponse, responseEntity.getBody());
	}

	@Test
	void testgetStateByGSTCode() {
		int id = 123;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(gstCodeService.findStateByGSTCode(id)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = vendorKYCController.getStateByGSTCode(id);
		assertEquals(expectedResult, response);
	}

	@Test
	void testgetAllCountry() {
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(gstCodeService.findAll()).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = vendorKYCController.getAllCountry();
		assertEquals(expectedResult, response);
	}

	@Test
	void testgetAllStateWithCode() {
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(gstCodeService.findAllStateByGSTCode()).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = vendorKYCController.getAllStateWithCode();
		assertEquals(expectedResult, response);
	}

	@Test
	void testsaveVendoremail() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		VendorOnboardingMailBoxDTO vendorDTO = new VendorOnboardingMailBoxDTO();
		when(vendorKYCService.saveVendoremail(vendorDTO)).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCController.saveVendoremail(vendorDTO);
		assertEquals(mockResponse, responseEntity.getBody());
	}

	@Test
	void testgetSavedEmail() {
		int id = 123;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(vendorKYCService.getSavedEmail(id)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = vendorKYCController.getSavedEmail(id);
		assertEquals(expectedResult, response);
	}

	@Test
	void testGetPdfFile() throws IOException {
		String id = "123";
		String docId = "456";
		HttpServletResponse response = mock(HttpServletResponse.class);
		vendorKYCController.getPdfFile(response, id, docId);
		verify(vendorKYCService).getPdfFile(eq(response), eq(id), eq(docId));
	}

	@Test
	void testgetrntlogo() throws IOException {
		String imangeName = "rntlogo.svg";
		HttpServletResponse response = mock(HttpServletResponse.class);
		vendorKYCController.getrntlogo(response, imangeName);
		verify(vendorKYCService).getBugImage(eq(imangeName), eq(response));
	}

}
