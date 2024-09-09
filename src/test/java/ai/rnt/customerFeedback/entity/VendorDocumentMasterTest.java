package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class VendorDocumentMasterTest {

	@Test
	void testVendorDocumentMasterEntity() {
		VendorDocumentMaster vendorDocumentMaster = new VendorDocumentMaster();

		vendorDocumentMaster.setDocumentId(1);
		VendorDocumentAttachment vendorDocumentAttachment1 = new VendorDocumentAttachment();
		VendorDocumentAttachment vendorDocumentAttachment2 = new VendorDocumentAttachment();
		vendorDocumentMaster
				.setVendorDocumentAttachment(Arrays.asList(vendorDocumentAttachment1, vendorDocumentAttachment2));
		vendorDocumentMaster.setDocumentType("SampleDocumentType");

		assertEquals(1, vendorDocumentMaster.getDocumentId());
		assertNotNull(vendorDocumentMaster.getVendorDocumentAttachment());
		assertEquals(2, vendorDocumentMaster.getVendorDocumentAttachment().size());
		assertEquals("SampleDocumentType", vendorDocumentMaster.getDocumentType());

		vendorDocumentMaster.setCreatedBy(1);
		vendorDocumentMaster.setCreatedDate(LocalDateTime.now());
		vendorDocumentMaster.setUpdatedBy(2);
		vendorDocumentMaster.setUpdatedDate(LocalDateTime.now());
		vendorDocumentMaster.setDeletedBy(3);
		vendorDocumentMaster.setDeletedDate(LocalDateTime.now());

		assertEquals(1, vendorDocumentMaster.getCreatedBy());
		assertNotNull(vendorDocumentMaster.getCreatedDate());
		assertEquals(2, vendorDocumentMaster.getUpdatedBy());
		assertNotNull(vendorDocumentMaster.getUpdatedDate());
		assertEquals(3, vendorDocumentMaster.getDeletedBy());
		assertNotNull(vendorDocumentMaster.getDeletedDate());
	}

}
