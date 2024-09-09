package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CustoSatisSurveyDTOTest {

	@Test
	void test() {
		CustoSatisSurveyDTO customerDto = new CustoSatisSurveyDTO();
		QuestionDTO questionDTO = new QuestionDTO();
		CustomerSatisfactionMasterDto customerSatisfactionMasterDto = new CustomerSatisfactionMasterDto();
		customerDto.setCustomerSatisfactionSurveyId(1);
		customerDto.setAnswer("answer");
		customerDto.setQuestionMaster(questionDTO);
		customerDto.setCustomerSatisfactionMaster(customerSatisfactionMasterDto);

		assertEquals(1, customerDto.getCustomerSatisfactionSurveyId());
		assertEquals("answer", customerDto.getAnswer());
		assertEquals(questionDTO, customerDto.getQuestionMaster());
		assertEquals(customerSatisfactionMasterDto, customerDto.getCustomerSatisfactionMaster());

	}

}
