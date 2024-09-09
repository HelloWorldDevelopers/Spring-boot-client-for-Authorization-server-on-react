package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorCoreGoodServicesDTOTest {

	@Test
	void test() {
		VendorCoreGoodServicesDTO vendorCoreGoodServicesDTO = new VendorCoreGoodServicesDTO();
		vendorCoreGoodServicesDTO.setServiceId(1);
		vendorCoreGoodServicesDTO.setCoreGoodsService("cwefcefc");

		assertEquals(1, vendorCoreGoodServicesDTO.getServiceId());
		assertEquals("cwefcefc", vendorCoreGoodServicesDTO.getCoreGoodsService());

	}

}
