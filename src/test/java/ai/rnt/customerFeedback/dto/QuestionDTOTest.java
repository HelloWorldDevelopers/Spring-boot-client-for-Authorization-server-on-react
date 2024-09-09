package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuestionDTOTest {

	@Test
	void test() {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setQuestionId(1);
		questionDTO.setIsMcqType(1);
		questionDTO.setQuestion("quetion");
		questionDTO.setExample("example");
		List<MCQOptionDTO> mcqOptions = new ArrayList<>();
		questionDTO.setMcqOptions(mcqOptions);

		assertEquals("quetion", questionDTO.getQuestion());
		assertEquals("example", questionDTO.getExample());
		assertEquals(1, questionDTO.getQuestionId());
		assertEquals(1, questionDTO.getIsMcqType());
		assertEquals(mcqOptions, questionDTO.getMcqOptions());

	}

}
