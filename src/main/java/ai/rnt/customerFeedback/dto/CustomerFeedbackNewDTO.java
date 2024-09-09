package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerFeedbackNewDTO {
	
	private Integer customerFeedbackId;

	private String answer;

	private CustomerFeebackMasterDTONew customerFeedbackMaster;

	private QuestionDTO questionMaster;

}
