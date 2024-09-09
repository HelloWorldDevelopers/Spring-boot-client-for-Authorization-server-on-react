package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class GSTDetailsTest {

	@Test
	void testGSTDetailsEntity() {
		GSTDetails gstDetails = new GSTDetails();
		VendorStateGSTCode vendorStateGSTCode = new VendorStateGSTCode();
		vendorStateGSTCode.setStateGstCodeId(1);
		vendorStateGSTCode.setStateGstCode(1);
		vendorStateGSTCode.setState("maharashtra");
		gstDetails.setGstDtlId(1);
		VendorKYC vendorKYC = new VendorKYC();
		gstDetails.setVendorKYC(vendorKYC);
		gstDetails.setGstNo("SampleGSTNo");
		gstDetails.setVendorStateGSTCode(vendorStateGSTCode);
		gstDetails.setGstAddress("SampleGSTAddress");
		gstDetails.setPanNo("PAN1234567");

		assertEquals(1, gstDetails.getGstDtlId());
		assertNotNull(gstDetails.getVendorKYC());
		assertNotNull(gstDetails.getVendorStateGSTCode());
		assertEquals("SampleGSTNo", gstDetails.getGstNo());
		assertEquals("SampleGSTAddress", gstDetails.getGstAddress());
		assertEquals("PAN1234567", gstDetails.getPanNo());

		gstDetails.setCreatedBy(1);
		gstDetails.setCreatedDate(LocalDateTime.now());
		gstDetails.setUpdatedBy(2);
		gstDetails.setUpdatedDate(LocalDateTime.now());
		gstDetails.setDeletedBy(3);
		gstDetails.setDeletedDate(LocalDateTime.now());

		assertEquals(1, gstDetails.getCreatedBy());
		assertNotNull(gstDetails.getCreatedDate());
		assertEquals(2, gstDetails.getUpdatedBy());
		assertNotNull(gstDetails.getUpdatedDate());
		assertEquals(3, gstDetails.getDeletedBy());
		assertNotNull(gstDetails.getDeletedDate());
	}

}
