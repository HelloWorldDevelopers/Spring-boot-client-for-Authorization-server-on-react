package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorKYCDtoFormTest2 {

	@Test
	void testVendorKYCDtoForm() {
		Integer expectedVendorKycId = 1;
		String expectedContactName = "John Doe";
		String expectedEmailId = "john.doe@example.com";
		String expectedMobileNo = "9876543210";
		String expectedLegalName = "ABC Corporation";

		VendorKYCDtoForm vendorKYCDtoForm = new VendorKYCDtoForm();
		vendorKYCDtoForm.setVendorKycId(expectedVendorKycId);
		vendorKYCDtoForm.setContactName(expectedContactName);
		vendorKYCDtoForm.setEmailId(expectedEmailId);
		vendorKYCDtoForm.setMobileNo(expectedMobileNo);
		vendorKYCDtoForm.setLeagalName(expectedLegalName);

		assertEquals(expectedVendorKycId, vendorKYCDtoForm.getVendorKycId());
		assertEquals(expectedContactName, vendorKYCDtoForm.getContactName());
		assertEquals(expectedEmailId, vendorKYCDtoForm.getEmailId());
		assertEquals(expectedMobileNo, vendorKYCDtoForm.getMobileNo());
		assertEquals(expectedLegalName, vendorKYCDtoForm.getLeagalName());

	}

}
