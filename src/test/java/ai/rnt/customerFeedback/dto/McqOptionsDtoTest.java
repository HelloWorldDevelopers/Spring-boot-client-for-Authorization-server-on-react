package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class McqOptionsDtoTest {

	@Test
	void testMcqOptionsDto() {
		Integer expectedMcqOptionId = 1;
		String expectedOption = "Option A";
		QuestionMasterDto questionMasterDto = new QuestionMasterDto();
		questionMasterDto.setQuestionId(101);
		McqOptionsDto mcqOptionsDto = new McqOptionsDto();
		mcqOptionsDto.setMcqOptionId(expectedMcqOptionId);
		mcqOptionsDto.setOption(expectedOption);
		mcqOptionsDto.setQuestionMasterDto(questionMasterDto);
		assertEquals(expectedMcqOptionId, mcqOptionsDto.getMcqOptionId());
		assertEquals(expectedOption, mcqOptionsDto.getOption());
		assertEquals(questionMasterDto, mcqOptionsDto.getQuestionMasterDto());
	}
}