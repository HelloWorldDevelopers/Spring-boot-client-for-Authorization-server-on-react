package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.dto_mapper.AddressMasterModelMapper.TO_ADDRESS_MASTER_DTO_LIST;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_CUSTOMER;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_CUSTOMER_LIST;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_PROJECT_LIST;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_QUESTIONS;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.entity.AddressMaster;
import ai.rnt.customerFeedback.entity.Customer;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.AddressMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerRepository;
import ai.rnt.customerFeedback.repository.ProjectRepository;
import ai.rnt.customerFeedback.repository.QuestionRepository;
import ai.rnt.customerFeedback.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	AddressMasterRepository addressMasterRepository;

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllQuestion() {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {

			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_QUESTIONS.apply(questionRepository.findAll()));
			return new ResponseEntity<>(resultMap, HttpStatus.FOUND);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomer() {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			List<Customer> activeCustomers = customerRepository.findAllActiveCustomers();
			activeCustomers.sort(Comparator.comparing(Customer::getCompanyName));
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_CUSTOMER_LIST.apply(activeCustomers));
			return new ResponseEntity<>(resultMap, HttpStatus.FOUND);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getProjectListByCustomerId(int id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA,
					TO_PROJECT_LIST.apply(projectRepository
							.findByCustomerCustomerIdAndProjectNameIsNotNullOrderByProjectNameAsc(id).stream()
							.filter(project -> !project.getProjectName().isEmpty()).collect(Collectors.toList())));
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllQuestionForCustomerSatisfaction(int formType) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_QUESTIONS.apply(questionRepository.findByFormType(formType)));
			return new ResponseEntity<>(resultMap, HttpStatus.FOUND);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> customerByCustomerId(int customerId) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);

		try {
			Customer findById = customerRepository.findById(customerId).orElse(null);

			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_CUSTOMER.apply(findById));

			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerContactPerson(int customerId) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			List<AddressMaster> allContactPersonOfCustomer = addressMasterRepository
					.findAllContactPersonOfCustomer(customerId);
			if (!allContactPersonOfCustomer.isEmpty()) {
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.DATA, TO_ADDRESS_MASTER_DTO_LIST.apply(allContactPersonOfCustomer));
			} else {
				resultMap.put(ApiResponse.SUCCESS, false);
				resultMap.put(ApiResponse.MESSAGE, "Company Don't Have SPOC.");
			}
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomersForSatisfaction() {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			List<Customer> activeCustomers = customerRepository.findAllCustomersForCustomerSatifaction();
			activeCustomers.sort(Comparator.comparing(Customer::getCompanyName));
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_CUSTOMER_LIST.apply(activeCustomers));
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}
}
