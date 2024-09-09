package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class VendorKYCDtoTest {

	@Test
	void testVendorKYCDto() {
		Integer expectedVendorKycId = 1;
		List<VendorDocumentAttachmentDto> expectedVendorDocumentAttachmentList = new ArrayList();
		VenderBankDetailsDto expectedVenderBankDetails = new VenderBankDetailsDto();
		List<GSTDetailsDto> expectedGSTDetails = new ArrayList<>();
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
		String expectedIsUnderGst = "No";
		String expectedDeclName = "John Doe";
		String expectedDeclDesignation = "Manager";
		LocalDate expectedOnboardingDate = LocalDate.of(2022, 3, 1);
		String expectedParseDate = "2022-03-01";
		boolean expectedStatus = true;

		VendorServiceProviderDTO vendorServiceProvider1 = new VendorServiceProviderDTO();
		VendorServiceProviderDTO vendorServiceProvider2 = new VendorServiceProviderDTO();

		VendorMajorCustomerDTO vendorMajorCustomerDTO1 = new VendorMajorCustomerDTO();
		VendorMajorCustomerDTO vendorMajorCustomerDTO2 = new VendorMajorCustomerDTO();

		VendorCoreGoodServicesDTO vendorCoreGoodServicesDTO1 = new VendorCoreGoodServicesDTO();
		VendorCoreGoodServicesDTO vendorCoreGoodServicesDTO2 = new VendorCoreGoodServicesDTO();
		LocalDateTime dateTime = LocalDateTime.now();
		VendorKYCDto vendorKYCDto = new VendorKYCDto();
		vendorKYCDto.setVendorKycId(expectedVendorKycId);
		vendorKYCDto.setVendorServiceProviderList(Arrays.asList(vendorServiceProvider1, vendorServiceProvider2));
		vendorKYCDto.setVendorMajorCustomerList(Arrays.asList(vendorMajorCustomerDTO1, vendorMajorCustomerDTO2));
		vendorKYCDto
				.setVendorCoreGoodServicesList(Arrays.asList(vendorCoreGoodServicesDTO1, vendorCoreGoodServicesDTO2));
		vendorKYCDto.setVendorDocumentAttachmentList(expectedVendorDocumentAttachmentList);
		vendorKYCDto.setVenderBankDetails(expectedVenderBankDetails);
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

		vendorKYCDto.setShippingAddressLine1("address");
		vendorKYCDto.setShippingAddressLine2("address");
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

		assertEquals("name", vendorKYCDto.getSpocName());
		assertEquals(expectedIsUnderGst, vendorKYCDto.getIsUnderMsme());
		assertEquals(dateTime, vendorKYCDto.getCreatedDate());
		assertEquals("07-09-2024", vendorKYCDto.getFilledDate());
		assertEquals("email.com", vendorKYCDto.getSpocEmail());
		assertEquals("8766543221", vendorKYCDto.getSpocTelNo());
		assertEquals("7898765434", vendorKYCDto.getSpocMobileNo());
		assertEquals("cecec", vendorKYCDto.getEscalationName());
		assertEquals("email.com", vendorKYCDto.getEscalationEmail());
		assertEquals("123445132", vendorKYCDto.getEscalationMobile());
		assertEquals("123445132", vendorKYCDto.getEscalationTel());

		assertEquals("nijwev wefv", vendorKYCDto.getSalesName());
		assertEquals("email.com", vendorKYCDto.getSalesEmail());
		assertEquals("89765432", vendorKYCDto.getSalesMobileNo());
		assertEquals("8877665558", vendorKYCDto.getSalesTelNo());

		assertEquals("wcwe wcwc", vendorKYCDto.getAccountsName());
		assertEquals("sdcwcwc@gmail.com", vendorKYCDto.getAccountsEmail());
		assertEquals("7654567887", vendorKYCDto.getAccountsMobileNo());
		assertEquals("8776655544", vendorKYCDto.getAccountsTelNo());
		assertNotNull(vendorKYCDto.getVendorServiceProviderList());
		assertNotNull(vendorKYCDto.getShippingcountryMaster());
		assertNotNull(vendorKYCDto.getBillingcountryMaster());

		assertEquals("address", vendorKYCDto.getBillingAddressLine1());
		assertEquals("address", vendorKYCDto.getBillingAddressLine2());
		assertEquals("address", vendorKYCDto.getBillingState());
		assertEquals("address", vendorKYCDto.getBillingCity());
		assertEquals("address", vendorKYCDto.getBillingZipCode());
		assertEquals("address", vendorKYCDto.getShippingAddressLine1());
		assertEquals("address", vendorKYCDto.getShippingAddressLine2());
		assertEquals("address", vendorKYCDto.getShippingCity());
		assertEquals("address", vendorKYCDto.getShippingState());
		assertEquals("address", vendorKYCDto.getShippingZipCode());
		assertEquals(expectedVendorKycId, vendorKYCDto.getVendorKycId());
		assertEquals(expectedVendorDocumentAttachmentList, vendorKYCDto.getVendorDocumentAttachmentList());
		assertEquals(expectedVenderBankDetails, vendorKYCDto.getVenderBankDetails());
		assertEquals(expectedGSTDetails, vendorKYCDto.getGstdetailsList());
		assertEquals(expectedAlternateNo, vendorKYCDto.getAlternateNo());
		assertEquals(expectedContactPosition, vendorKYCDto.getContactPosition());
		assertEquals(expectedTradeName, vendorKYCDto.getTradeName());
		assertEquals(expectedContactName, vendorKYCDto.getContactName());
		assertEquals(expectedEmailId, vendorKYCDto.getEmailId());
		assertEquals(expectedMobileNo, vendorKYCDto.getMobileNo());
		assertEquals(expectedLeagalName, vendorKYCDto.getLeagalName());
		assertEquals(expectedTelephoneNo, vendorKYCDto.getTelephoneNo());
		assertEquals(expectedCinId, vendorKYCDto.getCinId());
		assertEquals(expectedIsUnderTaxExemptions, vendorKYCDto.getIsUnderTaxExemptions());
		assertEquals(expectedIsUnderGst, vendorKYCDto.getIsUnderGst());
		assertEquals(expectedDeclName, vendorKYCDto.getDeclName());
		assertEquals(expectedDeclDesignation, vendorKYCDto.getDeclDesignation());
		assertEquals(expectedOnboardingDate, vendorKYCDto.getOnboardingDate());
		assertEquals(expectedParseDate, vendorKYCDto.getParseDate());
		assertEquals(expectedStatus, vendorKYCDto.isStatus());

	}
}
