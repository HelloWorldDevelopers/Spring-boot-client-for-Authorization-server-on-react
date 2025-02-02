package ai.rnt.customerFeedback.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDto {

	private Integer staffId;

	private String userID;
	private String password;

	private String firstName; // first_name

	private String middleName; // middle_name

	private String lastName;

	private String emailID;

	private String employeeJobTitle;

	private List<Role> employeeRole = new ArrayList<>();

}
