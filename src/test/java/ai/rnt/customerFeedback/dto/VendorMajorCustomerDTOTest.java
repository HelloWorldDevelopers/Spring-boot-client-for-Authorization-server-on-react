package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorMajorCustomerDTOTest {

	@Test
	void test() {
		VendorMajorCustomerDTO vendorMajorCustomerDTO = new VendorMajorCustomerDTO();
		vendorMajorCustomerDTO.setMajorCustomer("ndc ec");
		vendorMajorCustomerDTO.setMajorCustomerId(1);

		assertEquals(1, vendorMajorCustomerDTO.getMajorCustomerId());
		assertEquals("ndc ec", vendorMajorCustomerDTO.getMajorCustomer());
	}

}
