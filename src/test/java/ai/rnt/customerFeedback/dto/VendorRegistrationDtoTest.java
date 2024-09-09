package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VendorRegistrationDtoTest {

	@Test
	void testVendorRegistrationDto() {
		Integer expectedVendorKycId = 1;
		String expectedContactName = "John Doe";
		String expectedLegalName = "ABC Corporation";
		String expectedEmailId = "john.doe@example.com";
		String expectedMobileNo = "9876543210";
		VendorRegistrationDto vendorRegistrationDto = new VendorRegistrationDto();
		vendorRegistrationDto.setVendorKycId(expectedVendorKycId);
		vendorRegistrationDto.setContactName(expectedContactName);
		vendorRegistrationDto.setTradeName(expectedLegalName);
		vendorRegistrationDto.setEmailId(expectedEmailId);
		vendorRegistrationDto.setMobileNo(expectedMobileNo);
		assertEquals(expectedVendorKycId, vendorRegistrationDto.getVendorKycId());
		assertEquals(expectedContactName, vendorRegistrationDto.getContactName());
		assertEquals(expectedLegalName, vendorRegistrationDto.getTradeName());
		assertEquals(expectedEmailId, vendorRegistrationDto.getEmailId());
		assertEquals(expectedMobileNo, vendorRegistrationDto.getMobileNo());

	}
}
