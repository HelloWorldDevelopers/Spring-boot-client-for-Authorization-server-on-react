package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeMasterDTO {
	private Integer staffId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String emailId;
	private String userId;
	

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		updateFullName();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		updateFullName();
	}

	private void updateFullName() {
		this.fullName = this.firstName + " " + this.lastName;
	}
	
	
}
