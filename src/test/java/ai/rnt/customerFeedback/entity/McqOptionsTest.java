package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class McqOptionsTest {

	@Test
	void testMcqOptionsEntity() {
		McqOptions mcqOptions = new McqOptions();

		mcqOptions.setMcqOptionId(1);
		mcqOptions.setOption("SampleOption");
		QuestionMaster questionMaster = new QuestionMaster();
		mcqOptions.setQuestionMaster(questionMaster);

		assertEquals(1, mcqOptions.getMcqOptionId());
		assertEquals("SampleOption", mcqOptions.getOption());
		assertNotNull(mcqOptions.getQuestionMaster());

		mcqOptions.setCreatedBy(1);
		mcqOptions.setCreatedDate(LocalDateTime.now());
		mcqOptions.setUpdatedBy(2);
		mcqOptions.setUpdatedDate(LocalDateTime.now());
		mcqOptions.setDeletedBy(3);
		mcqOptions.setDeletedDate(LocalDateTime.now());

		assertEquals(1, mcqOptions.getCreatedBy());
		assertNotNull(mcqOptions.getCreatedDate());
		assertEquals(2, mcqOptions.getUpdatedBy());
		assertNotNull(mcqOptions.getUpdatedDate());
		assertEquals(3, mcqOptions.getDeletedBy());
		assertNotNull(mcqOptions.getDeletedDate());
	}

}
