/**
 * 
 */
package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.constants.SecurityConstant.CUSTOMERSATISFACTIONID;
import static ai.rnt.customerFeedback.dto_mapper.AddressMasterModelMapper.TO_ADDRESS_MASTER_DTO;
import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionMasterDtoMapper.CUSTOMER_SATISFACTION_MAILBOX;
import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionMasterDtoMapper.CUSTOMER_SATISFACTION_MAILBOX_DTO;
import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionMasterDtoMapper.TO_CUSTOMER_SATISFACTION_DTO;
import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionMasterDtoMapper.TO_CUSTOMER_SATISFACTION_MASTER_LIST_DTO;
import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionMasterDtoMapper.TO_CUST_SATISFACTION_MASTER;
import static ai.rnt.customerFeedback.dto_mapper.CustomerSatisfactionMasterDtoMapper.TO_CUST_SATISFACTION_MASTER_DTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.dto.AddressMasterDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMailBoxDTO;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMasterDTONew;
import ai.rnt.customerFeedback.dto.CustomerSatisfactionMasterDto;
import ai.rnt.customerFeedback.entity.AddressMaster;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionMailBox;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionMaster;
import ai.rnt.customerFeedback.entity.CustomerSatisfactionSurvey;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.AddressMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionMailBoxRepo;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionMasterRepository;
import ai.rnt.customerFeedback.repository.CustomerSatisfactionSurveyRepository;
import ai.rnt.customerFeedback.service.CustomerSatisfactionMasterService;

/**
 * @author Abhishek Ingle
 * @Date Jan 11, 2024
 * @Version 1.0
 */

@Service
public class CustomerSatisfactionMasterServiceImpl implements CustomerSatisfactionMasterService {

	@Autowired
	private CustomerSatisfactionMasterRepository customerSatisfactionMasterRepository;

	@Autowired
	private CustomerSatisfactionSurveyRepository customerSatisfactionSurveyRepository;

	@Autowired
	AddressMasterRepository addressMasterRepository;

	@Autowired
	CustomerSatisfactionMailBoxRepo satisfactionMailBoxRepo;

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerSatisfactionMaster(
			CustomerSatisfactionMasterDto customerSatisfactionMasterDto) {
		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);
		map.put(ApiResponse.SUCCESS, true);
		CustomerSatisfactionMaster customerSatisfactionMaster = TO_CUST_SATISFACTION_MASTER
				.apply(customerSatisfactionMasterDto).orElse(null);
		customerSatisfactionMasterRepository.save(customerSatisfactionMaster);
		map.put(ApiResponse.MESSAGE, customerSatisfactionMasterDto);
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerSatisfactionMasterById(Integer id) {
		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);

		CustomerSatisfactionMaster customerSatisfactionMaster = customerSatisfactionMasterRepository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("CustomerSatisfactionMaster", "custSatisfactionId", id));

		List<CustomerSatisfactionSurvey> customerSatisfactionSurveys = customerSatisfactionSurveyRepository
				.findByCustomerSatisfactionMasterCustSatisfactionId(id);

		if (!customerSatisfactionSurveys.isEmpty()) {
			CustomerSatisfactionSurvey firstSurvey = customerSatisfactionSurveys.get(0);
			String formattedDate = firstSurvey.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			boolean isFilled = !customerSatisfactionSurveys.isEmpty();
			customerSatisfactionMaster.setFilled(isFilled);
			customerSatisfactionMaster.setFilledDate(formattedDate);
		} else {
			customerSatisfactionMaster.setFilled(false);
			customerSatisfactionMaster.setFilledDate(null); // Or set to a default value
		}

		AddressMaster addressMaster = null;
		AddressMasterDTO addressMasterDTO = null;
		if (customerSatisfactionMaster.getAddressId() != null) {
			addressMaster = addressMasterRepository.findById(customerSatisfactionMaster.getAddressId()).orElse(null);
			if (addressMaster != null) {
				addressMasterDTO = TO_ADDRESS_MASTER_DTO.apply(addressMaster).orElse(null);

			}
		}

		CustomerSatisfactionMasterDTONew customerSatisfactionMasterDto = TO_CUSTOMER_SATISFACTION_DTO
				.apply(customerSatisfactionMaster).orElseThrow(ResourceNotFoundException::new);
		if (addressMasterDTO != null) {
			customerSatisfactionMasterDto.setAddressMaster(addressMasterDTO);
		}

		map.put(ApiResponse.SUCCESS, true);
		map.put(ApiResponse.DATA, customerSatisfactionMasterDto);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAllCustomerSatisfactionMaster() {

		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);

		try {
			List<CustomerSatisfactionMaster> findAll = customerSatisfactionMasterRepository
					.findAll(Sort.by(Sort.Direction.DESC, CUSTOMERSATISFACTIONID));
			findAll.stream().forEach(e -> {
				List<CustomerSatisfactionSurvey> findById2 = customerSatisfactionSurveyRepository
						.findByCustomerSatisfactionMasterCustSatisfactionId(e.getCustSatisfactionId());
				if (!findById2.isEmpty()) {
					String formattedDate = findById2.get(0).getCreatedDate()
							.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

					e.setFilled(true);
					e.setSortStatus(1);
					e.setFilledDate(formattedDate);
				} else {
					e.setFilledDate(null);
					e.setFilled(false);
					e.setSortStatus(0);
				}
				e.setIsEmailSend(e.getCustomerSatisfactionMailBox() != null);
				e.setFormCreatedDate(e.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			});
			List<CustomerSatisfactionDTO> list = TO_CUSTOMER_SATISFACTION_MASTER_LIST_DTO.apply(findAll);
			list.stream().forEach(customer -> {
				AddressMaster addressMaster = addressMasterRepository.findById(customer.getAddressId()).orElse(null);
				customer.setCustomerId(addressMaster.getCustomer().getCustomerId());
				customer.setCompanyName(addressMaster.getCustomer().getCompanyName());
				customer.setContactPersonName(addressMaster.getContactPersonName());
				customer.setContactPersonEmail(addressMaster.getContactPersonEmail());
				if (addressMaster.getContactPersonNo() == null || addressMaster.getContactPersonNo().isEmpty()) {
					customer.setContactPersonNo("-");
				} else {
					customer.setContactPersonNo(addressMaster.getContactPersonNo());
				}

			});
			map.put(ApiResponse.SUCCESS, true);
			map.put(ApiResponse.DATA, list);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> updateCustomerSatisfactionMaster(Integer id,
			CustomerSatisfactionMasterDto customerSatisfactionMasterDto) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			TO_CUST_SATISFACTION_MASTER.apply(customerSatisfactionMasterDto).ifPresent(custMaster -> {
				CustomerSatisfactionMaster customerSatisfactionMaster = customerSatisfactionMasterRepository
						.findById(id).orElseThrow(() -> new ResourceNotFoundException("CustomerSatisfactionMaster",
								CUSTOMERSATISFACTIONID, id));
				customerSatisfactionMaster.setAddressId(customerSatisfactionMasterDto.getAddressId());
				resultMap.put(ApiResponse.DATA, TO_CUST_SATISFACTION_MASTER_DTO
						.apply(customerSatisfactionMasterRepository.save(customerSatisfactionMaster)));

			});
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.MESSAGE, "updated");
		} catch (Exception e) {
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> deleteCustomerSatisfactionMaster(Integer id) {

		EnumMap<ApiResponse, Object> map = new EnumMap<>(ApiResponse.class);

		CustomerSatisfactionMaster customerSatisfactionMaster = customerSatisfactionMasterRepository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("CustomerSatisfactionMaster", "custSatisfactionId", id));
		customerSatisfactionMaster.setDeletedBy(1);
		customerSatisfactionMaster.setDeletedDate(LocalDateTime.now());

		map.put(ApiResponse.SUCCESS, true);
		map.put(ApiResponse.MESSAGE, "Deleted Successfully");

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getAddressMasterById(Integer id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);

		try {
			AddressMaster addressMaster = addressMasterRepository.findById(id).orElse(null);
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_ADDRESS_MASTER_DTO.apply(addressMaster));

		} catch (Exception e) {
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveCustomerSatisfactionMailBox(
			CustomerSatisfactionMailBoxDTO satisfactionMailBoxDTO) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			if (satisfactionMailBoxDTO != null) {
				CustomerSatisfactionMailBox findBySatisfactionMaster = satisfactionMailBoxRepo
						.findByCustomerSatisfaction(
								satisfactionMailBoxDTO.getCustomerSatisfactionMaster().getCustSatisfactionId());
				if (findBySatisfactionMaster != null) {
					resultMap.put(ApiResponse.SUCCESS, false);
					resultMap.put(ApiResponse.DATA, "Mail Already Sent!");
					return new ResponseEntity<>(resultMap, HttpStatus.OK);
				}
				CustomerSatisfactionMailBox satisfactionMailBox = CUSTOMER_SATISFACTION_MAILBOX
						.apply(satisfactionMailBoxDTO).orElse(null);
				CustomerSatisfactionMailBox savedCustomerFeedbackMailBox = satisfactionMailBoxRepo
						.save(satisfactionMailBox);
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.DATA, CUSTOMER_SATISFACTION_MAILBOX_DTO.apply(savedCustomerFeedbackMailBox));
			}

		} catch (Exception e) {
			resultMap.put(ApiResponse.SUCCESS, false);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getCustomerSatisfactionMail(Integer id) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			CustomerSatisfactionMailBox findBySatisfactionMaster = satisfactionMailBoxRepo
					.findByCustomerSatisfaction(id);
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, CUSTOMER_SATISFACTION_MAILBOX_DTO.apply(findBySatisfactionMaster));

		} catch (Exception e) {
			resultMap.put(ApiResponse.SUCCESS, false);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

//	@Override
//	public ResponseEntity<EnumMap<ApiResponse, Object>> getPseudoName(String name) {
//		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
//
//		try {
//			String companyPseudoName = clientRepository.getCompanyPseudoName(name);
//	        if (companyPseudoName != null && !companyPseudoName.isEmpty()) {
//			resultMap.put(ApiResponse.SUCCESS, true);
//			resultMap.put(ApiResponse.DATA, clientRepository.getCompanyPseudoName(name));
//			}
//			else {
//				resultMap.put(ApiResponse.SUCCESS, false);
//				resultMap.put(ApiResponse.DATA, "No pseudo name for "+name);
//	
//			}
//		} catch (Exception e) {
//			throw new CRMException(e);
//		}
//		return new ResponseEntity<>(resultMap, HttpStatus.OK);
//	}

}
