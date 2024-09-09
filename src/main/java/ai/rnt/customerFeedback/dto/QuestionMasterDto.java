package ai.rnt.customerFeedback.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionMasterDto {

	private Integer questionId;

	private Integer isMcqType;

	private Integer formType;

	private String question;

	private String example;

	private List<McqOptionsDto> mcqOptionsDtos;
}
