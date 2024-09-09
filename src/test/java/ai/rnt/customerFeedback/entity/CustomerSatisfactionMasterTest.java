package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class CustomerSatisfactionMasterTest {

	@Test
	void testCustomerSatisfactionMasterEntity() {
		CustomerSatisfactionMaster customerSatisfactionMaster = new CustomerSatisfactionMaster();

		customerSatisfactionMaster.setCustSatisfactionId(1);

		customerSatisfactionMaster.setAddressId(1);

		assertEquals(1, customerSatisfactionMaster.getCustSatisfactionId());
		assertNotNull(customerSatisfactionMaster.getAddressId());

		customerSatisfactionMaster.setCreatedBy(1);
		customerSatisfactionMaster.setCreatedDate(LocalDateTime.now());
		customerSatisfactionMaster.setUpdatedBy(2);
		customerSatisfactionMaster.setUpdatedDate(LocalDateTime.now());
		customerSatisfactionMaster.setDeletedBy(3);
		customerSatisfactionMaster.setDeletedDate(LocalDateTime.now());

		assertEquals(1, customerSatisfactionMaster.getCreatedBy());
		assertNotNull(customerSatisfactionMaster.getCreatedDate());
		assertEquals(2, customerSatisfactionMaster.getUpdatedBy());
		assertNotNull(customerSatisfactionMaster.getUpdatedDate());
		assertEquals(3, customerSatisfactionMaster.getDeletedBy());
		assertNotNull(customerSatisfactionMaster.getDeletedDate());

		customerSatisfactionMaster.setFilled(true);
		assertEquals(true, customerSatisfactionMaster.getFilled());
	}

}
