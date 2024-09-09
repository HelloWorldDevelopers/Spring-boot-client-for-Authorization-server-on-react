package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VendorDocumentAttachmentUploadDTOTest {

	@Test
	void test() {
		VendorDocumentAttachmentUploadDTO vendorDocument = new VendorDocumentAttachmentUploadDTO();
		VendorDocumentMasterDto vendordoc = new VendorDocumentMasterDto();
		VendorKYCNewDTO vendorKYC = new VendorKYCNewDTO();
		vendorDocument.setAttachmentId(1);
		vendorDocument.setDocumentAttachment("vendorDocumentAttachment");
		vendorDocument.setFileName("ewcwfc");
		vendorDocument.setFileType("qwdcwec");
		vendorDocument.setVendorDocumentMaster(vendordoc);
		vendorDocument.setVendorKYC(vendorKYC);

		assertEquals(1, vendorDocument.getAttachmentId());
		assertEquals("vendorDocumentAttachment", vendorDocument.getDocumentAttachment());
		assertEquals("ewcwfc", vendorDocument.getFileName());
		assertEquals("qwdcwec", vendorDocument.getFileType());
		assertEquals(vendordoc, vendorDocument.getVendorDocumentMaster());
		assertEquals(vendorKYC, vendorDocument.getVendorKYC());

	}

}
