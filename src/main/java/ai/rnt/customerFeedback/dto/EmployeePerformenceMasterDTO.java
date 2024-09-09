package ai.rnt.customerFeedback.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePerformenceMasterDTO {
	private Integer empPerformenceId;

	private Integer sortStatus;
	
	private MailBoxDTO employeePerformenceMailBox;
	
	private EmployeeMasterDTO employeeMaster;

	private ProjectDTOEmployee project;
	
	private String projectManagerName;

	private String projectManagerEmail;


	private String filledDate;

	private LocalDateTime createdDate;
	
	private String createdDateForm;

	private Boolean filled;
	
	private Boolean isEmailSend;

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
		updateParseDate(); // Call a method to update fullName
	}

	private void updateParseDate() {
		this.createdDateForm = this.createdDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}
}
