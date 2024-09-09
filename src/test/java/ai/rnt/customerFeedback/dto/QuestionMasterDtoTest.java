package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuestionMasterDtoTest {

	@Test
	void testQuestionMasterDto() {
		Integer expectedQuestionId = 1;
		Integer expectedIsMcqType = 1;
		Integer expectedFormType = 2;
		String expectedQuestion = "What is your favorite programming language?";
		String expectedExample = "e.g., Java";
		McqOptionsDto option1 = new McqOptionsDto();
		option1.setMcqOptionId(101);
		option1.setOption("Java");
		McqOptionsDto option2 = new McqOptionsDto();
		option2.setMcqOptionId(102);
		option2.setOption("Python");
		List<McqOptionsDto> mcqOptionsDtos = Arrays.asList(option1, option2);
		QuestionMasterDto questionMasterDto = new QuestionMasterDto();
		questionMasterDto.setQuestionId(expectedQuestionId);
		questionMasterDto.setIsMcqType(expectedIsMcqType);
		questionMasterDto.setFormType(expectedFormType);
		questionMasterDto.setQuestion(expectedQuestion);
		questionMasterDto.setExample(expectedExample);
		questionMasterDto.setMcqOptionsDtos(mcqOptionsDtos);
		assertEquals(expectedQuestionId, questionMasterDto.getQuestionId());
		assertEquals(expectedIsMcqType, questionMasterDto.getIsMcqType());
		assertEquals(expectedFormType, questionMasterDto.getFormType());
		assertEquals(expectedQuestion, questionMasterDto.getQuestion());
		assertEquals(expectedExample, questionMasterDto.getExample());
		assertEquals(mcqOptionsDtos, questionMasterDto.getMcqOptionsDtos());

	}
}