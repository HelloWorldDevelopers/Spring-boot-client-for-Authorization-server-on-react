
package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerSatisfactionSurveyDto {

	private Integer customerSatisfactionSurveyId;

	private String answer;

	private CustomerSatisfactionMasterDto customerSatisfactionMasterDto;

	private QuestionMasterDto questionMasterDto;

}
