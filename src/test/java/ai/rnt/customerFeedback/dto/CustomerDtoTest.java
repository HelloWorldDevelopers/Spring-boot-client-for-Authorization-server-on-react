package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerDtoTest {

	@Test
	void testCustomerDto() {
		Integer expectedCustomerId = 1;
		String expectedCompanyName = "ABC Company";
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(expectedCustomerId);
		customerDto.setCompanyName(expectedCompanyName);
		assertEquals(expectedCustomerId, customerDto.getCustomerId());
		assertEquals(expectedCompanyName, customerDto.getCompanyName());
	}
}