package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class CustomerFeedbackMasterTest {

	@Test
	void testCustomerFeedbackMasterEntity() {
		CustomerFeedbackMaster customerFeedbackMaster = new CustomerFeedbackMaster();

		customerFeedbackMaster.setCustFeedbackId(1);
		customerFeedbackMaster.setRole("SampleRole");
		customerFeedbackMaster.setFeedbackBy("SampleFeedbackBy");

		Project project = new Project();
		customerFeedbackMaster.setProject(project);

		assertEquals(1, customerFeedbackMaster.getCustFeedbackId());
		assertEquals("SampleRole", customerFeedbackMaster.getRole());
		assertEquals("SampleFeedbackBy", customerFeedbackMaster.getFeedbackBy());
		assertNotNull(customerFeedbackMaster.getProject());

		customerFeedbackMaster.setCreatedBy(1);
		customerFeedbackMaster.setCreatedDate(LocalDateTime.now());
		customerFeedbackMaster.setUpdatedBy(2);
		customerFeedbackMaster.setUpdatedDate(LocalDateTime.now());
		customerFeedbackMaster.setDeletedBy(3);
		customerFeedbackMaster.setDeletedDate(LocalDateTime.now());

		assertEquals(1, customerFeedbackMaster.getCreatedBy());
		assertNotNull(customerFeedbackMaster.getCreatedDate());
		assertEquals(2, customerFeedbackMaster.getUpdatedBy());
		assertNotNull(customerFeedbackMaster.getUpdatedDate());
		assertEquals(3, customerFeedbackMaster.getDeletedBy());
		assertNotNull(customerFeedbackMaster.getDeletedDate());

		customerFeedbackMaster.setFilled(true);
		assertEquals(true, customerFeedbackMaster.getFilled());
	}

}
