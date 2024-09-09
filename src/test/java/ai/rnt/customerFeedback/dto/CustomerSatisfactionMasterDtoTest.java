package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerSatisfactionMasterDtoTest {

	@Test
	void testCustomerSatisfactionMasterDto() {
		Integer expectedCustSatisfactionId = 1;

		CustomerDto customerDto = new CustomerDto();
		customerDto.setCompanyName("comany");
		customerDto.setContactPersonName("name");
		customerDto.setCustomerId(1);

		CustomerSatisfactionMasterDto customerSatisfactionMasterDto = new CustomerSatisfactionMasterDto();
		customerSatisfactionMasterDto.setCustSatisfactionId(expectedCustSatisfactionId);
		customerSatisfactionMasterDto.setAddressId(1);

		assertEquals(expectedCustSatisfactionId, customerSatisfactionMasterDto.getCustSatisfactionId());
		assertEquals(1, customerSatisfactionMasterDto.getAddressId());

	}
}
