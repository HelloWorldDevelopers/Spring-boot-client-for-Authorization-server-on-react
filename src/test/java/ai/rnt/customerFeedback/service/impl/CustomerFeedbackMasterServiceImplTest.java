package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.constants.SecurityConstant.CUSTOMERFEEDBACKID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import ai.rnt.customerFeedback.dto.CustomerFeedbackMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerFeedbackMasterDto;
import ai.rnt.customerFeedback.dto.ProjectDto;
import ai.rnt.customerFeedback.dto.VendorKYCNewDTO;
import ai.rnt.customerFeedback.entity.Customer;
import ai.rnt.customerFeedback.entity.CustomerFeedback;
import ai.rnt.customerFeedback.entity.CustomerFeedbackMailBox;
import ai.rnt.customerFeedback.entity.CustomerFeedbackMaster;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.CustomerFeedbackMailBoxRepo;
import ai.rnt.customerFeedback.repository.CustomerFeedbackMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerFeedbackRepository;
import ai.rnt.customerFeedback.repository.CustomerRepository;
import ai.rnt.customerFeedback.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
class CustomerFeedbackMasterServiceImplTest {

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private CustomerFeedbackMasterRepository customerFeedbackMasterRepository;

	@Mock
	private CustomerFeedbackRepository customerFeedbackRepository;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private CustomerFeedbackMailBoxRepo mailBoxRepo;

	@InjectMocks
	private CustomerFeedbackMasterServiceImpl customerFeedbackMasterService;

	@Test
	void testSaveCustomerFeedbackMaster_Success() {
		CustomerFeedbackMasterDto customerFeedbackMasterDto = new CustomerFeedbackMasterDto();
		customerFeedbackMasterDto.setCustFeedbackId(1);
		customerFeedbackMasterDto.setFeedbackBy("asjk");
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(1);
		projectDto.setProjectName("nikiy");
		CustomerDto customer = new CustomerDto();
		customer.setCompanyName("comaj");
		customer.setCustomerId(1);
		projectDto.setCustomer(customer);
		customerFeedbackMasterDto.setProjectDto(projectDto);
		customerFeedbackMasterDto.setRole("admin");
		when(projectRepository.findById(anyInt())).thenReturn(Optional.of(new Project()));
		assertNotNull(customerFeedbackMasterService.saveCustomerFeedbackMaster(customerFeedbackMasterDto));
		when(projectRepository.findById(anyInt())).thenThrow(NullPointerException.class);
		assertThrows(CRMException.class,
				() -> customerFeedbackMasterService.saveCustomerFeedbackMaster(customerFeedbackMasterDto));

	}

	@Test
	void testGetCustomerFeedbackMaster_Success() {
		int mockId = 1;
		CustomerFeedbackMaster mockCustomerFeedbackMaster = new CustomerFeedbackMaster();
		when(customerFeedbackMasterRepository.findById(anyInt())).thenReturn(Optional.of(mockCustomerFeedbackMaster));
		when(customerFeedbackRepository.findByCustomerFeedbackMasterCustFeedbackId(anyInt()))
				.thenReturn(new ArrayList<>());
		ResponseEntity<EnumMap<ApiResponse, Object>> actualResponse = customerFeedbackMasterService
				.getCustomerFeedbackMaster(mockId);
		assertEquals(HttpStatus.FOUND, actualResponse.getStatusCode());
		assertEquals(true, actualResponse.getBody().get(ApiResponse.SUCCESS));
		assertNotNull(actualResponse.getBody().get(ApiResponse.DATA));
		when(customerFeedbackMasterRepository.findById(anyInt())).thenThrow(NullPointerException.class);
		assertThrows(CRMException.class, () -> customerFeedbackMasterService.getCustomerFeedbackMaster(anyInt()));

	}

	@Test
	void testgetAllCustomerFeedbackMaster() {
		List<CustomerFeedbackMaster> customerList = new ArrayList<>();
		CustomerFeedbackMaster custome = new CustomerFeedbackMaster();
		custome.setCustFeedbackId(1);
		custome.setFeedbackBy("iuwd");
		customerList.add(custome);
		when(customerFeedbackMasterRepository.findAll(Sort.by(Sort.Direction.DESC, CUSTOMERFEEDBACKID)))
				.thenReturn(customerList);
		List<CustomerFeedback> list = new ArrayList<>();
		CustomerFeedback customer1 = new CustomerFeedback();
		customer1.setAnswer("ans");
		customer1.setCustomerFeedbackId(1);
		customer1.setCreatedDate(LocalDateTime.now());
		list.add(customer1);
		when(customerFeedbackRepository.findByCustomerFeedbackMasterCustFeedbackId(1)).thenReturn(list);
		assertNotNull(customerFeedbackMasterService.getAllCustomerFeedbackMaster());
		when(customerFeedbackMasterRepository.findAll(Sort.by(Sort.Direction.DESC, CUSTOMERFEEDBACKID)))
				.thenThrow(NullPointerException.class);
		assertThrows(CRMException.class, () -> customerFeedbackMasterService.getAllCustomerFeedbackMaster());

	}

	@Test
	void testDeleteCustomerFeedbackMaster() {
		int id = 1;
		when(customerFeedbackMasterRepository.findById(id)).thenReturn(Optional.of(new CustomerFeedbackMaster()));
		List<CustomerFeedback> list = new ArrayList<>();
		CustomerFeedback customer = new CustomerFeedback();
		customer.setAnswer("ans");
		customer.setCustomerFeedbackId(id);
		list.add(customer);
		when(customerFeedbackRepository.findByCustomerFeedbackMasterCustFeedbackId(id)).thenReturn(list);
		assertNotNull(customerFeedbackMasterService.deleteCustomerFeedbackMaster(id));

		when(customerFeedbackRepository.findByCustomerFeedbackMasterCustFeedbackId(id))
				.thenThrow(NullPointerException.class);
		assertThrows(CRMException.class, () -> customerFeedbackMasterService.deleteCustomerFeedbackMaster(id));
	}

	@Test
	void testUpdateCustomerFeedbackMaster() {
		CustomerFeedbackMasterDto customerFeedbackMasterDto = new CustomerFeedbackMasterDto();
		customerFeedbackMasterDto.setFeedbackBy("abc");
		customerFeedbackMasterDto.setCustFeedbackId(1);
		customerFeedbackMasterDto.setRole("role");
		ProjectDto projectDto = new ProjectDto();
		projectDto.setProjectId(1);
		customerFeedbackMasterDto.setProjectDto(projectDto);
		Customer customer = new Customer();
		customer.setCompanyName("abc");
		customer.setCustomerId(1);
		Project project = new Project();
		project.setProjectId(1);
		project.setProjectName("abc");
		project.setCustomer(customer);

		CustomerFeedbackMaster customerFeedbackMaster = new CustomerFeedbackMaster();
		customerFeedbackMaster.setCustFeedbackId(1);
		customerFeedbackMaster.setFilled(true);
		customerFeedbackMaster.setFeedbackBy("abc");
		customerFeedbackMaster.setProject(project);
		customerFeedbackMaster.setCustFeedbackId(1);
		when(customerFeedbackMasterRepository.findById(1)).thenReturn(Optional.of(customerFeedbackMaster));
		when(projectRepository.findById(1)).thenReturn(Optional.of(project));
		try {
			customerFeedbackMasterService.updateCustomerFeedbackMaster(1, customerFeedbackMasterDto);
		} catch (Exception e) {
		}
	}

	@Test
	void testSavecustomerFeedbackMail_Success() {
		CustomerFeedbackMailBoxDTO feedbackMailBoxDTO = new CustomerFeedbackMailBoxDTO();
		CustomerFeedbackMasterDto feedbackMasterDto = new CustomerFeedbackMasterDto();
		feedbackMasterDto.setCustFeedbackId(1);
		feedbackMailBoxDTO.setCustomerFeedbackMaster(feedbackMasterDto);
		feedbackMailBoxDTO.setMailboxId(1);
		CustomerFeedbackMailBox feedbackMailBox = new CustomerFeedbackMailBox();
		when(mailBoxRepo.findByCustomerFeedback(feedbackMailBoxDTO.getCustomerFeedbackMaster().getCustFeedbackId()))
				.thenReturn(feedbackMailBox);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = customerFeedbackMasterService
				.saveCustomerFeedbackMailBox(feedbackMailBoxDTO);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));

	}

	@Test
	void savegetSavedEmail() {
		CustomerFeedbackMaster customerFeedbackMaster = new CustomerFeedbackMaster();
		customerFeedbackMaster.setCustFeedbackId(1);
		CustomerFeedbackMailBox vendorOnboardingMailBox = new CustomerFeedbackMailBox();
		vendorOnboardingMailBox.setMailboxId(1);
		vendorOnboardingMailBox.setEmailBody("mail body");
		vendorOnboardingMailBox.setCustomerFeedbackMaster(customerFeedbackMaster);
		VendorKYCNewDTO vendorKYCNewDTO = new VendorKYCNewDTO();
		vendorKYCNewDTO.setVendorKycId(1);
		when(mailBoxRepo.findByCustomerFeedback(anyInt())).thenReturn(vendorOnboardingMailBox);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = customerFeedbackMasterService
				.getCustomerFeedbackMail(anyInt());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));

	}

}
