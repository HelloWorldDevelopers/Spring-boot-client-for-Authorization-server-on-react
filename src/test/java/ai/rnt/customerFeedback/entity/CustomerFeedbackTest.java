package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class CustomerFeedbackTest {

	@Test
	void testCustomerFeedbackEntity() {
		CustomerFeedback customerFeedback = new CustomerFeedback();

		customerFeedback.setCustomerFeedbackId(1);
		customerFeedback.setAnswer("SampleAnswer");

		CustomerFeedbackMaster customerFeedbackMaster = new CustomerFeedbackMaster();
		customerFeedback.setCustomerFeedbackMaster(customerFeedbackMaster);

		QuestionMaster questionMaster = new QuestionMaster();
		customerFeedback.setQuestionMaster(questionMaster);

		assertEquals(1, customerFeedback.getCustomerFeedbackId());
		assertEquals("SampleAnswer", customerFeedback.getAnswer());
		assertNotNull(customerFeedback.getCustomerFeedbackMaster());
		assertNotNull(customerFeedback.getQuestionMaster());

		customerFeedback.setCreatedBy(1);
		customerFeedback.setCreatedDate(LocalDateTime.now());
		customerFeedback.setUpdatedBy(2);
		customerFeedback.setUpdatedDate(LocalDateTime.now());
		customerFeedback.setDeletedBy(3);
		customerFeedback.setDeletedDate(LocalDateTime.now());

		assertEquals(1, customerFeedback.getCreatedBy());
		assertNotNull(customerFeedback.getCreatedDate());
		assertEquals(2, customerFeedback.getUpdatedBy());
		assertNotNull(customerFeedback.getUpdatedDate());
		assertEquals(3, customerFeedback.getDeletedBy());
		assertNotNull(customerFeedback.getDeletedDate());
	}
}
