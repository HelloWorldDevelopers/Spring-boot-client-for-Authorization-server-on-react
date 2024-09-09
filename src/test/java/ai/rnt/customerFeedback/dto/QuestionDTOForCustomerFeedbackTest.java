package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionDTOForCustomerFeedbackTest {

	@Test
	void test() {
		QuestionDTOForCustomerFeedback customerFeedback = new QuestionDTOForCustomerFeedback();
		customerFeedback.setQuestion("nikjj");
		customerFeedback.setQuestionId(1);

		assertEquals(1, customerFeedback.getQuestionId());
		assertEquals("nikjj", customerFeedback.getQuestion());
	}

}
