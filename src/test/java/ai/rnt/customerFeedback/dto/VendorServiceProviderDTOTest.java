package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorServiceProviderDTOTest {

	@Test
	void test() {
		VendorServiceProviderDTO vendorServiceProviderDTO = new VendorServiceProviderDTO();
		vendorServiceProviderDTO.setSvcsProviderId(1);
		vendorServiceProviderDTO.setDescOfServices("qwcc qcd");
		vendorServiceProviderDTO.setServiceAccgCode("1234ABC");
		vendorServiceProviderDTO.setGstRateExpected("0.9%");
		vendorServiceProviderDTO.setIsCompSchemeUnderGst("Yes");

		assertEquals(1, vendorServiceProviderDTO.getSvcsProviderId());
		assertEquals("qwcc qcd", vendorServiceProviderDTO.getDescOfServices());
		assertEquals("1234ABC", vendorServiceProviderDTO.getServiceAccgCode());
		assertEquals("0.9%", vendorServiceProviderDTO.getGstRateExpected());
		assertEquals("Yes", vendorServiceProviderDTO.getIsCompSchemeUnderGst());

	}

}
