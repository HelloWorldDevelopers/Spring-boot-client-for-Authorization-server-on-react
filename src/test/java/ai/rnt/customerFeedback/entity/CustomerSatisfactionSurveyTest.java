package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class CustomerSatisfactionSurveyTest {

	@Test
	void testCustomerSatisfactionSurveyEntity() {
		CustomerSatisfactionSurvey customerSatisfactionSurvey = new CustomerSatisfactionSurvey();

		customerSatisfactionSurvey.setCustomerSatisfactionSurveyId(1);
		customerSatisfactionSurvey.setAnswer("SampleAnswer");

		CustomerSatisfactionMaster customerSatisfactionMaster = new CustomerSatisfactionMaster();
		customerSatisfactionSurvey.setCustomerSatisfactionMaster(customerSatisfactionMaster);

		QuestionMaster questionMaster = new QuestionMaster();
		customerSatisfactionSurvey.setQuestionMaster(questionMaster);

		assertEquals(1, customerSatisfactionSurvey.getCustomerSatisfactionSurveyId());
		assertEquals("SampleAnswer", customerSatisfactionSurvey.getAnswer());
		assertNotNull(customerSatisfactionSurvey.getCustomerSatisfactionMaster());
		assertNotNull(customerSatisfactionSurvey.getQuestionMaster());

		customerSatisfactionSurvey.setCreatedBy(1);
		customerSatisfactionSurvey.setCreatedDate(LocalDateTime.now());
		customerSatisfactionSurvey.setUpdatedBy(2);
		customerSatisfactionSurvey.setUpdatedDate(LocalDateTime.now());
		customerSatisfactionSurvey.setDeletedBy(3);
		customerSatisfactionSurvey.setDeletedDate(LocalDateTime.now());

		assertEquals(1, customerSatisfactionSurvey.getCreatedBy());
		assertNotNull(customerSatisfactionSurvey.getCreatedDate());
		assertEquals(2, customerSatisfactionSurvey.getUpdatedBy());
		assertNotNull(customerSatisfactionSurvey.getUpdatedDate());
		assertEquals(3, customerSatisfactionSurvey.getDeletedBy());
		assertNotNull(customerSatisfactionSurvey.getDeletedDate());
	}
}
