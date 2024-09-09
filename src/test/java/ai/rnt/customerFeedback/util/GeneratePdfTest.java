package ai.rnt.customerFeedback.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.CountryMasterDTO;
import ai.rnt.customerFeedback.dto.GSTDetailsDto;
import ai.rnt.customerFeedback.dto.VenderBankDetailsDto;
import ai.rnt.customerFeedback.dto.VendorCoreGoodServicesDTO;
import ai.rnt.customerFeedback.dto.VendorDocumentAttachmentDto;
import ai.rnt.customerFeedback.dto.VendorDocumentMasterDto;
import ai.rnt.customerFeedback.dto.VendorKYCDto;
import ai.rnt.customerFeedback.dto.VendorMajorCustomerDTO;
import ai.rnt.customerFeedback.dto.VendorServiceProviderDTO;
import ai.rnt.customerFeedback.dto.VendorStateGSTCodeDTO;
import ai.rnt.customerFeedback.repository.VendorDocumentAttachmentRepository;

@ExtendWith(MockitoExtension.class)
class GeneratePdfTest {
	@InjectMocks
	GeneratePdf1 generatePdf;

	@Mock
	private VendorDocumentAttachmentRepository vendorDocumentAttachmentRepository;

	@Test
	void testGeneratePdfMethod() throws IOException {
		Integer expectedVendorKycId = 1;
		VenderBankDetailsDto venderBankDetails = new VenderBankDetailsDto();
		List<GSTDetailsDto> expectedGSTDetails = new ArrayList<>();
		Integer expectedGstDtlId = 1;
		String expectedGstNo = "GST123";
		String expectedGstAddress = "123 Main Street";
		VendorStateGSTCodeDTO vendorStateGSTCodeDTO = new VendorStateGSTCodeDTO();
		vendorStateGSTCodeDTO.setState("maharashtra");
		vendorStateGSTCodeDTO.setStateGstCode("01");
		vendorStateGSTCodeDTO.setStateGstCodeId(1);
		GSTDetailsDto gstDetailsDto = new GSTDetailsDto();
		gstDetailsDto.setGstDtlId(expectedGstDtlId);
		gstDetailsDto.setGstNo(expectedGstNo);
		gstDetailsDto.setGstAddress(expectedGstAddress);
		gstDetailsDto.setPanNo("PAN12345");
		gstDetailsDto.setVendorStateGSTCode(vendorStateGSTCodeDTO);
		expectedGSTDetails.add(gstDetailsDto);
		String expectedAlternateNo = "9876543210";
		String expectedContactPosition = "Manager";
		String expectedTradeName = "ABC Traders";
		String expectedContactName = "John Doe";
		String expectedEmailId = "john.doe@example.com";
		String expectedMobileNo = "9876543210";
		String expectedLeagalName = "ABC Legal";
		String expectedTelephoneNo = "9876543210";
		String expectedCinId = "CIN123";
		String expectedIsUnderTaxExemptions = "Yes";
		String expectedIsUnderGst = "Yes";
		String expectedDeclName = "John Doe";
		String expectedDeclDesignation = "Manager";
		LocalDate expectedOnboardingDate = LocalDate.of(2022, 3, 1);
		String expectedParseDate = "2022-03-01";
		boolean expectedStatus = true;

		VendorServiceProviderDTO vendorServiceProviderDTO = new VendorServiceProviderDTO();
		vendorServiceProviderDTO.setSvcsProviderId(1);
		vendorServiceProviderDTO.setDescOfServices("qwcc qcd");
		vendorServiceProviderDTO.setServiceAccgCode("1234ABC");
		vendorServiceProviderDTO.setGstRateExpected("0.9%");
		vendorServiceProviderDTO.setIsCompSchemeUnderGst("Yes");
		VendorServiceProviderDTO vendorServiceProviderDTO2 = new VendorServiceProviderDTO();
		vendorServiceProviderDTO2.setSvcsProviderId(1);
		vendorServiceProviderDTO2.setDescOfServices("qwcc qcd");
		vendorServiceProviderDTO2.setServiceAccgCode("1234ABC");
		vendorServiceProviderDTO2.setGstRateExpected("0.9%");
		vendorServiceProviderDTO2.setIsCompSchemeUnderGst("Yes");
		VendorMajorCustomerDTO vendorMajorCustomerDTO2 = new VendorMajorCustomerDTO();
		VendorMajorCustomerDTO vendorMajorCustomerDTO = new VendorMajorCustomerDTO();
		vendorMajorCustomerDTO.setMajorCustomer("ndc ec");
		vendorMajorCustomerDTO.setMajorCustomerId(1);
		vendorMajorCustomerDTO2.setMajorCustomer("ndc ec");
		vendorMajorCustomerDTO2.setMajorCustomerId(1);
		VendorCoreGoodServicesDTO vendorCoreGoodServicesDTO = new VendorCoreGoodServicesDTO();
		vendorCoreGoodServicesDTO.setServiceId(1);
		vendorCoreGoodServicesDTO.setCoreGoodsService("cwefcefc");
		VendorCoreGoodServicesDTO vendorCoreGoodServicesDTO2 = new VendorCoreGoodServicesDTO();
		vendorCoreGoodServicesDTO2.setServiceId(1);
		vendorCoreGoodServicesDTO2.setCoreGoodsService("cwefcefc");
		LocalDateTime dateTime = LocalDateTime.now();
		venderBankDetails.setBankName("SampleBankName");
		venderBankDetails.setBranch("SampleBranch");
		venderBankDetails.setBeneficiaryAccName("SampleBeneficiaryAccName");
		venderBankDetails.setBankAddressLine1("SampleBankAddress");
		venderBankDetails.setBankAddressLine2("SampleBankAddress");
		venderBankDetails.setCity("city");
		venderBankDetails.setState("state");
		CountryMasterDTO country = new CountryMasterDTO();
		venderBankDetails.setCountryMaster(country);
		venderBankDetails.setZipCode("89345234");
		venderBankDetails.setIntermediaryBankDtl("SampleIntermediaryBankDtl");
		venderBankDetails.setAccCurrency("AMD ֏ - Armenian dram");
		venderBankDetails.setIfscCode("SampleIFSCCode");
		venderBankDetails.setSwiftCode("SampleSwiftCode");
		venderBankDetails.setCompanyTurnover("78654322");
		venderBankDetails.setIsItrFiled("Yes");
		VendorKYCDto vendorKYCDto = new VendorKYCDto();

		vendorKYCDto.setVendorKycId(expectedVendorKycId);
		vendorKYCDto.setVendorServiceProviderList(Arrays.asList(vendorServiceProviderDTO, vendorServiceProviderDTO2));
		vendorKYCDto.setVendorMajorCustomerList(Arrays.asList(vendorMajorCustomerDTO, vendorMajorCustomerDTO2));
		vendorKYCDto
				.setVendorCoreGoodServicesList(Arrays.asList(vendorCoreGoodServicesDTO, vendorCoreGoodServicesDTO2));
		vendorKYCDto.setVenderBankDetails(venderBankDetails);
		vendorKYCDto.setGstdetailsList(expectedGSTDetails);
		vendorKYCDto.setAlternateNo(expectedAlternateNo);
		vendorKYCDto.setContactPosition(expectedContactPosition);
		vendorKYCDto.setTradeName(expectedTradeName);
		vendorKYCDto.setContactName(expectedContactName);
		vendorKYCDto.setEmailId(expectedEmailId);
		vendorKYCDto.setMobileNo(expectedMobileNo);
		vendorKYCDto.setLeagalName(expectedLeagalName);
		vendorKYCDto.setTelephoneNo(expectedTelephoneNo);
		vendorKYCDto.setCinId(expectedCinId);
		vendorKYCDto.setIsUnderTaxExemptions(expectedIsUnderTaxExemptions);
		vendorKYCDto.setIsUnderGst(expectedIsUnderGst);
		vendorKYCDto.setDeclName(expectedDeclName);
		vendorKYCDto.setDeclDesignation(expectedDeclDesignation);
		vendorKYCDto.setOnboardingDate(expectedOnboardingDate);
		vendorKYCDto.setParseDate(expectedParseDate);
		vendorKYCDto.setStatus(expectedStatus);
		vendorKYCDto.setFilledDate("07-09-2024");
		vendorKYCDto.setCreatedDate(dateTime);
		vendorKYCDto.setIsUnderMsme(expectedIsUnderGst);

		vendorKYCDto.setBillingcountryMaster(new CountryMasterDTO());
		vendorKYCDto.setShippingcountryMaster(new CountryMasterDTO());
		vendorKYCDto.setBillingAddressLine1("address");
		vendorKYCDto.setBillingAddressLine2("address");
		vendorKYCDto.setBillingCity("address");
		vendorKYCDto.setBillingState("address");
		vendorKYCDto.setBillingZipCode("address");

		vendorKYCDto.setShippingAddressLine1("shipping address");
		vendorKYCDto.setShippingAddressLine2("address line 2");
		vendorKYCDto.setShippingCity("address");
		vendorKYCDto.setShippingState("address");
		vendorKYCDto.setShippingZipCode("address");

		vendorKYCDto.setSpocName("name");
		vendorKYCDto.setSpocTelNo("8766543221");
		vendorKYCDto.setSpocMobileNo("7898765434");
		vendorKYCDto.setSpocEmail("email.com");
		vendorKYCDto.setEscalationName("cecec");
		vendorKYCDto.setEscalationEmail("email.com");
		vendorKYCDto.setEscalationMobile("123445132");
		vendorKYCDto.setEscalationTel("123445132");
		vendorKYCDto.setSalesName("nijwev wefv");
		vendorKYCDto.setSalesEmail("email.com");
		vendorKYCDto.setSalesMobileNo("89765432");
		vendorKYCDto.setSalesTelNo("8877665558");
		vendorKYCDto.setAccountsName("wcwe wcwc");
		vendorKYCDto.setAccountsTelNo("8776655544");
		vendorKYCDto.setAccountsMobileNo("7654567887");
		vendorKYCDto.setAccountsEmail("sdcwcwc@gmail.com");

		List<VendorDocumentAttachmentDto> vendorDocumentAttachmentList = new ArrayList<>();
		VendorDocumentAttachmentDto vendorDocumentAttachmentDto = new VendorDocumentAttachmentDto();
		vendorDocumentAttachmentDto.setAttachmentId(1); // Set attachmentId mock value
		vendorDocumentAttachmentDto.setDocumentAttachment("Base64EncodedDocument"); // Set documentAttachment mock value
		vendorDocumentAttachmentDto.setFileType("pdf"); // Set fileType mock value
		vendorDocumentAttachmentDto.setFileName("passport.pdf"); // Set fileName mock value

		VendorDocumentMasterDto vendorDocumentMasterDto = new VendorDocumentMasterDto();
		VendorDocumentMasterDto vendorDocumentMasterDto1 = new VendorDocumentMasterDto();
		VendorDocumentMasterDto vendorDocumentMasterDto2 = new VendorDocumentMasterDto();
		VendorDocumentMasterDto vendorDocumentMasterDto3 = new VendorDocumentMasterDto();
		VendorDocumentMasterDto vendorDocumentMasterDto4 = new VendorDocumentMasterDto();
		VendorDocumentMasterDto vendorDocumentMasterDto5 = new VendorDocumentMasterDto();
		VendorDocumentMasterDto vendorDocumentMasterDto6 = new VendorDocumentMasterDto();
		VendorDocumentMasterDto vendorDocumentMasterDto7 = new VendorDocumentMasterDto();
		vendorDocumentMasterDto.setDocumentId(1);
		vendorDocumentMasterDto.setDocumentType("Company Incorporation Document");
		vendorDocumentAttachmentDto.setVendorDocumentMaster(vendorDocumentMasterDto);
		vendorDocumentMasterDto1.setDocumentId(2);
		vendorDocumentMasterDto1.setDocumentType("Cancelled Cheque/ Bank Letter Statement");
		vendorDocumentAttachmentDto.setVendorDocumentMaster(vendorDocumentMasterDto1);

		vendorDocumentMasterDto2.setDocumentId(3);
		vendorDocumentMasterDto2.setDocumentType("Pan Card");
		vendorDocumentAttachmentDto.setVendorDocumentMaster(vendorDocumentMasterDto2);

		vendorDocumentMasterDto3.setDocumentId(4);
		vendorDocumentMasterDto3.setDocumentType("GST Certificate");
		vendorDocumentAttachmentDto.setVendorDocumentMaster(vendorDocumentMasterDto3);

		vendorDocumentMasterDto4.setDocumentId(5);
		vendorDocumentMasterDto4.setDocumentType("Last 3 Month Bank Statement");
		vendorDocumentAttachmentDto.setVendorDocumentMaster(vendorDocumentMasterDto4);

		vendorDocumentMasterDto5.setDocumentId(7);
		vendorDocumentMasterDto5.setDocumentType("Tax Exemptions Document");
		vendorDocumentAttachmentDto.setVendorDocumentMaster(vendorDocumentMasterDto5);

		vendorDocumentMasterDto6.setDocumentId(8);
		vendorDocumentMasterDto6.setDocumentType("MSME Certificate");
		vendorDocumentAttachmentDto.setVendorDocumentMaster(vendorDocumentMasterDto6);

		vendorDocumentMasterDto7.setDocumentId(9);
		vendorDocumentMasterDto7.setDocumentType("ITR File");
		vendorDocumentAttachmentDto.setVendorDocumentMaster(vendorDocumentMasterDto7);

		vendorDocumentAttachmentList.add(vendorDocumentAttachmentDto);
		vendorKYCDto.setVendorDocumentAttachmentList(vendorDocumentAttachmentList);
		try {
			Base64.getEncoder().encodeToString(vendorKYCDto.getVendorKycId().toString().getBytes());
		} catch (Exception e) {
		}

		ResponseEntity<ByteArrayResource> response = generatePdf.generatePdfMethod(vendorKYCDto);

		assert response.getStatusCode() == HttpStatus.OK;

	}
}
