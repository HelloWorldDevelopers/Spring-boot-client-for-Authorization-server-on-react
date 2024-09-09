package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CountryMasterTest {

	@Test
	void test() {
		CountryMaster countryMaster = new CountryMaster();

		countryMaster.setCountryId(1);
		countryMaster.setCountry("SampleMajorCustomer");
		List<VendorKYC> vendorKYClist = new ArrayList<>();
		VendorKYC vendorKYC1 = new VendorKYC();
		VendorKYC vendorKYC2 = new VendorKYC();
		vendorKYClist.add(vendorKYC2);
		vendorKYClist.add(vendorKYC1);
		countryMaster.setVendorKYCListForBilling(vendorKYClist);
		countryMaster.setVendorKYCListForShipping(vendorKYClist);
		VenderBankDetails vendorbankdetals = new VenderBankDetails();
		List<VenderBankDetails> list = new ArrayList<>();
		list.add(vendorbankdetals);
		countryMaster.setBankaddress(list);

		assertEquals(1, countryMaster.getCountryId());
		assertEquals("SampleMajorCustomer", countryMaster.getCountry());
		assertNotNull(countryMaster.getVendorKYCListForBilling());
		assertNotNull(countryMaster.getVendorKYCListForShipping());
		assertNotNull(countryMaster.getBankaddress());

		countryMaster.setCreatedBy(1);
		countryMaster.setCreatedDate(LocalDateTime.now());
		countryMaster.setUpdatedBy(2);
		countryMaster.setUpdatedDate(LocalDateTime.now());
		countryMaster.setDeletedBy(3);
		countryMaster.setDeletedDate(LocalDateTime.now());

		assertEquals(1, countryMaster.getCreatedBy());
		assertNotNull(countryMaster.getCreatedDate());
		assertEquals(2, countryMaster.getUpdatedBy());
		assertNotNull(countryMaster.getUpdatedDate());
		assertEquals(3, countryMaster.getDeletedBy());
		assertNotNull(countryMaster.getDeletedDate());
	}

}
