package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class VendorServiceProviderTest {

	@Test
	void test() {
		VendorServiceProvider vendorServiceProvider = new VendorServiceProvider();

		vendorServiceProvider.setSvcsProviderId(1);
		vendorServiceProvider.setDescOfServices("asfvafdv");
		vendorServiceProvider.setServiceAccgCode("wc1234");
		vendorServiceProvider.setGstRateExpected("2.9%");
		vendorServiceProvider.setIsCompSchemeUnderGst("Yes");
		vendorServiceProvider.setVendorKYC(new VendorKYC());

		assertEquals(1, vendorServiceProvider.getSvcsProviderId());
		assertEquals("asfvafdv", vendorServiceProvider.getDescOfServices());
		assertEquals("wc1234", vendorServiceProvider.getServiceAccgCode());
		assertEquals("2.9%", vendorServiceProvider.getGstRateExpected());
		assertEquals("Yes", vendorServiceProvider.getIsCompSchemeUnderGst());
		assertNotNull(vendorServiceProvider.getVendorKYC());
		vendorServiceProvider.setCreatedBy(1);
		vendorServiceProvider.setCreatedDate(LocalDateTime.now());
		vendorServiceProvider.setUpdatedBy(2);
		vendorServiceProvider.setUpdatedDate(LocalDateTime.now());
		vendorServiceProvider.setDeletedBy(3);
		vendorServiceProvider.setDeletedDate(LocalDateTime.now());

		assertEquals(1, vendorServiceProvider.getCreatedBy());
		assertNotNull(vendorServiceProvider.getCreatedDate());
		assertEquals(2, vendorServiceProvider.getUpdatedBy());
		assertNotNull(vendorServiceProvider.getUpdatedDate());
		assertEquals(3, vendorServiceProvider.getDeletedBy());
		assertNotNull(vendorServiceProvider.getDeletedDate());
	}

}
