package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VendorDocumentMasterDtoTest {

	@Test
	void testVendorDocumentMasterDto() {
		Integer expectedDocumentId = 1;
		String expectedDocumentType = "Invoice";
		VendorDocumentMasterDto vendorDocumentMasterDto = new VendorDocumentMasterDto();
		vendorDocumentMasterDto.setDocumentId(expectedDocumentId);
		vendorDocumentMasterDto.setDocumentType(expectedDocumentType);
		assertEquals(expectedDocumentId, vendorDocumentMasterDto.getDocumentId());
		assertEquals(expectedDocumentType, vendorDocumentMasterDto.getDocumentType());

	}
}
