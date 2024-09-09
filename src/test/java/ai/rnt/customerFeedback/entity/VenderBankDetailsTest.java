package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class VenderBankDetailsTest {

	@Test
	void testVenderBankDetailsEntity() {
		VenderBankDetails venderBankDetails = new VenderBankDetails();

		venderBankDetails.setVendorBankDtlId(1);
		VendorKYC vendorKYC = new VendorKYC();
		venderBankDetails.setVendorKYC(vendorKYC);
		venderBankDetails.setBankName("SampleBankName");
		venderBankDetails.setBranch("SampleBranch");
		venderBankDetails.setBeneficiaryAccName("SampleBeneficiaryAccName");
		venderBankDetails.setAccountNo(1234567890L);
		venderBankDetails.setBankAddressLine1("SampleBankAddress");
		venderBankDetails.setBankAddressLine2("SampleBankAddress");
		venderBankDetails.setCity("city");
		venderBankDetails.setState("state");
		CountryMaster country = new CountryMaster();
		venderBankDetails.setCountryMaster(country);
		venderBankDetails.setZipCode("89345234");
		venderBankDetails.setIntermediaryBankDtl("SampleIntermediaryBankDtl");
		venderBankDetails.setAccCurrency("USD");
		venderBankDetails.setIfscCode("SampleIFSCCode");
		venderBankDetails.setSwiftCode("SampleSwiftCode");
		venderBankDetails.setCompanyTurnover("78654322");
		venderBankDetails.setIsItrFiled("Yes");
		assertEquals(1, venderBankDetails.getVendorBankDtlId());
		assertNotNull(venderBankDetails.getVendorKYC());
		assertEquals("SampleBankName", venderBankDetails.getBankName());
		assertEquals("SampleBranch", venderBankDetails.getBranch());
		assertEquals("SampleBeneficiaryAccName", venderBankDetails.getBeneficiaryAccName());
		assertEquals(1234567890L, venderBankDetails.getAccountNo());
		assertEquals("SampleBankAddress", venderBankDetails.getBankAddressLine1());
		assertEquals("SampleBankAddress", venderBankDetails.getBankAddressLine2());
		assertEquals("SampleIntermediaryBankDtl", venderBankDetails.getIntermediaryBankDtl());
		assertEquals("USD", venderBankDetails.getAccCurrency());
		assertEquals("SampleIFSCCode", venderBankDetails.getIfscCode());
		assertEquals("SampleSwiftCode", venderBankDetails.getSwiftCode());
		assertEquals("78654322", venderBankDetails.getCompanyTurnover());
		assertEquals("Yes", venderBankDetails.getIsItrFiled());
		assertEquals("city", venderBankDetails.getCity());
		assertEquals("state", venderBankDetails.getState());
		assertEquals("89345234", venderBankDetails.getZipCode());
		assertEquals(country, venderBankDetails.getCountryMaster());

		venderBankDetails.setCreatedBy(1);
		venderBankDetails.setCreatedDate(LocalDateTime.now());
		venderBankDetails.setUpdatedBy(2);
		venderBankDetails.setUpdatedDate(LocalDateTime.now());
		venderBankDetails.setDeletedBy(3);
		venderBankDetails.setDeletedDate(LocalDateTime.now());

		assertEquals(1, venderBankDetails.getCreatedBy());
		assertNotNull(venderBankDetails.getCreatedDate());
		assertEquals(2, venderBankDetails.getUpdatedBy());
		assertNotNull(venderBankDetails.getUpdatedDate());
		assertEquals(3, venderBankDetails.getDeletedBy());
		assertNotNull(venderBankDetails.getDeletedDate());
	}
}
