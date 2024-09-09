package ai.rnt.customerFeedback.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import ai.rnt.customerFeedback.dto.CustomerDto;
import ai.rnt.customerFeedback.dto.CustomerFeedbackDto;
import ai.rnt.customerFeedback.dto.CustomerFeedbackMasterDto;
import ai.rnt.customerFeedback.dto.McqOptionsDto;
import ai.rnt.customerFeedback.dto.ProjectDto;
import ai.rnt.customerFeedback.dto.QuestionMasterDto;
import ai.rnt.customerFeedback.entity.CustomerFeedback;
import ai.rnt.customerFeedback.entity.CustomerFeedbackMaster;
import ai.rnt.customerFeedback.entity.QuestionMaster;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.CustomerFeedbackMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerFeedbackRepository;
import ai.rnt.customerFeedback.repository.QuestionRepository;

@ExtendWith(MockitoExtension.class)
class CustomerFeedbackServiceImplTest {

	@Mock
	private CustomerFeedbackRepository customerFeedbackRepository;

	@Mock
	private CustomerFeedbackMasterRepository customerFeedbackMasterRepository;

	@Mock
	private QuestionRepository questionRepository;

	@InjectMocks
	private CustomerFeedbackServiceImpl customerFeedbackService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveCustomerFeedback() {
		List<CustomerFeedbackDto> customerFeedbackDtos = new ArrayList<>();

		CustomerDto customer = new CustomerDto();
		customer.setCompanyName("kjhiu");
		customer.setCustomerId(1);

		ProjectDto projectDTO = new ProjectDto();
		projectDTO.setCustomer(customer);
		projectDTO.setProjectId(1);
		projectDTO.setProjectName("kudgc");

		CustomerFeedbackMasterDto customerFeedbackMasterDto = new CustomerFeedbackMasterDto();
		customerFeedbackMasterDto.setCustFeedbackId(1);
		customerFeedbackMasterDto.setFeedbackBy("uyu");
		customerFeedbackMasterDto.setProjectDto(projectDTO);
		customerFeedbackMasterDto.setRole("jfytd");

		QuestionMasterDto questionMasterDto = new QuestionMasterDto();
		questionMasterDto.setExample("iugcgw");
		questionMasterDto.setFormType(1);
		questionMasterDto.setIsMcqType(1);
		questionMasterDto.setQuestion("iuwciyiy");
		questionMasterDto.setQuestionId(1);

		List<McqOptionsDto> mcqList = new ArrayList<>();
		McqOptionsDto mcqOptionsDto = new McqOptionsDto();
		mcqOptionsDto.setMcqOptionId(1);
		mcqOptionsDto.setOption("four");
		mcqList.add(mcqOptionsDto);
		questionMasterDto.setMcqOptionsDtos(mcqList);

		CustomerFeedbackDto customerFeedbackDto = new CustomerFeedbackDto();
		customerFeedbackDto.setAnswer("ansk");
		customerFeedbackDto.setCustomerFeedbackId(1);
		customerFeedbackDto.setCustomerFeedbackMasterDto(customerFeedbackMasterDto);
		customerFeedbackDto.setQuestionMasterDto(questionMasterDto);

		customerFeedbackDtos.add(customerFeedbackDto);
		List<CustomerFeedback> st = new ArrayList<>();
		when(customerFeedbackRepository.findByCustomerFeedbackMasterCustFeedbackId(anyInt())).thenReturn(st);
		when(customerFeedbackMasterRepository.findById(anyInt())).thenReturn(Optional.of(new CustomerFeedbackMaster()));
		when(questionRepository.findById(anyInt())).thenReturn(Optional.of(new QuestionMaster()));
		assertNotNull(customerFeedbackService.saveCustomerFeedback(customerFeedbackDtos));
	}

	@Test
	void testGetByCustomerFeedback() {
		int customerId = 1;
		List<CustomerFeedback> customerFeedbackList = new ArrayList<>();

		when(customerFeedbackRepository
				.findByCustomerFeedbackMasterCustFeedbackIdOrderByQuestionMasterQuestionIdAsc(customerId))
				.thenReturn(customerFeedbackList);

		assertNotNull(customerFeedbackService.getByCustomerFeedback(customerId));
		when(customerFeedbackRepository
				.findByCustomerFeedbackMasterCustFeedbackIdOrderByQuestionMasterQuestionIdAsc(customerId))
				.thenThrow(NullPointerException.class);
		assertThrows(CRMException.class, () -> customerFeedbackService.getByCustomerFeedback(customerId));

	}

	@Test
	void testgetAllCustomerFeedback() {
		List<CustomerFeedback> customerList = new ArrayList<>();
		when(customerFeedbackRepository.findAll()).thenReturn(customerList);
		assertNotNull(customerFeedbackService.getAllCustomerFeedback());
		when(customerFeedbackRepository.findAll()).thenThrow(NullPointerException.class);
		assertThrows(CRMException.class, () -> customerFeedbackService.getAllCustomerFeedback());
	}

}
