package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class McqOptionsDto {
	private Integer mcqOptionId;

	private String option;

	private QuestionMasterDto questionMasterDto;
}
