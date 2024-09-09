package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class VendorDocumentAttachmentTest {
	@Test
	void testVendorDocumentAttachmentEntity() {
		VendorDocumentAttachment vendorDocumentAttachment = new VendorDocumentAttachment();

		vendorDocumentAttachment.setAttachmentId(1);
		VendorDocumentMaster vendorDocumentMaster = new VendorDocumentMaster();
		vendorDocumentAttachment.setVendorDocumentMaster(vendorDocumentMaster);
		VendorKYC vendorKYC = new VendorKYC();
		vendorDocumentAttachment.setVendorKYC(vendorKYC);
		vendorDocumentAttachment.setDocumentAttachment("SampleDocumentAttachment");
		vendorDocumentAttachment.setFileType("PDF");
		vendorDocumentAttachment.setFileName("SampleFileName");

		assertEquals(1, vendorDocumentAttachment.getAttachmentId());
		assertNotNull(vendorDocumentAttachment.getVendorDocumentMaster());
		assertNotNull(vendorDocumentAttachment.getVendorKYC());
		assertEquals("SampleDocumentAttachment", vendorDocumentAttachment.getDocumentAttachment());
		assertEquals("PDF", vendorDocumentAttachment.getFileType());
		assertEquals("SampleFileName", vendorDocumentAttachment.getFileName());

		vendorDocumentAttachment.setCreatedBy(1);
		vendorDocumentAttachment.setCreatedDate(LocalDateTime.now());
		vendorDocumentAttachment.setUpdatedBy(2);
		vendorDocumentAttachment.setUpdatedDate(LocalDateTime.now());
		vendorDocumentAttachment.setDeletedBy(3);
		vendorDocumentAttachment.setDeletedDate(LocalDateTime.now());

		assertEquals(1, vendorDocumentAttachment.getCreatedBy());
		assertNotNull(vendorDocumentAttachment.getCreatedDate());
		assertEquals(2, vendorDocumentAttachment.getUpdatedBy());
		assertNotNull(vendorDocumentAttachment.getUpdatedDate());
		assertEquals(3, vendorDocumentAttachment.getDeletedBy());
		assertNotNull(vendorDocumentAttachment.getDeletedDate());
	}

}
