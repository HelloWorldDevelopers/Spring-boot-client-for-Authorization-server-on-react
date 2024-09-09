package ai.rnt.customerFeedback.service.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

import ai.rnt.customerFeedback.dto.CountryMasterDTO;
import ai.rnt.customerFeedback.dto.GSTDetailsDto;
import ai.rnt.customerFeedback.dto.VenderBankDetailsDto;
import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentDto;
import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentUploadDTO;
import ai.rnt.customerFeedback.dto.VendorKYCDto;
import ai.rnt.customerFeedback.dto.VendorKYCNewDTO;
import ai.rnt.customerFeedback.dto.VendorOnboardingMailBoxDTO;
import ai.rnt.customerFeedback.dto.VendorRegistrationDto;
import ai.rnt.customerFeedback.entity.VendorDocumentAttachment;
import ai.rnt.customerFeedback.entity.VendorDocumentMaster;
import ai.rnt.customerFeedback.entity.VendorKYC;
import ai.rnt.customerFeedback.entity.VendorOnboardingMailBox;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.GSTDetailsRepository;
import ai.rnt.customerFeedback.repository.VendorBankDetailsRepository;
import ai.rnt.customerFeedback.repository.VendorCoreGoodServicesRepository;
import ai.rnt.customerFeedback.repository.VendorDocumentAttachmentRepository;
import ai.rnt.customerFeedback.repository.VendorKYCRepository;
import ai.rnt.customerFeedback.repository.VendorMajorCustomerRepository;
import ai.rnt.customerFeedback.repository.VendorOnboardingMailBoxRepo;
import ai.rnt.customerFeedback.repository.VendorServiceProviderRepository;
import ai.rnt.customerFeedback.util.S3BucketUtil;

@ExtendWith(MockitoExtension.class)
class VendorKYCServiceImplTest {

	@Mock
	private VendorKYCRepository vendorKYCRepository;

	@Mock
	private VendorBankDetailsRepository vendorBankDetailsRepository;

	@Mock
	private VendorDocumentAttachmentRepository vendorDocumentAttachmentRepository;

	@Mock
	private GSTDetailsRepository gSTDetailsRepository;

	@Mock
	private VendorServiceProviderRepository vendorServiceProviderRepository;

	@Mock
	private VendorMajorCustomerRepository vendorMajorCustomerRepository;

	@Mock
	private VendorCoreGoodServicesRepository vendorCoreGoodServicesRepository;

	@Mock
	private VendorOnboardingMailBoxRepo mailBoxRepo;
	@Mock
	private S3BucketUtil bucketUtil;

	@InjectMocks
	private VendorKYCServiceImpl vendorKYCService;
	private VendorKYC existingVendorKYC;
	private VendorDocumentAttachment vendorDocumentAttachment;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		vendorDocumentAttachment = new VendorDocumentAttachment();
		VendorDocumentMaster vendorDocumentMaster = new VendorDocumentMaster();
		vendorDocumentMaster.setDocumentId(456);
		vendorDocumentAttachment.setVendorDocumentMaster(vendorDocumentMaster);
		vendorDocumentAttachment
				.setDocumentAttachment("data:application/pdf;base64,JVBERi0xLjQKJeLjz9MNCjEgMCBvYmoKMiAwIG9iag=="); // sample
																													// base64

		existingVendorKYC = new VendorKYC();
		existingVendorKYC.setVendorDocumentAttachmentList(Collections.singletonList(vendorDocumentAttachment));

	}

	@Test
	void testFindAll() {
		VendorKYC vendor1 = new VendorKYC();
		vendor1.setVendorKycId(1);
		vendor1.setDeclName("Vendor1");
		List<VendorDocumentAttachment> documentlist = new ArrayList<>();
		VendorDocumentAttachment vendorAttachment = new VendorDocumentAttachment();
		vendorAttachment.setAttachmentId(1);
		VendorDocumentMaster vendor = new VendorDocumentMaster();
		vendor.setDocumentId(10);
		vendor.setDocumentType("Signed Form");
		vendorAttachment.setVendorDocumentMaster(vendor);
		documentlist.add(vendorAttachment);
		vendor1.setVendorDocumentAttachmentList(documentlist);
		vendor1.setStatus(true);
		vendor1.setFilledDate("12-09-2024");
		VendorKYC vendor2 = new VendorKYC();
		vendor2.setVendorKycId(2);
		vendor2.setDeclName(null);
		vendor2.setVendorDocumentAttachmentList(documentlist);
		List<VendorKYC> vendorKYCList = Arrays.asList(vendor1, vendor2);

		when(vendorKYCRepository.findAllByOrderByCreatedDateDescVendorKycIdDesc()).thenReturn(vendorKYCList);

		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCService.findAll();

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		EnumMap<ApiResponse, Object> responseMap = responseEntity.getBody();
		assertNotNull(responseMap);
		assertTrue((boolean) responseMap.get(ApiResponse.SUCCESS));

		List<VendorRegistrationDto> vendorRegistrationDtoList = (List<VendorRegistrationDto>) responseMap
				.get(ApiResponse.DATA);
		assertNotNull(vendorRegistrationDtoList);
		assertEquals(2, vendorRegistrationDtoList.size());

		VendorRegistrationDto dto1 = vendorRegistrationDtoList.get(0);
		assertEquals(1, dto1.getVendorKycId());
		assertTrue(dto1.isStatus());

		VendorRegistrationDto dto2 = vendorRegistrationDtoList.get(1);
		assertEquals(2, dto2.getVendorKycId());
	}

	@Test
	void testGetFullForm() {
		vendorKYCService.getFullForm(any());
		when(vendorKYCRepository.findByvendorKycId(any())).thenThrow(NullPointerException.class);
		assertThrows(CRMException.class, () -> vendorKYCService.getFullForm(any()));
	}

	@Test
	void testFindById() {
		Integer kycId = 1;
		VendorKYC vendorKYC = new VendorKYC();
		vendorKYC.setVendorKycId(kycId);
		vendorKYC.setOnboardingDate(LocalDate.now());
		vendorKYC.setFilledDate("22-03-2024");
		vendorKYC.setCreatedDate(LocalDateTime.now());
		List<VendorDocumentAttachment> vendorAttachment = new ArrayList<>();
		VendorDocumentAttachment vendorDoc = new VendorDocumentAttachment();
		vendorDoc.setAttachmentId(1);
		vendorDoc.setFileName("fileName");
		vendorDoc.setFileType("fileType");
		vendorDoc.setDocumentAttachment("document attachment");
		VendorDocumentMaster vendorDocument = new VendorDocumentMaster();
		vendorDocument.setDocumentId(1);
		vendorDocument.setVendorDocumentAttachment(vendorAttachment);
		vendorDoc.setVendorDocumentMaster(vendorDocument);
		vendorAttachment.add(vendorDoc);
		vendorKYC.setVendorDocumentAttachmentList(vendorAttachment);
		when(vendorKYCRepository.findByvendorKycId(kycId)).thenReturn(vendorKYC);

		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCService.findById(kycId);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		EnumMap<ApiResponse, Object> responseMap = responseEntity.getBody();
		assertNotNull(responseMap);
		assertTrue((boolean) responseMap.get(ApiResponse.SUCCESS));

		Optional<VendorKYCDto> vendorKYCMainDto = (Optional<VendorKYCDto>) responseMap.get(ApiResponse.DATA);
		assertNotNull(vendorKYCMainDto);

		vendorKYCMainDto.ifPresent(vk -> {
			assertTrue(Objects.nonNull(vk.getOnboardingDate()));
			assertEquals(kycId, vk.getVendorKycId());
			assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), vk.getParseDate());
			when(vendorKYCRepository.findByvendorKycId(kycId)).thenThrow(NullPointerException.class);
			assertThrows(CRMException.class, () -> vendorKYCService.findById(kycId));

		});

	}

	@Test
	void testSaveVendorKYCForm() {
		VendorKYCDto vendorKYCDto = new VendorKYCDto();
		vendorKYCDto.setVendorKycId(1);
		vendorKYCDto.setDeclName("John Doe");
		vendorKYCDto.setOnboardingDate(LocalDate.now());
		vendorKYCDto.setCinId("12345");
		vendorKYCDto.setContactPosition("developer");
		vendorKYCDto.setContactName("abc");
		vendorKYCDto.setAlternateNo("6789654");
		vendorKYCDto.setTradeName("trade name");
		vendorKYCDto.setTelephoneNo("89076433");
		vendorKYCDto.setIsUnderGst("Yes");
		vendorKYCDto.setIsUnderTaxExemptions("No");
		vendorKYCDto.setDeclName("name");
		vendorKYCDto.setDeclDesignation("dev");
		vendorKYCDto.setStatus(true);
		vendorKYCDto.setParseDate(vendorKYCDto.getOnboardingDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		VenderBankDetailsDto venderBankDetailsDto = new VenderBankDetailsDto();
		venderBankDetailsDto.setAccCurrency("$");
		venderBankDetailsDto.setAccountNo("7998766");
		venderBankDetailsDto.setBankAddressLine1("iuwdwieugcd");
		venderBankDetailsDto.setBankAddressLine2("iuwdwieugcd");
		venderBankDetailsDto.setCity("phalatan");
		venderBankDetailsDto.setState("phalatan");
		venderBankDetailsDto.setZipCode("7655433");
		venderBankDetailsDto.setBankName("abc");
		venderBankDetailsDto.setBeneficiaryAccName("liuhcde");
		venderBankDetailsDto.setBranch("IT");
		venderBankDetailsDto.setIfscCode("IFSC235");
		venderBankDetailsDto.setIntermediaryBankDtl("wiehucd oiuegc");
		venderBankDetailsDto.setSwiftCode("87887");
		vendorKYCDto.setVenderBankDetails(venderBankDetailsDto);
		CountryMasterDTO countryMasterDTO = new CountryMasterDTO();
		countryMasterDTO.setCountryId(1);
		countryMasterDTO.setCountry("India");
		vendorKYCDto.setBillingAddressLine1("Billing address");
		vendorKYCDto.setBillingAddressLine2("Billing address");
		vendorKYCDto.setBillingCity("city");
		vendorKYCDto.setBillingcountryMaster(countryMasterDTO);
		vendorKYCDto.setBillingState("state");
		vendorKYCDto.setBillingZipCode("789098");
		GSTDetailsDto gSTDetailsDto = new GSTDetailsDto();
		gSTDetailsDto.setGstAddress("address");
		gSTDetailsDto.setGstNo("7977578");
		List<GSTDetailsDto> gSTDetailsDtolist = new ArrayList<>();
		gSTDetailsDtolist.add(gSTDetailsDto);

		VendorKYC existingVendorKYC = new VendorKYC();
		existingVendorKYC.setVendorKycId(1);
		existingVendorKYC.setDeclName("Old DeclName");
		existingVendorKYC.setOnboardingDate(LocalDate.now().minusDays(1));
		existingVendorKYC.setCinId("12345");
		existingVendorKYC.setContactPosition("developer");
		existingVendorKYC.setContactName("abc");
		existingVendorKYC.setAlternateNo("6789654");
		existingVendorKYC.setTradeName("trade name");
		existingVendorKYC.setTelephoneNo("89076433");
		existingVendorKYC.setIsUnderGst("Yes");
		existingVendorKYC.setIsUnderTaxExemptions("No");
		existingVendorKYC.setDeclName("name");
		existingVendorKYC.setDeclDesignation("dev");
		existingVendorKYC.setStatus(true);
		existingVendorKYC
				.setParseDate(vendorKYCDto.getOnboardingDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		existingVendorKYC.setBillingAddressLine1("billing address");
		existingVendorKYC.setBillingAddressLine2("billing address");
		existingVendorKYC.setBillingCity("city");
		existingVendorKYC.setBillingState("state");
		existingVendorKYC.setBillingZipCode("786523");
		existingVendorKYC.setShippingAddressLine1("shipping address");
		existingVendorKYC.setShippingAddressLine2("shipping address");
		existingVendorKYC.setShippingCity("city");
		existingVendorKYC.setShippingState("state");

		List<VendorDocumentAttachmentDto> vendorDocumentAttachmentListDto = new ArrayList();
		VendorDocumentAttachmentDto attachment1 = new VendorDocumentAttachmentDto();
		attachment1.setAttachmentId(1);
		attachment1.setDocumentAttachment("attachment");
		attachment1.setFileName("bvhg");
		attachment1.setFileType("type");
		vendorDocumentAttachmentListDto.add(attachment1);

		when(vendorKYCRepository.findById(existingVendorKYC.getVendorKycId()))
				.thenReturn(Optional.of(existingVendorKYC));
		when(vendorKYCRepository.save(any(VendorKYC.class))).thenReturn(existingVendorKYC);

		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCService.saveVendorKYCForm(vendorKYCDto);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		EnumMap<ApiResponse, Object> responseMap = responseEntity.getBody();
		assertNotNull(responseMap);
		assertTrue((boolean) responseMap.get(ApiResponse.SUCCESS));

		VendorKYCDto savedVendorKYCDto = (VendorKYCDto) responseMap.get(ApiResponse.DATA);
		assertNotNull(savedVendorKYCDto);

		assertEquals(existingVendorKYC.getVendorKycId(), savedVendorKYCDto.getVendorKycId());

		assertEquals(vendorKYCDto.getDeclName(), savedVendorKYCDto.getDeclName());
	}

	@Test
	void testUpdateVendorRegistration() {

		VendorRegistrationDto vendorRegistrationDto = new VendorRegistrationDto();
		vendorRegistrationDto.setVendorKycId(1);
		vendorRegistrationDto.setContactName("John Doe");
		vendorRegistrationDto.setEmailId("john@example.com");
		vendorRegistrationDto.setTradeName("Legal Name");
		vendorRegistrationDto.setMobileNo("1234567890");

		VendorKYC existingVendorKYC = new VendorKYC();
		existingVendorKYC.setVendorKycId(1);
		existingVendorKYC.setContactName("John Doe");
		existingVendorKYC.setEmailId("john@example.com");
		existingVendorKYC.setLeagalName("Legal name");
		existingVendorKYC.setMobileNo("899876655");

		when(vendorKYCRepository.findById(existingVendorKYC.getVendorKycId())).thenReturn(Optional.empty());
		vendorKYCService.updateVendorRegistration(vendorRegistrationDto);
		when(vendorKYCRepository.findById(existingVendorKYC.getVendorKycId()))
				.thenReturn(Optional.of(existingVendorKYC));
		when(vendorKYCRepository.save(any(VendorKYC.class))).thenReturn(existingVendorKYC);

		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCService
				.updateVendorRegistration(vendorRegistrationDto);

		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		EnumMap<ApiResponse, Object> responseMap = responseEntity.getBody();
		assertNotNull(responseMap);

		boolean success = (boolean) responseMap.get(ApiResponse.SUCCESS);
		assertTrue(success);

	}

	@Test
    void testUpdateVendorRegistration_Exception() {
        when(vendorKYCRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(CRMException.class, () -> {
            vendorKYCService.updateVendorRegistration(new VendorRegistrationDto());
        });

    }

	@Test
	void testSaveVendorRegistration_Success() {
		VendorRegistrationDto vendorRegistrationDto = new VendorRegistrationDto();
		vendorRegistrationDto.setContactName("contact name");
		vendorRegistrationDto.setEmailId("email@gmail.com");
		vendorRegistrationDto.setTradeName("nikita");
		vendorRegistrationDto.setMobileNo("897765556");
		vendorRegistrationDto.setStatus(false);
		when(vendorKYCRepository.save(any())).thenReturn(new VendorKYC());

		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCService
				.saveVendorRegistration(vendorRegistrationDto);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue((boolean) responseEntity.getBody().get(ApiResponse.SUCCESS));
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));
	}

	@Test
    void testsaveVendorRegistration_Exception() {
        when(vendorKYCRepository.save(any())).thenReturn(Optional.empty());

        assertThrows(CRMException.class, () -> {
            vendorKYCService.saveVendorRegistration(new VendorRegistrationDto());
        });

    }

	@Test
    void testsaveVendorKYCForm_Exception() {
    	when(vendorKYCRepository.findById(anyInt())).thenReturn(null);
    		vendorKYCService.saveVendorKYCForm(new VendorKYCDto());
    }

	@Test
	void testuploadDocument() {
		VendorDocumentAttachmentUploadDTO vendorDocumentAttachmentUpload = new VendorDocumentAttachmentUploadDTO();
		vendorDocumentAttachmentUpload.setAttachmentId(1);
		vendorDocumentAttachmentUpload.setDocumentAttachment("document");
		vendorDocumentAttachmentUpload.setFileName("name");
		vendorDocumentAttachmentUpload.setFileType("fileType");

		when(vendorDocumentAttachmentRepository.save(any())).thenReturn(new VendorDocumentAttachment());

		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCService
				.uploadDocument(vendorDocumentAttachmentUpload);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue((boolean) responseEntity.getBody().get(ApiResponse.SUCCESS));
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));
	}

	@Test
	void saveVendorEmailTest() {
		VendorOnboardingMailBoxDTO vendorOnboardingMailBoxDTO = new VendorOnboardingMailBoxDTO();
		VendorKYCNewDTO vendorKYCNewDTO = new VendorKYCNewDTO();
		vendorKYCNewDTO.setVendorKycId(1);
		vendorOnboardingMailBoxDTO.setVendorKYC(vendorKYCNewDTO);
		vendorOnboardingMailBoxDTO.setMailboxId(1);
		when(mailBoxRepo.findByVendoronboardingId(anyInt())).thenReturn(null);

		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCService
				.saveVendoremail(vendorOnboardingMailBoxDTO);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));

	}

	@Test
	void saveVendorEmailTest1() {
		VendorOnboardingMailBoxDTO vendorOnboardingMailBoxDTO = new VendorOnboardingMailBoxDTO();
		VendorKYCNewDTO vendorKYCNewDTO = new VendorKYCNewDTO();
		vendorKYCNewDTO.setVendorKycId(1);
		vendorOnboardingMailBoxDTO.setVendorKYC(vendorKYCNewDTO);
		vendorOnboardingMailBoxDTO.setMailboxId(1);
		VendorOnboardingMailBox vendorOnboardingMailBox = new VendorOnboardingMailBox();
		when(mailBoxRepo.findByVendoronboardingId(anyInt())).thenReturn(vendorOnboardingMailBox);

		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCService
				.saveVendoremail(vendorOnboardingMailBoxDTO);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));

	}

	@Test
	void savegetSavedEmail() {
		VendorKYC vendorkyc = new VendorKYC();
		vendorkyc.setVendorKycId(1);
		VendorOnboardingMailBox vendorOnboardingMailBox = new VendorOnboardingMailBox();
		vendorOnboardingMailBox.setMailboxId(1);
		vendorOnboardingMailBox.setEmailBody("mail body");
		vendorOnboardingMailBox.setVendorKYC(vendorkyc);
		VendorKYCNewDTO vendorKYCNewDTO = new VendorKYCNewDTO();
		vendorKYCNewDTO.setVendorKycId(1);
		when(mailBoxRepo.findByVendoronboardingId(anyInt())).thenReturn(vendorOnboardingMailBox);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = vendorKYCService.getSavedEmail(anyInt());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));

	}

	@Test
	void testGetPdfFile_Success() throws IOException {
		String id = Base64.getEncoder().encodeToString("123".getBytes());
		String docId = Base64.getEncoder().encodeToString("456".getBytes());
		when(vendorKYCRepository.findById(123)).thenReturn(Optional.of(existingVendorKYC));
		MockHttpServletResponse response = new MockHttpServletResponse();
		vendorKYCService.getPdfFile(response, id, docId);
		verify(vendorKYCRepository).findById(123);
		assertEquals("application/pdf", response.getContentType());
		assertEquals(200, response.getStatus());
		byte[] expectedBytes = Base64.getDecoder().decode("JVBERi0xLjQKJeLjz9MNCjEgMCBvYmoKMiAwIG9iag==");
		assertArrayEquals(expectedBytes, response.getContentAsByteArray());
	}

	@Test
	void testGetBugImage() throws IOException {
		String imageName = "rntlogo.svg";
		String base64EncodedImage = "data:image/jpg;base64,JVBERi0xLjQKJeLjz9MNCjEgMCBvYmoKMiAwIG9iag==";
		Map<String, Object> bugImage = new HashMap<>();
		bugImage.put("DATA", base64EncodedImage);
		when(bucketUtil.getBugImage(imageName)).thenReturn(bugImage);
		MockHttpServletResponse response = new MockHttpServletResponse();
		vendorKYCService.getBugImage(imageName, response);
		verify(bucketUtil).getBugImage(imageName);
		assertEquals("image/svg+xml", response.getContentType());
		assertEquals(200, response.getStatus());
		byte[] expectedBytes = Base64.getDecoder().decode("JVBERi0xLjQKJeLjz9MNCjEgMCBvYmoKMiAwIG9iag==");
		assertArrayEquals(expectedBytes, response.getContentAsByteArray());
	}

}
