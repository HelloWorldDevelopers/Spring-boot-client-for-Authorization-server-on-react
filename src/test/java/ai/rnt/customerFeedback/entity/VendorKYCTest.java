package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class VendorKYCTest {

	@Test
	void testVendorKYCEntity() {
		VendorKYC vendorKYC = new VendorKYC();

		vendorKYC.setVendorKycId(1);
		VenderBankDetails venderBankDetails = new VenderBankDetails();
		vendorKYC.setVenderBankDetails(venderBankDetails);
		GSTDetails gSTDetails1 = new GSTDetails();
		GSTDetails gSTDetails2 = new GSTDetails();
		VendorServiceProvider vendorServiceProvider1 = new VendorServiceProvider();
		VendorServiceProvider vendorServiceProvider2 = new VendorServiceProvider();
		VendorMajorCustomer vendorMajorCustomer1 = new VendorMajorCustomer();
		VendorMajorCustomer vendorMajorCustomer2 = new VendorMajorCustomer();
		VendorCoreGoodServices vendorCoreGoodServices1 = new VendorCoreGoodServices();
		VendorCoreGoodServices vendorCoreGoodServices2 = new VendorCoreGoodServices();
		VendorDocumentAttachment vendorDocumentAttachment1 = new VendorDocumentAttachment();
		VendorDocumentAttachment vendorDocumentAttachment2 = new VendorDocumentAttachment();
		vendorKYC.setVendorDocumentAttachmentList(Arrays.asList(vendorDocumentAttachment1, vendorDocumentAttachment2));
		vendorKYC.setGstdetailsList(Arrays.asList(gSTDetails1, gSTDetails2));
		vendorKYC.setVendorServiceProviderList(Arrays.asList(vendorServiceProvider1, vendorServiceProvider2));
		vendorKYC.setVendorMajorCustomerList(Arrays.asList(vendorMajorCustomer1, vendorMajorCustomer2));
		vendorKYC.setVendorCoreGoodServicesList(Arrays.asList(vendorCoreGoodServices1, vendorCoreGoodServices2));

		vendorKYC.setTradeName("SampleTradeName");
		vendorKYC.setLeagalName("SampleLegalName");
		vendorKYC.setTelephoneNo("8765436788");
		vendorKYC.setCinId("SampleCinId");
		vendorKYC.setContactName("SampleContactName");
		vendorKYC.setContactPosition("SampleContactPosition");
		vendorKYC.setEmailId("SampleEmailId");
		vendorKYC.setMobileNo("SampleMobileNo");
		vendorKYC.setAlternateNo("SampleAlternateNo");
		vendorKYC.setIsUnderTaxExemptions("Yes");
		vendorKYC.setIsUnderGst("No");
		vendorKYC.setDeclName("SampleDeclName");
		vendorKYC.setDeclDesignation("SampleDeclDesignation");
		vendorKYC.setOnboardingDate(LocalDate.of(2022, 3, 5));
		vendorKYC.setParseDate("SampleParseDate");
		vendorKYC.setStatus(true);

		vendorKYC.setWebsite("website");
		vendorKYC.setBillingAddressLine1("address Billing");
		vendorKYC.setBillingAddressLine2("address Billing");
		vendorKYC.setBillingCity("city");
		vendorKYC.setBillingState("Satere");
		vendorKYC.setBillingZipCode("1233");
		vendorKYC.setShippingAddressLine1("adcxdcwc wdcx");
		vendorKYC.setShippingAddressLine2("adcxdcwc wdcx");
		vendorKYC.setShippingcountryMaster(new CountryMaster());
		vendorKYC.setShippingCity("citt");
		vendorKYC.setShippingState("wecwc");
		vendorKYC.setShippingZipCode("234567");
		vendorKYC.setSpocName("name");
		vendorKYC.setSpocTelNo("8766543221");
		vendorKYC.setSpocMobileNo("7898765434");
		vendorKYC.setSpocEmail("email.com");
		vendorKYC.setEscalationName("cecec");
		vendorKYC.setEscalationEmail("email.com");
		vendorKYC.setEscalationMobile("123445132");
		vendorKYC.setEscalationTel("123445132");
		vendorKYC.setSalesName("nijwev wefv");
		vendorKYC.setSalesEmail("email.com");
		vendorKYC.setSalesMobileNo("89765432");
		vendorKYC.setSalesTelNo("8877665558");
		vendorKYC.setAccountsName("wcwe wcwc");
		vendorKYC.setAccountsTelNo("8776655544");
		vendorKYC.setAccountsMobileNo("7654567887");
		vendorKYC.setAccountsEmail("sdcwcwc@gmail.com");
		vendorKYC.setCompanyType("nijjcqq");
		vendorKYC.setIsUnderMsme("Yes");
		vendorKYC.setFilledDate("17-07-2024");
		vendorKYC.setShippingcountryMaster(new CountryMaster());
		vendorKYC.setBillingcountryMaster(new CountryMaster());
		assertEquals(1, vendorKYC.getVendorKycId());
		assertNotNull(vendorKYC.getVenderBankDetails());
		assertNotNull(vendorKYC.getVendorServiceProviderList());
		assertNotNull(vendorKYC.getVendorMajorCustomerList());
		assertNotNull(vendorKYC.getShippingcountryMaster());
		assertNotNull(vendorKYC.getBillingcountryMaster());
		assertEquals("Yes", vendorKYC.getIsUnderMsme());
		assertEquals("17-07-2024", vendorKYC.getFilledDate());

		assertEquals(2, vendorKYC.getVendorDocumentAttachmentList().size());
		assertEquals("SampleTradeName", vendorKYC.getTradeName());
		assertEquals("SampleLegalName", vendorKYC.getLeagalName());
		assertEquals("8765436788", vendorKYC.getTelephoneNo());
		assertEquals("SampleCinId", vendorKYC.getCinId());
		assertEquals("SampleContactName", vendorKYC.getContactName());
		assertEquals("SampleContactPosition", vendorKYC.getContactPosition());
		assertEquals("SampleEmailId", vendorKYC.getEmailId());
		assertEquals("SampleMobileNo", vendorKYC.getMobileNo());
		assertEquals("SampleAlternateNo", vendorKYC.getAlternateNo());
		assertEquals("Yes", vendorKYC.getIsUnderTaxExemptions());
		assertEquals("No", vendorKYC.getIsUnderGst());
		assertEquals("SampleDeclName", vendorKYC.getDeclName());
		assertEquals("SampleDeclDesignation", vendorKYC.getDeclDesignation());
		assertEquals(LocalDate.of(2022, 3, 5), vendorKYC.getOnboardingDate());
		assertEquals("SampleParseDate", vendorKYC.getParseDate());
		assert (vendorKYC.isStatus());
		assertEquals("website", vendorKYC.getWebsite());
		assertEquals("address Billing", vendorKYC.getBillingAddressLine1());
		assertEquals("address Billing", vendorKYC.getBillingAddressLine2());
		assertEquals("city", vendorKYC.getBillingCity());
		assertEquals("Satere", vendorKYC.getBillingState());
		assertEquals("1233", vendorKYC.getBillingZipCode());
		assertEquals("1233", vendorKYC.getBillingZipCode());
		assertEquals("adcxdcwc wdcx", vendorKYC.getShippingAddressLine1());
		assertEquals("adcxdcwc wdcx", vendorKYC.getShippingAddressLine2());
		assertEquals("wecwc", vendorKYC.getShippingState());
		assertEquals("234567", vendorKYC.getShippingZipCode());
		assertEquals("citt", vendorKYC.getShippingCity());
		assertEquals("name", vendorKYC.getSpocName());
		assertEquals("email.com", vendorKYC.getSpocEmail());
		assertEquals("8766543221", vendorKYC.getSpocTelNo());
		assertEquals("7898765434", vendorKYC.getSpocMobileNo());
		assertEquals("cecec", vendorKYC.getEscalationName());
		assertEquals("email.com", vendorKYC.getEscalationEmail());
		assertEquals("123445132", vendorKYC.getEscalationMobile());
		assertEquals("123445132", vendorKYC.getEscalationTel());

		assertEquals("nijwev wefv", vendorKYC.getSalesName());
		assertEquals("email.com", vendorKYC.getSalesEmail());
		assertEquals("89765432", vendorKYC.getSalesMobileNo());
		assertEquals("8877665558", vendorKYC.getSalesTelNo());

		assertEquals("wcwe wcwc", vendorKYC.getAccountsName());
		assertEquals("sdcwcwc@gmail.com", vendorKYC.getAccountsEmail());
		assertEquals("7654567887", vendorKYC.getAccountsMobileNo());
		assertEquals("8776655544", vendorKYC.getAccountsTelNo());
		assertEquals("nijjcqq", vendorKYC.getCompanyType());

		vendorKYC.setCreatedBy(1);
		vendorKYC.setCreatedDate(LocalDateTime.now());
		vendorKYC.setUpdatedBy(2);
		vendorKYC.setUpdatedDate(LocalDateTime.now());
		vendorKYC.setDeletedBy(3);
		vendorKYC.setDeletedDate(LocalDateTime.now());

		assertEquals(1, vendorKYC.getCreatedBy());
		assertNotNull(vendorKYC.getCreatedDate());
		assertEquals(2, vendorKYC.getUpdatedBy());
		assertNotNull(vendorKYC.getUpdatedDate());
		assertEquals(3, vendorKYC.getDeletedBy());
		assertNotNull(vendorKYC.getDeletedDate());
	}

}
