package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class QuestionMasterTest {

	@Test
	void testQuestionMasterEntity() {
		QuestionMaster questionMaster = new QuestionMaster();

		questionMaster.setQuestionId(1);
		questionMaster.setIsMcqType(1);
		questionMaster.setFormType(2);
		questionMaster.setQuestion("SampleQuestion");

		assertEquals(1, questionMaster.getQuestionId());
		assertEquals(1, questionMaster.getIsMcqType());
		assertEquals(2, questionMaster.getFormType());
		assertEquals("SampleQuestion", questionMaster.getQuestion());

	}

	@Test
	void testQuestionMasterMcqOptionsRelationship() {
		// Create an instance of QuestionMaster
		QuestionMaster questionMaster = new QuestionMaster();

		// Create McqOptions instances
		McqOptions mcqOption1 = new McqOptions();
		mcqOption1.setMcqOptionId(1);
		mcqOption1.setOption("Option1");

		McqOptions mcqOption2 = new McqOptions();
		mcqOption2.setMcqOptionId(2);
		mcqOption2.setOption("Option2");

		// Set McqOptions list in QuestionMaster
		questionMaster.setMcqOptions(Arrays.asList(mcqOption1, mcqOption2));

		// Check if the McqOptions list is set correctly
		assertNotNull(questionMaster.getMcqOptions());
		assertEquals(2, questionMaster.getMcqOptions().size());

	}
}
