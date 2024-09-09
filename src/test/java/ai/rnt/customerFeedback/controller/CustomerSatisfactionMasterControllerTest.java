package ai.rnt.customerFeedback.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.EnumMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.CustomerSatisfactionMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMasterDto;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.CustomerSatisfactionMasterService;
import ai.rnt.customerFeedback.util.EmailUtil;

@ExtendWith(MockitoExtension.class)
class CustomerSatisfactionMasterControllerTest {

	@Mock
	private CustomerSatisfactionMasterService customerSatisfactionMasterService;

	@Mock
	EmailUtil emailUtil;

	@InjectMocks
	private CustomerSatisfactionMasterController customerSatisfactionMasterController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveCustomerSatisfactionMaster() {
		CustomerSatisfactionMasterDto customerSatisfactionMasterDto = createSampleCustomerSatisfactionMasterDto();
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(customerSatisfactionMasterService.saveCustomerSatisfactionMaster(customerSatisfactionMasterDto))
				.thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionMasterController
				.saveCustomerSatisfactionMaster(customerSatisfactionMasterDto);
		assertEquals(expectedResult, response);
	}

	@Test
	void testGetCustomerSatisfactionMasterById() {
		Integer id = 1;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(customerSatisfactionMasterService.getCustomerSatisfactionMasterById(id)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionMasterController
				.getCustomerSatisfactionMasterById(id);
		assertEquals(expectedResult, response);
	}

	@Test
	void testgetAddressMasterById() {
		Integer id = 1;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(customerSatisfactionMasterService.getAddressMasterById(id)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionMasterController
				.getAddressMasterById(id);
		assertEquals(expectedResult, response);
	}

	@Test
	void testGetAllCustomerSatisfactionMaster() {
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(customerSatisfactionMasterService.getAllCustomerSatisfactionMaster()).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionMasterController
				.getAllCustomerSatisfactionMaster();
		assertEquals(expectedResult, response);
	}

	@Test
	void testUpdateCustomerSatisfactionMaster() {
		Integer id = 1;
		CustomerSatisfactionMasterDto customerSatisfactionMasterDto = createSampleCustomerSatisfactionMasterDto();
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(customerSatisfactionMasterService.updateCustomerSatisfactionMaster(id, customerSatisfactionMasterDto))
				.thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionMasterController
				.updateCustomerSatisfactionMaster(id, customerSatisfactionMasterDto);
		assertEquals(expectedResult, response);
	}

	@Test
	void testDeleteCustomerSatisfactionMaster() {
		Integer id = 1;
		ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();
		when(customerSatisfactionMasterService.deleteCustomerSatisfactionMaster(id)).thenReturn(expectedResult);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionMasterController
				.deleteCustomerSatisfactionMaster(id);
		assertEquals(expectedResult, response);
	}

	private CustomerSatisfactionMasterDto createSampleCustomerSatisfactionMasterDto() {
		return new CustomerSatisfactionMasterDto();
	}

	private ResponseEntity<EnumMap<ApiResponse, Object>> createSampleResponseEntity() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		responseMap.put(ApiResponse.SUCCESS, true);
		responseMap.put(ApiResponse.DATA, "Sample Data");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@Test
	void testSendEmail() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(emailUtil.sendEmail(anyInt())).thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = customerSatisfactionMasterController
				.sendEmail(anyInt());
		assertEquals(mockResponse, responseEntity.getBody());

	}

	@Test
	void testsaveCustomerSatisfactionMailBox() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		CustomerSatisfactionMailBoxDTO vendorDTO = new CustomerSatisfactionMailBoxDTO();
		when(customerSatisfactionMasterService.saveCustomerSatisfactionMailBox(vendorDTO))
				.thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = customerSatisfactionMasterController
				.saveCustomerSatisfactionMailBox(vendorDTO);
		assertEquals(mockResponse, responseEntity.getBody());
	}

	@Test
	void testgetCustomerSatisfactionMail() {
		EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		when(customerSatisfactionMasterService.getCustomerSatisfactionMail(anyInt()))
				.thenReturn(ResponseEntity.ok(mockResponse));
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = customerSatisfactionMasterController
				.getCustomerSatisfactionMail(anyInt());
		assertEquals(mockResponse, responseEntity.getBody());

	}

}
