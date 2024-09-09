package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class CustomerTest {

	@Test
	void testCustomerEntity() {
		Customer customer = new Customer();

		customer.setCustomerId(1);
		customer.setCompanyName("SampleCompany");

		customer.setCustomerContactNumber("898776657");
		customer.setContactPersonName("wdscwefcw");

		customer.setEmailId("wqdcqwdc");

		assertEquals(1, customer.getCustomerId());
		assertEquals("SampleCompany", customer.getCompanyName());

		assertEquals("wqdcqwdc", customer.getEmailId());
		assertEquals("898776657", customer.getCustomerContactNumber());
		assertEquals("wdscwefcw", customer.getContactPersonName());

		customer.setCreatedBy(1);
		customer.setCreatedDate(LocalDateTime.now());
		customer.setUpdatedBy(2);
		customer.setUpdatedDate(LocalDateTime.now());
		customer.setDeletedBy(3);
		customer.setDeletedDate(LocalDateTime.now());

		assertEquals(1, customer.getCreatedBy());
		assertNotNull(customer.getCreatedDate());
		assertEquals(2, customer.getUpdatedBy());
		assertNotNull(customer.getUpdatedDate());
		assertEquals(3, customer.getDeletedBy());
		assertNotNull(customer.getDeletedDate());
	}

	@Test
	void testCloneMethod() throws CloneNotSupportedException {
		Customer originalCustomer = new Customer();
		originalCustomer.setCustomerId(1);
		originalCustomer.setCompanyName("SampleCompany");

		Customer clonedCustomer = (Customer) originalCustomer;

		assertEquals(originalCustomer, clonedCustomer);

		assertEquals(originalCustomer, clonedCustomer);
	}
}