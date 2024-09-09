package ai.rnt.customerFeedback.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import ai.rnt.customerFeedback.dto.CustomerFeedbackMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerFeedbackMasterDto;
import ai.rnt.customerFeedback.dto.CustomerListDto;
import ai.rnt.customerFeedback.dto.EmployeePerformenceMailBoxDTO;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.service.CustomerFeedbackMasterService;
import ai.rnt.customerFeedback.util.EmailUtil;
@ExtendWith(MockitoExtension.class)
class CustomerFeedbackMasterControllerTest {

	@Mock
    private CustomerFeedbackMasterService customerFeedbackMasterService;

	@Mock
	EmailUtil emailUtil;
	
    @InjectMocks
    private CustomerFeedbackMasterController customerFeedbackMasterController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testSaveCustomerFeedbackMaster() {
        CustomerFeedbackMasterDto customerFeedbackMasterDto = createSampleCustomerFeedbackMasterDto();
        ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

        when(customerFeedbackMasterService.saveCustomerFeedbackMaster(customerFeedbackMasterDto)).thenReturn(expectedResult);

        ResponseEntity<EnumMap<ApiResponse, Object>> response = customerFeedbackMasterController.saveCustomerFeedbackMaster(customerFeedbackMasterDto);

        assertEquals(expectedResult, response);
    }

    @Test
     void testGetCustomerFeedbackMaster() {
        int id = 1;
        ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

        when(customerFeedbackMasterService.getCustomerFeedbackMaster(id)).thenReturn(expectedResult);

        ResponseEntity<EnumMap<ApiResponse, Object>> response = customerFeedbackMasterController.getCustomerFeedbackMaster(id);

        assertEquals(expectedResult, response);
    }

    @Test
     void testGetAllCustomerFeedbackMaster() {
        ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

        when(customerFeedbackMasterService.getAllCustomerFeedbackMaster()).thenReturn(expectedResult);

        ResponseEntity<EnumMap<ApiResponse, Object>> response = customerFeedbackMasterController.getAllCustomerFeedbackMaster();

        assertEquals(expectedResult, response);
    }

    @Test
     void testUpdateCustomerFeedbackMaster() {
        int id = 1;
        CustomerFeedbackMasterDto customerListDto = new CustomerFeedbackMasterDto();
        ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

        when(customerFeedbackMasterService.updateCustomerFeedbackMaster(id, customerListDto)).thenReturn(expectedResult);

        ResponseEntity<EnumMap<ApiResponse, Object>> response = customerFeedbackMasterController.updateCustomerFeedbackMaster(id, customerListDto);

        assertEquals(expectedResult, response);
    }

    @Test
     void testDeleteCustomerFeedbackMaster() {
        int id = 1;
        ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

        when(customerFeedbackMasterService.deleteCustomerFeedbackMaster(id)).thenReturn(expectedResult);

        ResponseEntity<EnumMap<ApiResponse, Object>> response = customerFeedbackMasterController.deleteCustomerFeedbackMaster(id);

        assertEquals(expectedResult, response);
    }

    private CustomerFeedbackMasterDto createSampleCustomerFeedbackMasterDto() {
        return new CustomerFeedbackMasterDto();
    }

    private CustomerListDto createSampleCustomerListDto() {
        return new CustomerListDto();
    }

    private ResponseEntity<EnumMap<ApiResponse, Object>> createSampleResponseEntity() {
        EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
        responseMap.put(ApiResponse.SUCCESS, true);
        responseMap.put(ApiResponse.DATA, "Sample Data");
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
    
    @Test
    void testSendMailToManager() {
       int id = 1;
       ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

       when(emailUtil.sendEmailProjectEndFeedback(id)).thenReturn(expectedResult);

       ResponseEntity<EnumMap<ApiResponse, Object>> response = customerFeedbackMasterController.sendMailToManager(id);
       assertEquals(expectedResult, response); 
    } 
    
    @Test
	 void testsaveEmployeePerformanceMail() {
		 EnumMap<ApiResponse, Object> mockResponse = new EnumMap<>(ApiResponse.class);
		 CustomerFeedbackMailBoxDTO vendorDTO = new CustomerFeedbackMailBoxDTO();
		 when(customerFeedbackMasterService.saveCustomerFeedbackMailBox(vendorDTO)).thenReturn(ResponseEntity.ok(mockResponse));
		 ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = customerFeedbackMasterController.saveCustomerFeedbackMasterMail(vendorDTO);
		 assertEquals(mockResponse, responseEntity.getBody());
	 }
    
    @Test
    void testgetCustomerFeedbackMail() {
       int id = 1;
       ResponseEntity<EnumMap<ApiResponse, Object>> expectedResult = createSampleResponseEntity();

       when(customerFeedbackMasterService.getCustomerFeedbackMail(id)).thenReturn(expectedResult);

       ResponseEntity<EnumMap<ApiResponse, Object>> response = customerFeedbackMasterController.getCustomerFeedbackMail(id);
       assertEquals(expectedResult, response); 
    } 
       

}
