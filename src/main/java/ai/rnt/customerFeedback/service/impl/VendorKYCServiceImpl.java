package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.dto_mapper.GSTDetailsDtoMapper.TO_GST_DETAILS_LIST;
import static ai.rnt.customerFeedback.dto_mapper.VenderBankDetailsDtoMapper.TO_VENDOR_BANK_DETAILS;
import static ai.rnt.customerFeedback.dto_mapper.VendorDocumentAttachmentDtoMapper.TO_DOCUMENT_ATTACHMENT_LIST;
import static ai.rnt.customerFeedback.dto_mapper.VendorDocumentAttachmentDtoMapper.TO_DOCUMENT_DTO;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.TO_VEDOR_REGISTRATION_DTO_LIST;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.TO_VENDOR_COREGOODSSERVICES_LIST;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.TO_VENDOR_KYC;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.TO_VENDOR_KYC_DTO;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.TO_VENDOR_KYC_MAIN_DTO;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.TO_VENDOR_KYC_RESISTRATION;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.TO_VENDOR_KYC_RESISTRATION_DTO;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.TO_VENDOR_MAJOR_CUSTOMER_LIST;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.TO_VENDOR_SERVICE_PROVIDER_LIST;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.VENDORONBOARDING_MAILBOX;
import static ai.rnt.customerFeedback.dto_mapper.VendorKYCDtoMapper.VENDORONBOARDING_MAILBOX_DTO;
import static java.util.Objects.nonNull;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentDto;
import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentUploadDTO;
import ai.rnt.customerFeedback.dto.VendorKYCDto;
import ai.rnt.customerFeedback.dto.VendorOnboardingMailBoxDTO;
import ai.rnt.customerFeedback.dto.VendorRegistrationDto;
import ai.rnt.customerFeedback.entity.GSTDetails;
import ai.rnt.customerFeedback.entity.VenderBankDetails;
import ai.rnt.customerFeedback.entity.VendorCoreGoodServices;
import ai.rnt.customerFeedback.entity.VendorDocumentAttachment;
import ai.rnt.customerFeedback.entity.VendorKYC;
import ai.rnt.customerFeedback.entity.VendorMajorCustomer;
import ai.rnt.customerFeedback.entity.VendorOnboardingMailBox;
import ai.rnt.customerFeedback.entity.VendorServiceProvider;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.repository.GSTDetailsRepository;
import ai.rnt.customerFeedback.repository.VendorBankDetailsRepository;
import ai.rnt.customerFeedback.repository.VendorCoreGoodServicesRepository;
import ai.rnt.customerFeedback.repository.VendorDocumentAttachmentRepository;
import ai.rnt.customerFeedback.repository.VendorKYCRepository;
import ai.rnt.customerFeedback.repository.VendorMajorCustomerRepository;
import ai.rnt.customerFeedback.repository.VendorOnboardingMailBoxRepo;
import ai.rnt.customerFeedback.repository.VendorServiceProviderRepository;
import ai.rnt.customerFeedback.service.VendorKYCService;
import ai.rnt.customerFeedback.util.S3BucketUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VendorKYCServiceImpl implements VendorKYCService {
	@Autowired
	private VendorKYCRepository vendorKYCRepository;

	@Autowired
	private VendorBankDetailsRepository vendorBankDetailsRepository;

	@Autowired
	private VendorDocumentAttachmentRepository vendorDocumentAttachmentRepository;

	@Autowired
	private GSTDetailsRepository gSTDetailsRepository;

	@Autowired
	private VendorServiceProviderRepository vendorServiceProviderRepository;

	@Autowired
	private VendorMajorCustomerRepository vendorMajorCustomerRepository;

	@Autowired
	private VendorCoreGoodServicesRepository vendorCoreGoodServicesRepository;

	@Autowired
	private VendorOnboardingMailBoxRepo vendorOnboardingMailBoxRepo;

	@Autowired
	private S3BucketUtil bucketUtil;

	@Override
	@Transactional
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveVendorKYCForm(VendorKYCDto vendorKYCDto) {
		log.info("Enter into save vendor form method...{}");
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		VenderBankDetails savedVenderBankDetails = new VenderBankDetails();
		List<GSTDetails> savedGSTDetails = new ArrayList<>();
		List<VendorCoreGoodServices> savedvendorCoreGoodServicesList = new ArrayList<>();
		List<VendorMajorCustomer> savedvendorMajorCustomersList = new ArrayList<>();
		List<VendorServiceProvider> savedVendorServiceProviderList = new ArrayList<>();
		try {
			VendorKYC existingVendorKYC = vendorKYCRepository.findById(vendorKYCDto.getVendorKycId()).orElse(null);
			if (Objects.isNull(existingVendorKYC)) {
				responseMap.put(ApiResponse.SUCCESS, false);
				return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
			} else {
				List<VendorDocumentAttachmentDto> vendorDocumentAttachmentListDto = vendorKYCDto
						.getVendorDocumentAttachmentList();

				VenderBankDetails venderBankDetails = TO_VENDOR_BANK_DETAILS.apply(vendorKYCDto.getVenderBankDetails())
						.orElse(null);
				if (nonNull(existingVendorKYC.getVenderBankDetails())
						&& nonNull(existingVendorKYC.getVenderBankDetails().getVendorBankDtlId()))
					venderBankDetails.setVendorBankDtlId(existingVendorKYC.getVenderBankDetails().getVendorBankDtlId());
				List<GSTDetails> gstdetailsList = TO_GST_DETAILS_LIST.apply(vendorKYCDto.getGstdetailsList());
				List<VendorServiceProvider> vendorServiceProviderList = TO_VENDOR_SERVICE_PROVIDER_LIST
						.apply(vendorKYCDto.getVendorServiceProviderList());
				List<VendorCoreGoodServices> vendorCoreGoodServices = TO_VENDOR_COREGOODSSERVICES_LIST
						.apply(vendorKYCDto.getVendorCoreGoodServicesList());
				List<VendorMajorCustomer> vendorMajorCustomers = TO_VENDOR_MAJOR_CUSTOMER_LIST
						.apply(vendorKYCDto.getVendorMajorCustomerList());

				vendorKYCDto.setVendorKycId(existingVendorKYC.getVendorKycId());
				vendorKYCDto.setContactName(existingVendorKYC.getContactName());
				vendorKYCDto.setEmailId(existingVendorKYC.getEmailId());
				vendorKYCDto.setMobileNo(existingVendorKYC.getMobileNo());
				vendorKYCDto.setParseDate(
						vendorKYCDto.getOnboardingDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
				vendorKYCDto.setVenderBankDetails(null);
				VendorKYC savedVendorKYC = vendorKYCRepository.save(TO_VENDOR_KYC.apply(vendorKYCDto).orElse(null));
				if (Objects.nonNull(venderBankDetails)) {
					venderBankDetails.setVendorKYC(savedVendorKYC);
					savedVenderBankDetails = vendorBankDetailsRepository.save(venderBankDetails);
				}
				if (Objects.nonNull(gstdetailsList)) {
					gstdetailsList.forEach(gst -> gst.setVendorKYC(savedVendorKYC));
					savedGSTDetails = gSTDetailsRepository.saveAll(gstdetailsList);
				}
				if (Objects.nonNull(vendorServiceProviderList)) {
					vendorServiceProviderList.forEach(service -> service.setVendorKYC(savedVendorKYC));
					savedVendorServiceProviderList = vendorServiceProviderRepository.saveAll(vendorServiceProviderList);
				}
				if (Objects.nonNull(vendorCoreGoodServices)) {
					vendorCoreGoodServices.forEach(goods -> goods.setVendorKYC(savedVendorKYC));
					savedvendorCoreGoodServicesList = vendorCoreGoodServicesRepository.saveAll(vendorCoreGoodServices);
				}
				if (Objects.nonNull(vendorMajorCustomers)) {
					vendorMajorCustomers.forEach(customer -> customer.setVendorKYC(savedVendorKYC));
					savedvendorMajorCustomersList = vendorMajorCustomerRepository.saveAll(vendorMajorCustomers);
				}

				savedVendorKYC.setVenderBankDetails(savedVenderBankDetails);
				List<VendorDocumentAttachment> newVendorDocumentAttachmentList = TO_DOCUMENT_ATTACHMENT_LIST
						.apply(vendorDocumentAttachmentListDto);
				newVendorDocumentAttachmentList.forEach(attach -> attach.setVendorKYC(savedVendorKYC));
				vendorDocumentAttachmentRepository.saveAll(newVendorDocumentAttachmentList);
				savedVendorKYC.setGstdetailsList(savedGSTDetails);
				savedVendorKYC.setVendorCoreGoodServicesList(savedvendorCoreGoodServicesList);
				savedVendorKYC.setVendorServiceProviderList(savedVendorServiceProviderList);
				savedVendorKYC.setVendorMajorCustomerList(savedvendorMajorCustomersList);
				responseMap.put(ApiResponse.SUCCESS, true);
				responseMap.put(ApiResponse.DATA, TO_VENDOR_KYC_DTO.apply(savedVendorKYC).orElse(null));
				return new ResponseEntity<>(responseMap, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseMap.put(ApiResponse.SUCCESS, false);
			responseMap.put(ApiResponse.MESSAGE, e.getMessage());
			return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> findAll() {
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);

		List<VendorKYC> vendorKYCList = vendorKYCRepository.findAllByOrderByCreatedDateDescVendorKycIdDesc();
		vendorKYCList.forEach(vendor -> {
			vendor.setSortStatus(0);
			if (vendor.getDeclName() != null) {
				vendor.setFormFilled(true);
				vendor.setSortStatus(1);
			}

			vendor.setIsEmailSend(vendor.getVendorOnboardingMailBox() == null ? false : true);

			vendor.getVendorDocumentAttachmentList().forEach(doc -> {
				if (doc.getVendorDocumentMaster().getDocumentId() == 10) {
					vendor.setStatus(true);
					vendor.setSortStatus(2);
					vendor.setFilledDate((!Objects.isNull(doc.getCreatedDate()))
							? (doc.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
							: null);
				}
			});

			vendor.setParseDate((!Objects.isNull(vendor.getCreatedDate()))
					? (vendor.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
					: null);
		});

		responseMap.put(ApiResponse.SUCCESS, true);
		responseMap.put(ApiResponse.DATA, TO_VEDOR_REGISTRATION_DTO_LIST.apply(vendorKYCList));
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> findById(Integer kycId) {
		log.info("Enter into findByKYCId method...{} ");
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		try {
			VendorKYC vendorKyc = vendorKYCRepository.findByvendorKycId(kycId);
			Optional<VendorKYCDto> vendorKYCMainDto = TO_VENDOR_KYC_MAIN_DTO.apply(vendorKyc);
			vendorKYCMainDto.ifPresent(vk -> {
				vk.getVendorDocumentAttachmentList().forEach(doc -> {
					if (doc.getVendorDocumentMaster().getDocumentId() == 10) {
						vk.setStatus(true);
						vk.setFilledDate((!Objects.isNull(doc.getCreatedDate()))
								? (doc.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
								: null);
					}
				});
				if (vk.getDeclName() != null) {
					vk.setFormFilled(true);
				}
				vk.setParseDate((!Objects.isNull(vk.getCreatedDate()))
						? (vk.getCreatedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
						: null);
			});

			responseMap.put(ApiResponse.SUCCESS, true);
			responseMap.put(ApiResponse.DATA, vendorKYCMainDto);
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}

	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveVendorRegistration(
			VendorRegistrationDto vendorRegistrationDto) {
		log.info("Enter into save vendor form method...{} ");
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);

		try {
			VendorKYC vendorKYC = TO_VENDOR_KYC_RESISTRATION.apply(vendorRegistrationDto).orElse(null);
			VendorKYC savedVendorKYC = vendorKYCRepository.save(vendorKYC);
			responseMap.put(ApiResponse.SUCCESS, true);
			responseMap.put(ApiResponse.DATA, TO_VENDOR_KYC_RESISTRATION_DTO.apply(savedVendorKYC));
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getFullForm(Integer id) {
		log.info("Enter into findByKYCId method...{} ");
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		try {
			VendorKYC vendorKyc = vendorKYCRepository.findByvendorKycId(id);
			VendorKYCDto vendorKYCDto = TO_VENDOR_KYC_MAIN_DTO.apply(vendorKyc).orElse(null);
			responseMap.put(ApiResponse.SUCCESS, true);
			responseMap.put(ApiResponse.DATA, vendorKYCDto);
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> updateVendorRegistration(
			VendorRegistrationDto vendorRegistrationDto) {
		log.info("Enter into save vendor form method...{} ");
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);

		try {
			VendorKYC existingVendorKYC = vendorKYCRepository.findById(vendorRegistrationDto.getVendorKycId())
					.orElse(null);
			if (Objects.isNull(existingVendorKYC)) {
				responseMap.put(ApiResponse.SUCCESS, false);
				return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
			} else {

				existingVendorKYC.setContactName(vendorRegistrationDto.getContactName());
				existingVendorKYC.setEmailId(vendorRegistrationDto.getEmailId());
				existingVendorKYC.setTradeName(vendorRegistrationDto.getTradeName());
				existingVendorKYC.setMobileNo(vendorRegistrationDto.getMobileNo());
				responseMap.put(ApiResponse.SUCCESS, true);
				VendorKYC savedVendor = vendorKYCRepository.save(existingVendorKYC);
				responseMap.put(ApiResponse.DATA, TO_VENDOR_KYC_RESISTRATION_DTO.apply(savedVendor));
				return new ResponseEntity<>(responseMap, HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> uploadDocument(
			VendorDocumentAttachmentUploadDTO vendorDocumentAttachmentUploadDTO) {
		log.info("Enter into findByKYCId method...{} ");
		EnumMap<ApiResponse, Object> responseMap = new EnumMap<>(ApiResponse.class);
		try {
			VendorDocumentAttachment vendorDocumentAttachment = TO_DOCUMENT_DTO.apply(vendorDocumentAttachmentUploadDTO)
					.orElseThrow(ResourceNotFoundException::new);
			vendorDocumentAttachmentRepository.save(vendorDocumentAttachment);
			responseMap.put(ApiResponse.SUCCESS, true);
			responseMap.put(ApiResponse.DATA, "Form Submitted Successfully !");
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> saveVendoremail(
			VendorOnboardingMailBoxDTO vendorOnboardingMailBoxDTO) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			if (vendorOnboardingMailBoxDTO != null) {
				VendorOnboardingMailBox findByVendoronboarding = vendorOnboardingMailBoxRepo
						.findByVendoronboardingId(vendorOnboardingMailBoxDTO.getVendorKYC().getVendorKycId());
				if (findByVendoronboarding != null) {
					resultMap.put(ApiResponse.SUCCESS, false);
					resultMap.put(ApiResponse.DATA, "Mail Already Sent!");
					return new ResponseEntity<>(resultMap, HttpStatus.OK);
				}
				VendorOnboardingMailBox vendorOnboardingMailBox = VENDORONBOARDING_MAILBOX
						.apply(vendorOnboardingMailBoxDTO).orElse(null);
				VendorOnboardingMailBox savedvendorOnboardingMailBox = vendorOnboardingMailBoxRepo
						.save(vendorOnboardingMailBox);
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.DATA, VENDORONBOARDING_MAILBOX_DTO.apply(savedvendorOnboardingMailBox));
			}

		} catch (Exception e) {
			resultMap.put(ApiResponse.SUCCESS, false);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getSavedEmail(int kycId) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			VendorOnboardingMailBox findByVendoronboarding = vendorOnboardingMailBoxRepo
					.findByVendoronboardingId(kycId);
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, VENDORONBOARDING_MAILBOX_DTO.apply(findByVendoronboarding));
		} catch (Exception e) {
			resultMap.put(ApiResponse.SUCCESS, false);
			throw new CRMException(e);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@Override
	public void getPdfFile(HttpServletResponse response, String venderId, String docId) {

		try {
			byte[] decodedBytes1 = Base64.getDecoder().decode(venderId);
			String decodedData1 = new String(decodedBytes1);
			int number = Integer.parseInt(decodedData1);
			byte[] decodedBytes = Base64.getDecoder().decode(docId);
			String decodedData = new String(decodedBytes);
			int docuId = Integer.parseInt(decodedData);

			VendorKYC existingVendorKYC = vendorKYCRepository.findById(number).orElse(null);
			String base64 = null;
			if (Objects.nonNull(existingVendorKYC)) {

				base64 = existingVendorKYC.getVendorDocumentAttachmentList().stream()
						.filter(doc -> Objects.equals(doc.getVendorDocumentMaster().getDocumentId(), docuId))
						.map(VendorDocumentAttachment::getDocumentAttachment).findFirst().orElse(null);
			}

			if (Objects.nonNull(base64) && base64.startsWith("data:application/pdf;base64,")) {
				base64 = base64.substring("data:application/pdf;base64,".length());
			}

			byte[] bytes = Base64.getDecoder().decode(base64);
			response.setContentType("application/pdf");
			response.getOutputStream().write(bytes);
		} catch (Exception e) {
			throw new CRMException(e);
		}

	}

	@Override
	public void getBugImage(String imangeName, HttpServletResponse response) {
		try {
			Map<String, Object> bugImage = bucketUtil.getBugImage(imangeName);
			String document = (String) bugImage.get("DATA");
			String base64 = null;
			if (Objects.nonNull(document)) {

				base64 = document;
			}

			if (Objects.nonNull(base64) && base64.startsWith("data:image/jpg;base64,")) {
				base64 = base64.substring("data:image/jpg;base64,".length());
			}

			byte[] bytes = Base64.getDecoder().decode(base64);
			response.setContentType("image/svg+xml");
			response.getOutputStream().write(bytes);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

}
