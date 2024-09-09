package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerFeedbackDtoTest {

	@Test
	void testCustomerFeedbackDto() {
		Integer expectedCustomerFeedbackId = 1;
		String expectedAnswer = "Excellent";
		CustomerFeedbackMasterDto customerFeedbackMasterDto = new CustomerFeedbackMasterDto();
		QuestionMasterDto questionMasterDto = new QuestionMasterDto();
		CustomerFeedbackDto customerFeedbackDto = new CustomerFeedbackDto();
		customerFeedbackDto.setCustomerFeedbackId(expectedCustomerFeedbackId);
		customerFeedbackDto.setAnswer(expectedAnswer);
		customerFeedbackDto.setCustomerFeedbackMasterDto(customerFeedbackMasterDto);
		customerFeedbackDto.setQuestionMasterDto(questionMasterDto);
		assertEquals(expectedCustomerFeedbackId, customerFeedbackDto.getCustomerFeedbackId());
		assertEquals(expectedAnswer, customerFeedbackDto.getAnswer());
		assertEquals(customerFeedbackMasterDto, customerFeedbackDto.getCustomerFeedbackMasterDto());
		assertEquals(questionMasterDto, customerFeedbackDto.getQuestionMasterDto());

	}
}
