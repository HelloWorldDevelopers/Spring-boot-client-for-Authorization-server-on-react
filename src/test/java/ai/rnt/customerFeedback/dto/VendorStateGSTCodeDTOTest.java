package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorStateGSTCodeDTOTest {

	@Test
	void test() {
		VendorStateGSTCodeDTO vendorStateGSTCodeDTO = new VendorStateGSTCodeDTO();
		vendorStateGSTCodeDTO.setStateGstCodeId(1);
		vendorStateGSTCodeDTO.setStateGstCode("1234GST");
		vendorStateGSTCodeDTO.setState("maharashtra");

		assertEquals(1, vendorStateGSTCodeDTO.getStateGstCodeId());
		assertEquals("1234GST", vendorStateGSTCodeDTO.getStateGstCode());
		assertEquals("maharashtra", vendorStateGSTCodeDTO.getState());

	}

}
