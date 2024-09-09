/**
 * 
 */
package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.constants.SecurityConstant.CUSTOMERFEEDBACKID;
import static ai.rnt.customerFeedback.dto_mapper.CustomerFeedbackMasterDtoMapper.CUSTOMER_FEEDBACK_MAILBOX;
import static ai.rnt.customerFeedback.dto_mapper.CustomerFeedbackMasterDtoMapper.CUSTOMER_FEEDBACK_MAILBOX_DTO;
import static ai.rnt.customerFeedback.dto_mapper.CustomerFeedbackMasterDtoMapper.TO_CM_NEW_DTO;
import static ai.rnt.customerFeedback.dto_mapper.CustomerFeedbackMasterDtoMapper.TO_CUSTOMERS_DTO;
import static ai.rnt.customerFeedback.dto_mapper.CustomerFeedbackMasterDtoMapper.TO_CUSTOMER_FEEDBACK_MASTER;
import static ai.rnt.customerFeedback.dto_mapper.CustomerFeedbackMasterDtoMapper.TO_CUSTOMER_LIST_DTO;
import static ai.rnt.customerFeedback.dto_mapper.QuestionDtoMapper.TO_PROJECT_DTO;

import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.dto.CustomerFeedbackMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerFeedbackMasterDto;
import ai.rnt.customerFeedback.dto.CustomerListDto;
import ai.rnt.customerFeedback.entity.CustomerFeedback;
import ai.rnt.customerFeedback.entity.CustomerFeedbackMailBox;
import ai.rnt.customerFeedback.entity.CustomerFeedbackMaster;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.CustomerFeedbackMailBoxRepo;
import ai.rnt.customerFeedback.repository.CustomerFeedbackMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerFeedbackRepository;
import ai.rnt.customerFeedback.repository.ProjectRepository;
import ai.rnt.customerFeedback.service.CustomerFeedbackMasterService;

/**
 * @author Abhishek Ingle
 * @Date Jan 9, 2024
 * @Version 1.0
 */
@Service
public class CustomerFeedbackMasterServiceImpl implements CustomerFeedbackMasterService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private CustomerFeedbackMasterRepository customerFeedbackMasterRepository;

	@Autowired
	private CustomerFeedbackRepository customerFeedbackRepository;

	@Autowired
	private CustomerFeedbackMailBoxRepo customerFeedbackMailBoxRepo;

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerFeedbackMaster(
			CustomerFeedbackMasterDto customerFeedbackMasterDto) {

		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			customerFeedbackMasterDto
					.setProjectDto(TO_PROJECT_DTO
							.apply(projectRepository.findById(customerFeedbackMasterDto.getProjectDto().getProjectId())
									.orElseThrow(ResourceNotFoundException::new))
							.orElseThrow(ResourceNotFoundException::new));
			CustomerFeedbackMaster customerFeedbackMaster = TO_CUSTOMER_FEEDBACK_MASTER.apply(customerFeedbackMasterDto)
					.orElseThrow(ResourceNotFoundException::new);
			CustomerFeedbackMaster savedCustomerFeedbackMaster = customerFeedbackMasterRepository
					.save(customerFeedbackMaster);

			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_CM_NEW_DTO.apply(savedCustomerFeedbackMaster));
			return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerFeedbackMaster(int id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			CustomerFeedbackMaster customerFeedbackMaster = customerFeedbackMasterRepository.findById(id)
					.orElseThrow(ResourceNotFoundException::new);
			List<CustomerFeedback> customerFeedbacks = customerFeedbackRepository
					.findByCustomerFeedbackMasterCustFeedbackId(id);
			if (!customerFeedbacks.isEmpty()) {
				CustomerFeedback firstfeedback = customerFeedbacks.get(0);
				String formattedDate = firstfeedback.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

				boolean isFilled = !customerFeedbacks.isEmpty();
				customerFeedbackMaster.setFilled(isFilled);
				customerFeedbackMaster.setFilledDate(formattedDate);
			}
			TO_CM_NEW_DTO.apply(customerFeedbackMaster);
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_CM_NEW_DTO.apply(customerFeedbackMaster));
			return new ResponseEntity<>(resultMap, HttpStatus.FOUND);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerFeedbackMaster() {

		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			List<CustomerFeedbackMaster> findAll = customerFeedbackMasterRepository
					.findAll(Sort.by(Sort.Direction.DESC, CUSTOMERFEEDBACKID));
			findAll.stream().forEach(e -> {
				List<CustomerFeedback> findById2 = customerFeedbackRepository
						.findByCustomerFeedbackMasterCustFeedbackId(e.getCustFeedbackId());
				if (!findById2.isEmpty()) {
					String formattedDate = findById2.get(0).getCreatedDate()
							.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

					e.setFilled(true);
					e.setSortStatus(1);
					e.setFilledDate(formattedDate);
				} else {
					e.setFilledDate(null); // No surveys, so filledDate should be null
					e.setFilled(false);
					e.setSortStatus(0);
				}
				e.setIsEmailSend(e.getCustomerFeedbackMailBox() != null);

			});
			List<CustomerListDto> list = TO_CUSTOMER_LIST_DTO.apply(findAll);
			list.forEach(customer -> {
				if (customer.getRole() == null || customer.getRole().isEmpty()) {
					customer.setRole("-");
				}
			});
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, list);
			return new ResponseEntity<>(resultMap, HttpStatus.FOUND);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> updateCustomerFeedbackMaster(int id,
			CustomerFeedbackMasterDto customerFeedbackMasterDto) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			CustomerFeedbackMaster customerFeedbackMaster = customerFeedbackMasterRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("CustomerFeedbackMaster", CUSTOMERFEEDBACKID, id));
			Project project = projectRepository.findById(customerFeedbackMasterDto.getProjectDto().getProjectId())
					.orElseThrow(ResourceNotFoundException::new);

			customerFeedbackMaster.setProject(project);
			customerFeedbackMaster.setFeedbackBy(customerFeedbackMasterDto.getFeedbackBy());
			customerFeedbackMaster.setRole(customerFeedbackMasterDto.getRole());
			customerFeedbackMaster.setEmailId(customerFeedbackMasterDto.getEmailId());

			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.MESSAGE, "updated");
			resultMap.put(ApiResponse.DATA,
					TO_CUSTOMERS_DTO.apply(customerFeedbackMasterRepository.save(customerFeedbackMaster)));
		} catch (Exception e) {
			resultMap.put(ApiResponse.SUCCESS, false);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> deleteCustomerFeedbackMaster(int id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			CustomerFeedbackMaster findById = customerFeedbackMasterRepository.findById(id)
					.orElseThrow(ResourceNotFoundException::new);
			List<CustomerFeedback> findById2 = customerFeedbackRepository
					.findByCustomerFeedbackMasterCustFeedbackId(id);
			findById.setDeletedBy(1);
			customerFeedbackMasterRepository.save(findById);
			findById2.stream().forEach(e -> {
				e.setDeletedBy(1);
				customerFeedbackRepository.save(e);
			});
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.MESSAGE, "delete");
			return new ResponseEntity<>(resultMap, HttpStatus.FOUND);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerFeedbackMailBox(
			CustomerFeedbackMailBoxDTO customerFeedbackMailBoxDTO) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			if (customerFeedbackMailBoxDTO != null) {
				CustomerFeedbackMailBox findByCustomerFeedback = customerFeedbackMailBoxRepo.findByCustomerFeedback(
						customerFeedbackMailBoxDTO.getCustomerFeedbackMaster().getCustFeedbackId());
				if (findByCustomerFeedback != null) {
					resultMap.put(ApiResponse.SUCCESS, false);
					resultMap.put(ApiResponse.DATA, "Mail Already Sent!");
					return new ResponseEntity<>(resultMap, HttpStatus.OK);
				}
				CustomerFeedbackMailBox customerFeedbackMailBox = CUSTOMER_FEEDBACK_MAILBOX
						.apply(customerFeedbackMailBoxDTO).orElse(null);
				CustomerFeedbackMailBox savedCustomerFeedbackMailBox = customerFeedbackMailBoxRepo
						.save(customerFeedbackMailBox);
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.DATA, CUSTOMER_FEEDBACK_MAILBOX_DTO.apply(savedCustomerFeedbackMailBox));
			}

		} catch (Exception e) {
			resultMap.put(ApiResponse.SUCCESS, false);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerFeedbackMail(Integer id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			CustomerFeedbackMailBox findByCustomerFeedback = customerFeedbackMailBoxRepo.findByCustomerFeedback(id);

			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, CUSTOMER_FEEDBACK_MAILBOX_DTO.apply(findByCustomerFeedback));

		} catch (Exception e) {
			resultMap.put(ApiResponse.SUCCESS, false);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);

	}

}
