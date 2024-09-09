package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerSatisfactionMasterDTONewTest {

	@Test
	void testCustomerFeedbackMasterDto() {
		Integer custSatisfactionId = 1;
		AddressMasterDTO addressMasterDTO = new AddressMasterDTO();
		addressMasterDTO.setAddressId(1);
		addressMasterDTO.setContactPersonEmail("2wdc");
		addressMasterDTO.setContactPersonName("gukkjj");
		addressMasterDTO.setCustomerId(1);

		CustomerSatisfactionMasterDTONew customerFeedbackMasterDto = new CustomerSatisfactionMasterDTONew();
		customerFeedbackMasterDto.setCustSatisfactionId(custSatisfactionId);
		customerFeedbackMasterDto.setAddressId(1);
		customerFeedbackMasterDto.setAddressMaster(addressMasterDTO);
		customerFeedbackMasterDto.setCustomerId(1);
		customerFeedbackMasterDto.setCompanyName("wec");
		customerFeedbackMasterDto.setContactPersonName("abc");
		customerFeedbackMasterDto.setContactPersonEmail("thn");
		customerFeedbackMasterDto.setContactPersonNo("6767787");
		customerFeedbackMasterDto.setFilled(true);
		customerFeedbackMasterDto.setFilledDate("08-09-2024");
		customerFeedbackMasterDto.setPseudoName("comapany");
		assertEquals(1, customerFeedbackMasterDto.getCustSatisfactionId());
		assertEquals(1, customerFeedbackMasterDto.getAddressId());
		assertEquals(1, customerFeedbackMasterDto.getCustomerId());
		assertEquals("wec", customerFeedbackMasterDto.getCompanyName());
		assertEquals("abc", customerFeedbackMasterDto.getContactPersonName());
		assertEquals("6767787", customerFeedbackMasterDto.getContactPersonNo());
		assertEquals("thn", customerFeedbackMasterDto.getContactPersonEmail());
		assertEquals(true, customerFeedbackMasterDto.getFilled());
		assertEquals("08-09-2024", customerFeedbackMasterDto.getFilledDate());
		assertEquals("comapany", customerFeedbackMasterDto.getPseudoName());

		assertEquals(addressMasterDTO, customerFeedbackMasterDto.getAddressMaster());
	}

}
