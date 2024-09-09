
package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.constants.SecurityConstant.CUSTOMERSATISFACTIONID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.CustomerDto;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMasterDto;
import ai.rnt.customerFeedback.dto.VendorKYCNewDTO;
import ai.rnt.customerFeedback.entity.AddressMaster;
import ai.rnt.customerFeedback.entity.Customer;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionMailBox;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionMaster;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionSurvey;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.AddressMasterRepository;
//import ai.rnt.customerFeedback.repository.ClientRepository;
import ai.rnt.customerFeedback.repository.CustomerRepository;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionMailBoxRepo;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionSurveyRepository;
import ai.rnt.customerFeedback.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
class CustomerSatisfactionMasterServiceImplTest {

	@Mock
	private CustomerSatisfactionMasterRepository customerSatisfactionMasterRepository;

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private AddressMasterRepository addressMasterRepository;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private CustomerSatisfactionMailBoxRepo customerSatisfactionMailBoxRepo;

	@InjectMocks
	private CustomerSatisfactionMasterServiceImpl customerService;

	@Mock
	private CustomerSatisfactionSurveyRepository customerSatisfactionSurveyRepository;

	@Test
	void testSaveCustomerSatisfactionMaster() {
		customerService.saveCustomerSatisfactionMaster(new CustomerSatisfactionMasterDto());
	}

	@Test
	void testGetCustomerSatisfactionMasterById_Success() {
		CustomerSatisfactionMaster customerSatisfactionMaster = new CustomerSatisfactionMaster();
		customerSatisfactionMaster.setCustSatisfactionId(1);
		customerSatisfactionMaster.setAddressId(1);
		customerSatisfactionMaster.setFilled(true);
		customerSatisfactionMaster.setFilledDate("12-04-2024");
		List<CustomerSatisfactionSurvey> list = new ArrayList<>();
		CustomerSatisfactionSurvey customerSatisfactionSurvey = new CustomerSatisfactionSurvey();
		customerSatisfactionSurvey.setCreatedDate(LocalDateTime.now());
		list.add(customerSatisfactionSurvey);
		try {
			when(customerSatisfactionMasterRepository.findById(anyInt()))
					.thenReturn(Optional.ofNullable(customerSatisfactionMaster));
			when(customerSatisfactionSurveyRepository.findByCustomerSatisfactionMasterCustSatisfactionId(anyInt()))
					.thenReturn((list));
			customerService.getCustomerSatisfactionMasterById(1);
		} catch (Exception e) {
		}
	}

	@Test
    void testGetCustomerSatisfactionMasterById_ResourceNotFound() {
        when(customerSatisfactionMasterRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
        	customerService.getCustomerSatisfactionMasterById(1);
        });
    }

	@Test
    void testGetCustomerSatisfactionMasterById_ExceptionHandling() {
        when(customerSatisfactionMasterRepository.findById(anyInt())).thenThrow(ResourceNotFoundException.class); 
       assertThrows(ResourceNotFoundException.class, () -> {
        	customerService.getCustomerSatisfactionMasterById(1);
        });
    }

	@Test
	void testGetAllCustomerSatisfactionMaster() {
		List<CustomerSatisfactionMaster> customerList = new ArrayList<>();
		CustomerSatisfactionMaster customersatisfaction = new CustomerSatisfactionMaster();
		customersatisfaction.setCustSatisfactionId(1);
		customersatisfaction.setFilled(any());
		customersatisfaction.setFilledDate("22-03-2024");
		customersatisfaction.setCreatedDate(LocalDateTime.now());
		customersatisfaction.setAddressId(1);
		customerList.add(customersatisfaction);

		List<CustomerSatisfactionDTO> customerSatisfactionDTOlist = new ArrayList<>();
		CustomerSatisfactionDTO customerSatisfactionDTO = new CustomerSatisfactionDTO();
		customerSatisfactionDTO.setAddressId(1);
		customerSatisfactionDTO.setCompanyName("dshd");
		customerSatisfactionDTO.setContactPersonEmail("email.com");
		customerSatisfactionDTO.setContactPersonNo("8877654578");
		customerSatisfactionDTO.setCustomerId(1);
		customerSatisfactionDTOlist.add(customerSatisfactionDTO);

		AddressMaster addressMaster = new AddressMaster();
		addressMaster.setAddressId(1);
		addressMaster.setContactPersonEmail("n.raut@rnt.ai");
		addressMaster.setContactPersonName("nedc edc");
		addressMaster.setContactPersonNo("8977654567");
		Customer customer = new Customer();
		addressMaster.setCustomer(customer);

		when(customerSatisfactionMasterRepository.findAll(Sort.by(Sort.Direction.DESC, CUSTOMERSATISFACTIONID)))
				.thenReturn(customerList);
		List<CustomerSatisfactionSurvey> list = new ArrayList<>();
		CustomerSatisfactionSurvey customerSatisfactionSurvey = new CustomerSatisfactionSurvey();
		customerSatisfactionSurvey.setAnswer("answer");
		customerSatisfactionSurvey.setCustomerSatisfactionSurveyId(1);
		customerSatisfactionSurvey.setCreatedDate(LocalDateTime.now());
		list.add(customerSatisfactionSurvey);

		when(addressMasterRepository.findById(1)).thenReturn(Optional.of(addressMaster));
		when(customerSatisfactionSurveyRepository.findByCustomerSatisfactionMasterCustSatisfactionId(1))
				.thenReturn(list);

		assertNotNull(customerService.getAllCustomerSatisfactionMaster());
		when(customerSatisfactionMasterRepository.findAll(Sort.by(Sort.Direction.DESC, CUSTOMERSATISFACTIONID)))
				.thenThrow(NullPointerException.class);
		assertThrows(CRMException.class, () -> customerService.getAllCustomerSatisfactionMaster());
	}

	@Test
	void testDeleteCustomerSatisfactionMaster() {
		Integer customerId = 1;
		CustomerSatisfactionMaster expectedEntity = createSampleEntity();

		when(customerSatisfactionMasterRepository.findById(customerId)).thenReturn(Optional.of(expectedEntity));

		ResponseEntity<EnumMap<ApiResponse, Object>> response = customerService
				.deleteCustomerSatisfactionMaster(customerId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue((Boolean) response.getBody().get(ApiResponse.SUCCESS));
		assertEquals("Deleted Successfully", response.getBody().get(ApiResponse.MESSAGE));
	}

	private CustomerSatisfactionMaster createSampleEntity() {
		return new CustomerSatisfactionMaster();
	}

	@Test
	void testUpdateCustomerSatisfactionMaster() {
		int id = 1;
		CustomerSatisfactionMasterDto employeePerformenceDTO = new CustomerSatisfactionMasterDto();
		employeePerformenceDTO.setCustSatisfactionId(id);
		// employeePerformenceDTO.setFeedbackBy("abc");;
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(1);
//		ProjectDto projectDTO = new ProjectDto();
//		projectDTO.setProjectId(id);
		employeePerformenceDTO.setAddressId(1);
		CustomerSatisfactionMaster employeePerformenceMaster = new CustomerSatisfactionMaster();
		employeePerformenceMaster.setCustSatisfactionId(id);
		;

		when(customerSatisfactionMasterRepository.findById(id)).thenReturn(Optional.of(employeePerformenceMaster));
		// when(addressMasterRepository.findById(1)).thenReturn(Optional.ofNullable(new
		// AddressMaster()));

		assertNotNull(customerService.updateCustomerSatisfactionMaster(id, employeePerformenceDTO));
		// assertThrows(CRMException.class, () ->
		// customerService.updateCustomerSatisfactionMaster(id,
		// employeePerformenceDTO));

	}

	@Test
	void testgetAddressMasterById() {
		try {
			when(addressMasterRepository.findById(anyInt())).thenReturn(Optional.ofNullable(new AddressMaster()));
			// when(customerSatisfactionSurveyRepository.findByCustomerSatisfactionMasterCustSatisfactionId(anyInt())).thenReturn((list));
			customerService.getAddressMasterById(1);
		} catch (Exception e) {
		}
	}

	@Test
	void saveCustomerSatisfactionMailBoxTest() {
		CustomerSatisfactionMailBoxDTO satisfactionMailBoxDTO = new CustomerSatisfactionMailBoxDTO();
		CustomerSatisfactionMasterDto customerSatisfactionMasterDto = new CustomerSatisfactionMasterDto();
		customerSatisfactionMasterDto.setCustSatisfactionId(1);
		satisfactionMailBoxDTO.setCustomerSatisfactionMaster(customerSatisfactionMasterDto);
		satisfactionMailBoxDTO.setMailboxId(1);
		CustomerSatisfactionMailBox feedbackMailBox = new CustomerSatisfactionMailBox();
		when(customerSatisfactionMailBoxRepo.findByCustomerSatisfaction(
				satisfactionMailBoxDTO.getCustomerSatisfactionMaster().getCustSatisfactionId()))
				.thenReturn(feedbackMailBox);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = customerService
				.saveCustomerSatisfactionMailBox(satisfactionMailBoxDTO);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));

	}

	@Test
	void saveCustomerSatisfactionMailBoxTest1() {
		CustomerSatisfactionMailBoxDTO satisfactionMailBoxDTO = new CustomerSatisfactionMailBoxDTO();
		CustomerSatisfactionMasterDto customerSatisfactionMasterDto = new CustomerSatisfactionMasterDto();
		customerSatisfactionMasterDto.setCustSatisfactionId(1);
		satisfactionMailBoxDTO.setCustomerSatisfactionMaster(customerSatisfactionMasterDto);
		satisfactionMailBoxDTO.setMailboxId(1);
		when(customerSatisfactionMailBoxRepo.findByCustomerSatisfaction(
				satisfactionMailBoxDTO.getCustomerSatisfactionMaster().getCustSatisfactionId())).thenReturn(null);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = customerService
				.saveCustomerSatisfactionMailBox(satisfactionMailBoxDTO);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));

	}

	@Test
	void savegetSavedEmail() {
		CustomerSatisfactionMaster customerFeedbackMaster = new CustomerSatisfactionMaster();
		customerFeedbackMaster.setCustSatisfactionId(1);
		CustomerSatisfactionMailBox vendorOnboardingMailBox = new CustomerSatisfactionMailBox();
		vendorOnboardingMailBox.setMailboxId(1);
		vendorOnboardingMailBox.setEmailBody("mail body");
		vendorOnboardingMailBox.setCustomerSatisfactionMaster(customerFeedbackMaster);
		VendorKYCNewDTO vendorKYCNewDTO = new VendorKYCNewDTO();
		vendorKYCNewDTO.setVendorKycId(1);
		when(customerSatisfactionMailBoxRepo.findByCustomerSatisfaction(anyInt())).thenReturn(vendorOnboardingMailBox);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = customerService
				.getCustomerSatisfactionMail(anyInt());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));

	}

//	@Test
//	void testgetPseudoNameById() {
//		try {
//			when(clientRepository.getCompanyPseudoName("name")).thenReturn("company alias");
//			//when(customerSatisfactionSurveyRepository.findByCustomerSatisfactionMasterCustSatisfactionId(anyInt())).thenReturn((list));
//			customerService.getPseudoName("name");
//		}catch (Exception e) {
//		}
//	}

}
