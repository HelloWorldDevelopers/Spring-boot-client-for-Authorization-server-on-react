package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmployeePerformenceMailBoxDTO {

	private Integer mailboxId;

	private EmployeePerformenceMasterNewDTO employeePerformenceMaster;

	private String fromEmail;

	private String toEmail;

	private String subject;

	private String emailBody;
}
