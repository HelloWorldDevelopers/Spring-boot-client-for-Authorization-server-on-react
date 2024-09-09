package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustomerSatisfactionSurveyDtoTest {

	@Test
	void testCustomerSatisfactionSurveyDto() {
		Integer expectedCustomerSatisfactionSurveyId = 1;
		String expectedAnswer = "Very Satisfied";
		CustomerSatisfactionMasterDto customerSatisfactionMasterDto = new CustomerSatisfactionMasterDto();
		customerSatisfactionMasterDto.setCustSatisfactionId(101);
		QuestionMasterDto questionMasterDto = new QuestionMasterDto();
		questionMasterDto.setQuestionId(201);
		CustomerSatisfactionSurveyDto customerSatisfactionSurveyDto = new CustomerSatisfactionSurveyDto();
		customerSatisfactionSurveyDto.setCustomerSatisfactionSurveyId(expectedCustomerSatisfactionSurveyId);
		customerSatisfactionSurveyDto.setAnswer(expectedAnswer);
		customerSatisfactionSurveyDto.setCustomerSatisfactionMasterDto(customerSatisfactionMasterDto);
		customerSatisfactionSurveyDto.setQuestionMasterDto(questionMasterDto);
		assertEquals(expectedCustomerSatisfactionSurveyId,
				customerSatisfactionSurveyDto.getCustomerSatisfactionSurveyId());
		assertEquals(expectedAnswer, customerSatisfactionSurveyDto.getAnswer());
		assertEquals(customerSatisfactionMasterDto, customerSatisfactionSurveyDto.getCustomerSatisfactionMasterDto());
		assertEquals(questionMasterDto, customerSatisfactionSurveyDto.getQuestionMasterDto());
	}
}
