package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class VendorCoreGoodServicesTest {

	@Test
	void test() {
		VendorCoreGoodServices vendorCoreGoodServices = new VendorCoreGoodServices();

		vendorCoreGoodServices.setServiceId(1);

		vendorCoreGoodServices.setCoreGoodsService("SampleMajorCustomer");
		VendorKYC vendorKYC = new VendorKYC();
		vendorCoreGoodServices.setVendorKYC(vendorKYC);
		assertEquals(1, vendorCoreGoodServices.getServiceId());
		assertEquals("SampleMajorCustomer", vendorCoreGoodServices.getCoreGoodsService());
		assertNotNull(vendorCoreGoodServices.getVendorKYC());

		vendorCoreGoodServices.setCreatedBy(1);
		vendorCoreGoodServices.setCreatedDate(LocalDateTime.now());
		vendorCoreGoodServices.setUpdatedBy(2);
		vendorCoreGoodServices.setUpdatedDate(LocalDateTime.now());
		vendorCoreGoodServices.setDeletedBy(3);
		vendorCoreGoodServices.setDeletedDate(LocalDateTime.now());

		assertEquals(1, vendorCoreGoodServices.getCreatedBy());
		assertNotNull(vendorCoreGoodServices.getCreatedDate());
		assertEquals(2, vendorCoreGoodServices.getUpdatedBy());
		assertNotNull(vendorCoreGoodServices.getUpdatedDate());
		assertEquals(3, vendorCoreGoodServices.getDeletedBy());
		assertNotNull(vendorCoreGoodServices.getDeletedDate());
	}

}
