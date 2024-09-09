package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AddressMasterDTOTest {

	@Test
	void testCustomerDto() {
		String expectedCompanyName = "ABC Company";

		AddressMasterDTO addressMasterDTO = new AddressMasterDTO();
		addressMasterDTO.setAddressId(1);
		addressMasterDTO.setCompanyName("wdc");
		addressMasterDTO.setContactPersonEmail("wdx");
		addressMasterDTO.setContactPersonName("yuu");
		addressMasterDTO.setContactPersonNo("678899");
		addressMasterDTO.setCustomerId(1);
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(1);
		customerDto.setCompanyName(expectedCompanyName);
		customerDto.setContactPersonName("hjjj");
		customerDto.setCustomerContactNumber("8765678965");
		customerDto.setEmailId("hjuui");

		assertEquals(1, customerDto.getCustomerId());
		assertEquals("ABC Company", customerDto.getCompanyName());
		assertEquals("hjuui", customerDto.getEmailId());
		assertEquals("hjjj", customerDto.getContactPersonName());
		assertEquals("8765678965", customerDto.getCustomerContactNumber());
		addressMasterDTO.setCustomer(customerDto);

		assertEquals(1, addressMasterDTO.getCustomerId());
		assertEquals(1, addressMasterDTO.getAddressId());
		assertEquals("wdc", addressMasterDTO.getCompanyName());
		assertEquals("wdx", addressMasterDTO.getContactPersonEmail());
		assertEquals("yuu", addressMasterDTO.getContactPersonName());
		assertEquals("678899", addressMasterDTO.getContactPersonNo());
		assertEquals(customerDto, addressMasterDTO.getCustomer());

	}

}
