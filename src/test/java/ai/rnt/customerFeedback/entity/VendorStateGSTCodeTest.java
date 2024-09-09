package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class VendorStateGSTCodeTest {

	@Test
	void test() {
		VendorStateGSTCode vendorStateGSTCode = new VendorStateGSTCode();

		vendorStateGSTCode.setStateGstCodeId(1);
		vendorStateGSTCode.setStateGstCode(22);
		vendorStateGSTCode.setState("state");
		List<GSTDetails> gstDetails = new ArrayList<>();
		vendorStateGSTCode.setGstdetailsList(gstDetails);

		assertEquals(1, vendorStateGSTCode.getStateGstCodeId());
		assertEquals(22, vendorStateGSTCode.getStateGstCode());
		assertEquals("state", vendorStateGSTCode.getState());
		assertNotNull(vendorStateGSTCode.getGstdetailsList());
		vendorStateGSTCode.setCreatedBy(1);
		vendorStateGSTCode.setCreatedDate(LocalDateTime.now());
		vendorStateGSTCode.setUpdatedBy(2);
		vendorStateGSTCode.setUpdatedDate(LocalDateTime.now());
		vendorStateGSTCode.setDeletedBy(3);
		vendorStateGSTCode.setDeletedDate(LocalDateTime.now());

		assertEquals(1, vendorStateGSTCode.getCreatedBy());
		assertNotNull(vendorStateGSTCode.getCreatedDate());
		assertEquals(2, vendorStateGSTCode.getUpdatedBy());
		assertNotNull(vendorStateGSTCode.getUpdatedDate());
		assertEquals(3, vendorStateGSTCode.getDeletedBy());
		assertNotNull(vendorStateGSTCode.getDeletedDate());
	}

}
