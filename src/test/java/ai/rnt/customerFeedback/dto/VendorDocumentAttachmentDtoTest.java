package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorDocumentAttachmentDtoTest {

	@Test
	void testVendorDocumentAttachmentDto() {
		Integer expectedAttachmentId = 1;
		String expectedDocumentAttachment = "Base64EncodedData";
		String expectedFileType = "pdf";
		String expectedFileName = "invoice.pdf";
		VendorDocumentMasterDto vendorDocumentMasterDto = new VendorDocumentMasterDto();
		vendorDocumentMasterDto.setDocumentId(101);
		vendorDocumentMasterDto.setDocumentType("Invoice");
		VendorDocumentAttachmentDto vendorDocumentAttachmentDto = new VendorDocumentAttachmentDto();
		vendorDocumentAttachmentDto.setAttachmentId(expectedAttachmentId);
		vendorDocumentAttachmentDto.setDocumentAttachment(expectedDocumentAttachment);
		vendorDocumentAttachmentDto.setFileType(expectedFileType);
		vendorDocumentAttachmentDto.setFileName(expectedFileName);
		vendorDocumentAttachmentDto.setVendorDocumentMaster(vendorDocumentMasterDto);
		assertEquals(expectedAttachmentId, vendorDocumentAttachmentDto.getAttachmentId());
		assertEquals(expectedDocumentAttachment, vendorDocumentAttachmentDto.getDocumentAttachment());
		assertEquals(expectedFileType, vendorDocumentAttachmentDto.getFileType());
		assertEquals(expectedFileName, vendorDocumentAttachmentDto.getFileName());
		assertEquals(vendorDocumentMasterDto, vendorDocumentAttachmentDto.getVendorDocumentMaster());

	}
}
