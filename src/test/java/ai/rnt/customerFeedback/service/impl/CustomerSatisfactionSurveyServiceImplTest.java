package ai.rnt.customerFeedback.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.CustomerSatisfactionMasterDto;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionSurveyDto;
import ai.rnt.customerFeedback.dto.McqOptionsDto;
import ai.rnt.customerFeedback.dto.QuestionMasterDto;
import ai.rnt.customerFeedback.entity.Customer;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionMaster;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionSurvey;
import ai.rnt.customerFeedback.entity.McqOptions;
import ai.rnt.customerFeedback.entity.QuestionMaster;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionSurveyRepository;
import ai.rnt.customerFeedback.repository.QuestionRepository;

@ExtendWith(MockitoExtension.class)
class CustomerSatisfactionSurveyServiceImplTest {
	@Mock
	private CustomerSatisfactionSurveyRepository customerSatisfactionSurveyRepository;

	@Mock
	private CustomerSatisfactionMasterRepository customerSatisfactionMasterRepository;

	@Mock
	private QuestionRepository questionRepository;

	@InjectMocks
	private CustomerSatisfactionSurveyServiceImpl customerSatisfactionSurveyService;

	private CustomerSatisfactionSurvey survey;
	private CustomerSatisfactionMaster customerSatisfactionMaster;
	private Customer customer;

	private QuestionMaster questionMaster;
	private McqOptions mcqOption;
	private List<McqOptions> mcqOptions;

	@BeforeEach
	void setUp() {
		survey = new CustomerSatisfactionSurvey();
		survey.setCustomerSatisfactionSurveyId(1);
		customerSatisfactionMaster = new CustomerSatisfactionMaster();
		customerSatisfactionMaster.setCustSatisfactionId(1);
		customer = new Customer();
		customer.setCustomerId(1);
		customerSatisfactionMaster.setAddressId(1);
		;
		survey.setCustomerSatisfactionMaster(customerSatisfactionMaster);
		questionMaster = new QuestionMaster();
		questionMaster.setQuestionId(1);
		mcqOption = new McqOptions();
		mcqOption.setMcqOptionId(1);
		mcqOptions = List.of(mcqOption);
		questionMaster.setMcqOptions(mcqOptions);
		survey.setQuestionMaster(questionMaster);
	}

	@Test
	void saveCustomerSatisfactionSurvey_Failure() {
		List<CustomerSatisfactionSurveyDto> customerSatisfactionSurveyDtoList = new ArrayList<>();
		QuestionMasterDto questionMasterDto = new QuestionMasterDto();
		questionMasterDto.setQuestionId(1);
		questionMasterDto.setExample("nikscd");
		questionMasterDto.setFormType(1);
		questionMasterDto.setIsMcqType(1);
		List<McqOptionsDto> listMcqDto = new ArrayList<>();
		McqOptionsDto mcqDto = new McqOptionsDto();
		mcqDto.setMcqOptionId(1);
		mcqDto.setOption("disagree");
		listMcqDto.add(mcqDto);
		questionMasterDto.setMcqOptionsDtos(listMcqDto);

		CustomerSatisfactionMasterDto customerSatisfactionMasterDto = new CustomerSatisfactionMasterDto();
		customerSatisfactionMasterDto.setCustSatisfactionId(1);
		CustomerSatisfactionSurveyDto customerSatisfactionSurveyDto = new CustomerSatisfactionSurveyDto();
		customerSatisfactionSurveyDto.setCustomerSatisfactionSurveyId(1);
		customerSatisfactionSurveyDto.setAnswer("answer");
		customerSatisfactionSurveyDto.setCustomerSatisfactionMasterDto(customerSatisfactionMasterDto);
		customerSatisfactionSurveyDto.setQuestionMasterDto(questionMasterDto);
		customerSatisfactionSurveyDtoList.add(customerSatisfactionSurveyDto);

		CustomerSatisfactionMaster customerSatisfaction = new CustomerSatisfactionMaster();
		Customer cust = new Customer();
		cust.setCustomerId(1);
		cust.setCompanyName("abc");
		cust.setContactPersonName("contact person name");
		cust.setEmailId("email.com");
		customerSatisfaction.setAddressId(1);
		;
		customerSatisfaction.setCustSatisfactionId(1);
		customerSatisfaction.setFilled(true);
		customerSatisfaction.setFilledDate("30-05-2024");
		when(customerSatisfactionSurveyRepository.findByCustomerSatisfactionMasterCustSatisfactionId(anyInt()))
				.thenReturn(new ArrayList<>()); // Assuming existing surveys
		when(customerSatisfactionMasterRepository.findById(1)).thenReturn(Optional.of(customerSatisfaction));
		questionRepository.findById(anyInt());

		assertThrows(CRMException.class, () -> customerSatisfactionSurveyService
				.saveCustomerSatisfactionSurvey(customerSatisfactionSurveyDtoList));
	}

	@Test
    void getAllCustomerSatisfactionSurvey_Success() {
        when(customerSatisfactionSurveyRepository.findAll()).thenReturn(Collections.singletonList(new CustomerSatisfactionSurvey()));
        ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionSurveyService.getAllCustomerSatisfactionSurvey();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        EnumMap<ApiResponse, Object> resultMap = response.getBody();
        assertEquals(true, resultMap.get(ApiResponse.SUCCESS));
    }

	@Test
    void getAllCustomerSatisfactionSurvey_EmptyList() {
        when(customerSatisfactionSurveyRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<EnumMap<ApiResponse, Object>> response = customerSatisfactionSurveyService.getAllCustomerSatisfactionSurvey();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        EnumMap<ApiResponse, Object> resultMap = response.getBody();
        assertEquals(true, resultMap.get(ApiResponse.SUCCESS));
    }

	@Test
	void testGetCustomerSatisfactionSurveyById() {
		int customerId = 1;
		List<CustomerSatisfactionSurvey> customerFeedbackList = new ArrayList<>();

		when(customerSatisfactionSurveyRepository
				.findByCustomerSatisfactionMasterCustSatisfactionIdOrderByQuestionMasterQuestionIdAsc(customerId))
				.thenReturn(customerFeedbackList);

		assertNotNull(customerSatisfactionSurveyService.getCustomerSatisfactionSurveyById(customerId));
		when(customerSatisfactionSurveyRepository
				.findByCustomerSatisfactionMasterCustSatisfactionIdOrderByQuestionMasterQuestionIdAsc(customerId))
				.thenThrow(NullPointerException.class);
		assertThrows(NullPointerException.class,
				() -> customerSatisfactionSurveyService.getCustomerSatisfactionSurveyById(customerId));

	}

	@Test
	void saveCustomerSatisfactionSurvey_ExistingSurveys() {
		List<CustomerSatisfactionSurveyDto> customerSatisfactionSurveyDtoList = createTestDtoList();
		try {
			customerSatisfactionSurveyService.saveCustomerSatisfactionSurvey(customerSatisfactionSurveyDtoList);
		} catch (CRMException e) {
		}

	}

	@Test
	void saveCustomerSatisfactionSurvey_NoExistingSurveys() {
		List<CustomerSatisfactionSurveyDto> customerSatisfactionSurveyDtoList = createTestDtoList();
		try {
			customerSatisfactionSurveyService.saveCustomerSatisfactionSurvey(customerSatisfactionSurveyDtoList);
		} catch (CRMException e) {
		}

	}

	private List<CustomerSatisfactionSurveyDto> createTestDtoList() {
		List<CustomerSatisfactionSurveyDto> customerSatisfactionSurveyDtoList = new ArrayList<>();
		return customerSatisfactionSurveyDtoList;
	}

}