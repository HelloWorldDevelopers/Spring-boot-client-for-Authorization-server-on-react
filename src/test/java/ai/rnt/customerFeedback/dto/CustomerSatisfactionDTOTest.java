package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerSatisfactionDTOTest {

	@Test
	void test() {
		CustomerSatisfactionDTO customerSatisfactionDTO = new CustomerSatisfactionDTO();
		customerSatisfactionDTO.setCustSatisfactionId(1);
		customerSatisfactionDTO.setAddressId(1);
		customerSatisfactionDTO.setCustomerId(1);
		customerSatisfactionDTO.setCompanyName("abc");
		customerSatisfactionDTO.setContactPersonName("pqr");
		customerSatisfactionDTO.setContactPersonEmail("xyz");
		customerSatisfactionDTO.setContactPersonNo("7890789067");
		customerSatisfactionDTO.setFilled(true);
		customerSatisfactionDTO.setFilledDate("5-5-2024");
		customerSatisfactionDTO.setFormCreatedDate("4-05-2024");

		assertEquals(1, customerSatisfactionDTO.getCustSatisfactionId());
		assertEquals(1, customerSatisfactionDTO.getAddressId());
		assertEquals(1, customerSatisfactionDTO.getCustomerId());
		assertEquals("abc", customerSatisfactionDTO.getCompanyName());
		assertEquals("pqr", customerSatisfactionDTO.getContactPersonName());
		assertEquals("xyz", customerSatisfactionDTO.getContactPersonEmail());
		assertEquals("7890789067", customerSatisfactionDTO.getContactPersonNo());
		assertEquals(true, customerSatisfactionDTO.getFilled());
		assertEquals("5-5-2024", customerSatisfactionDTO.getFilledDate());
		assertEquals("4-05-2024", customerSatisfactionDTO.getFormCreatedDate());

	}

}
