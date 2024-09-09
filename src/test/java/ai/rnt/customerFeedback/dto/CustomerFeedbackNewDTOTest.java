package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerFeedbackNewDTOTest {
	@Test
	void testCustomerFeedbackDto() {
		Integer expectedCustomerFeedbackId = 1;
		String expectedAnswer = "Excellent";

		CustomerFeebackMasterDTONew customerFeedbackMasterDto = new CustomerFeebackMasterDTONew();

		QuestionDTO questionMasterDto = new QuestionDTO();

		CustomerFeedbackNewDTO customerFeedbackDto = new CustomerFeedbackNewDTO();
		customerFeedbackDto.setCustomerFeedbackId(expectedCustomerFeedbackId);
		customerFeedbackDto.setAnswer(expectedAnswer);
		customerFeedbackDto.setCustomerFeedbackMaster(customerFeedbackMasterDto);
		customerFeedbackDto.setQuestionMaster(questionMasterDto);

		assertEquals(expectedCustomerFeedbackId, customerFeedbackDto.getCustomerFeedbackId());
		assertEquals(expectedAnswer, customerFeedbackDto.getAnswer());
		assertEquals(customerFeedbackMasterDto, customerFeedbackDto.getCustomerFeedbackMaster());
		assertEquals(questionMasterDto, customerFeedbackDto.getQuestionMaster());
	}

}
