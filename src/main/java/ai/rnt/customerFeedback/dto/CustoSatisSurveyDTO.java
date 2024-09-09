package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustoSatisSurveyDTO {

	private Integer customerSatisfactionSurveyId;

	private String answer;

	private CustomerSatisfactionMasterDto customerSatisfactionMaster;

	private QuestionDTO questionMaster;

}
