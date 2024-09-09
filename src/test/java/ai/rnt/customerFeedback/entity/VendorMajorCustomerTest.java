package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class VendorMajorCustomerTest {

	@Test
	void test() {
		VendorMajorCustomer vendorMajorCustomer = new VendorMajorCustomer();

		vendorMajorCustomer.setMajorCustomerId(1);
		vendorMajorCustomer.setMajorCustomer("SampleMajorCustomer");
		VendorKYC vendorKYC = new VendorKYC();
		vendorMajorCustomer.setVendorKYC(vendorKYC);
		assertEquals(1, vendorMajorCustomer.getMajorCustomerId());
		assertEquals("SampleMajorCustomer", vendorMajorCustomer.getMajorCustomer());
		assertNotNull(vendorMajorCustomer.getVendorKYC());

		vendorMajorCustomer.setCreatedBy(1);
		vendorMajorCustomer.setCreatedDate(LocalDateTime.now());
		vendorMajorCustomer.setUpdatedBy(2);
		vendorMajorCustomer.setUpdatedDate(LocalDateTime.now());
		vendorMajorCustomer.setDeletedBy(3);
		vendorMajorCustomer.setDeletedDate(LocalDateTime.now());

		assertEquals(1, vendorMajorCustomer.getCreatedBy());
		assertNotNull(vendorMajorCustomer.getCreatedDate());
		assertEquals(2, vendorMajorCustomer.getUpdatedBy());
		assertNotNull(vendorMajorCustomer.getUpdatedDate());
		assertEquals(3, vendorMajorCustomer.getDeletedBy());
		assertNotNull(vendorMajorCustomer.getDeletedDate());
	}

}
