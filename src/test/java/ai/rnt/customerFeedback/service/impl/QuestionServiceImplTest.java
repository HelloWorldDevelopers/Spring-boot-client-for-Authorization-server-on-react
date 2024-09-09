package ai.rnt.customerFeedback.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.entity.Customer;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.entity.QuestionMaster;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.AddressMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerRepository;
import ai.rnt.customerFeedback.repository.ProjectRepository;
import ai.rnt.customerFeedback.repository.QuestionRepository;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

	@Mock
	private QuestionRepository questionRepository;

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private QuestionServiceImpl questionService;

	@Mock
	AddressMasterRepository addressMasterRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllQuestion() {
		List<QuestionMaster> questionList = createSampleQuestions();

		when(questionRepository.findAll()).thenReturn(questionList);

		ResponseEntity<EnumMap<ApiResponse, Object>> response = questionService.getAllQuestion();

		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertTrue((Boolean) response.getBody().get(ApiResponse.SUCCESS));
		assertNotNull(response.getBody().get(ApiResponse.DATA));
	}

	@Test
    void testgetAllQuestion_Exception() {
        when(questionRepository.findAll()).thenReturn(null);
        assertThrows(CRMException.class, () -> {
        	questionService.getAllQuestion();
        });

    }

	@Test
	void testGetAllCustomer() {
		List<Customer> customerList = createSampleCustomers();

		when(customerRepository.findAllActiveCustomers()).thenReturn(customerList);

		ResponseEntity<EnumMap<ApiResponse, Object>> response = questionService.getAllCustomer();

		assertEquals(HttpStatus.FOUND, response.getStatusCode());
		assertTrue((Boolean) response.getBody().get(ApiResponse.SUCCESS));
		assertNotNull(response.getBody().get(ApiResponse.DATA));
	}

	@Test
    void testgetAllCustomer_Exception() {
        when(customerRepository.findAllActiveCustomers()).thenReturn(null);

        assertThrows(CRMException.class, () -> {
        	questionService.getAllCustomer();
        });

    }

	private List<QuestionMaster> createSampleQuestions() {
		List<QuestionMaster> questions = new ArrayList<>();
		return questions;
	}

	private List<Customer> createSampleCustomers() {
		List<Customer> customers = new ArrayList<>();
		return customers;
	}

	@Test
	void testGetProjectListByCustomerId_Success() {
		int customerId = 123;
		List<Project> mockProjects = new ArrayList<>();
		when(projectRepository.findByCustomerCustomerIdAndProjectNameIsNotNullOrderByProjectNameAsc(customerId))
				.thenReturn(mockProjects);
		ResponseEntity<EnumMap<ApiResponse, Object>> response = questionService.getProjectListByCustomerId(customerId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		EnumMap<ApiResponse, Object> resultMap = response.getBody();
		assertEquals(true, resultMap.get(ApiResponse.SUCCESS));
		assertEquals(mockProjects, resultMap.get(ApiResponse.DATA));
	}

	@Test
    void testgetProjectListByCustomerId_Exception() {
        when(projectRepository.findByCustomerCustomerIdAndProjectNameIsNotNullOrderByProjectNameAsc(anyInt())).thenReturn(null);

        assertThrows(CRMException.class, () -> {
        	questionService.getProjectListByCustomerId(anyInt());
        });

    }

	@Test
	void testgetAllQuestionForCustomerSatisfaction() {
		assertNotNull(questionService.getAllQuestionForCustomerSatisfaction(anyInt()));
		when(questionRepository.findByFormType(anyInt())).thenReturn(null);
		assertThrows(CRMException.class, () -> {
			questionService.getAllQuestionForCustomerSatisfaction(anyInt());
		});
	}

	@Test
	void testCustomerByCustomerId() {
		assertNotNull(questionService.customerByCustomerId(anyInt()));
		when(customerRepository.findById(anyInt())).thenReturn(null);
		assertThrows(CRMException.class, () -> {
			questionService.customerByCustomerId(anyInt());
		});
	}

	@Test
	void testgetAllCustomerContactPerson() {
		Exception e = new Exception();
		assertNotNull(questionService.getAllCustomerContactPerson(anyInt()));
		when(addressMasterRepository.findAllContactPersonOfCustomer(anyInt())).thenThrow(new CRMException(e));
		try {
			assertNotNull(questionService.getAllCustomerContactPerson(anyInt()));
		} catch (Exception ex) {

		}
	}

	@Test
	void testgetAllCustomersForSatisfaction() {
		assertNotNull(questionService.getAllCustomersForSatisfaction());
		when(customerRepository.findAllCustomersForCustomerSatifaction()).thenReturn(null);
		assertThrows(CRMException.class, () -> {
			questionService.getAllCustomersForSatisfaction();
		});
	}
}
