package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerFeedbackDto {

	private Integer customerFeedbackId;

	private String answer;

	private CustomerFeedbackMasterDto customerFeedbackMasterDto;

	private QuestionMasterDto questionMasterDto;
}
