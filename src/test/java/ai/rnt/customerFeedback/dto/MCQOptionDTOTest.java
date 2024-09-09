package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MCQOptionDTOTest {

	@Test
	void test() {
		MCQOptionDTO mcqoption = new MCQOptionDTO();
		mcqoption.setMcqOptionId(1);
		mcqoption.setOption("option");

		assertEquals("option", mcqoption.getOption());
		assertEquals(1, mcqoption.getMcqOptionId());

	}

}
