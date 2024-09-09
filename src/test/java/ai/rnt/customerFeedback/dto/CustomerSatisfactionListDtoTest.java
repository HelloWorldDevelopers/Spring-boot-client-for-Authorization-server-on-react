package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CustomerSatisfactionListDtoTest {
	@Test
	void testGetterAndSetter() {
		CustomerSatisfactionListDto dto = new CustomerSatisfactionListDto();
		dto.setCustSatisfactionId(1);
		dto.setCreatedDate("2024-03-21");
		dto.setCompanyName("Company");
		dto.setCustomerId(1);
		dto.setFilledDate("2024-03-21");
		dto.setFilled(true);
		dto.setEmailId("email@example.com");
		dto.setCustomerContactNumber("1234567890");
		dto.setContactPersonName("person name");
		assertEquals(1, dto.getCustSatisfactionId());
		assertEquals(1, dto.getCustomerId());
		assertEquals("2024-03-21", dto.getCreatedDate());
		assertEquals("2024-03-21", dto.getFilledDate());
		assertEquals("Company", dto.getCompanyName());
		assertTrue(dto.getFilled());
		assertEquals("email@example.com", dto.getEmailId());
		assertEquals("1234567890", dto.getCustomerContactNumber());
		assertEquals("person name", dto.getContactPersonName());

	}
}
