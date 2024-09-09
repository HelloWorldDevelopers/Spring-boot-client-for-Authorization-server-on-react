package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GSTDetailsDtoTest {

    @Test
    void testGSTDetailsDto() {
        Integer expectedGstDtlId = 1;
        String expectedGstNo = "GST123";
        String expectedGstAddress = "123 Main Street";
        VendorStateGSTCodeDTO vendorStateGSTCodeDTO = new VendorStateGSTCodeDTO();
        vendorStateGSTCodeDTO.setState("maharashtra");
        vendorStateGSTCodeDTO.setStateGstCode("01");
        vendorStateGSTCodeDTO.setStateGstCodeId(1);
        GSTDetailsDto gstDetailsDto = new GSTDetailsDto();
        gstDetailsDto.setGstDtlId(expectedGstDtlId);
        gstDetailsDto.setGstNo(expectedGstNo);
        gstDetailsDto.setGstAddress(expectedGstAddress);
        gstDetailsDto.setPanNo("PAN12345");
        gstDetailsDto.setVendorStateGSTCode(vendorStateGSTCodeDTO);
        assertEquals(vendorStateGSTCodeDTO, gstDetailsDto.getVendorStateGSTCode());
        assertEquals(expectedGstDtlId, gstDetailsDto.getGstDtlId());
        assertEquals("PAN12345", gstDetailsDto.getPanNo());
        assertEquals(expectedGstNo, gstDetailsDto.getGstNo());
        assertEquals(expectedGstAddress, gstDetailsDto.getGstAddress());
    }
}