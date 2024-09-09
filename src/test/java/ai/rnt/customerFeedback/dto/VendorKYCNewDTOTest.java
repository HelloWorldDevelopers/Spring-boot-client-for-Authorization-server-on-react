package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorKYCNewDTOTest {

	@Test
	void test() {
		VendorKYCNewDTO vendor = new VendorKYCNewDTO();
		vendor.setVendorKycId(1);

		assertEquals(1, vendor.getVendorKycId());

	}

}
