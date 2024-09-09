package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class EmployeeDtoTest {

    @Test
     void testEmployeeDto() {
        Integer expectedStaffId = 1;
        String expectedUserID = "john_doe";
        String expectedPassword = "securePassword";
        String expectedFirstName = "John";
        String expectedMiddleName = "M";
        String expectedLastName = "Doe";
        String expectedEmailID = "john.doe@example.com";
        String expectedEmployeeJobTitle = "Software Engineer";
        List<Role> expectedEmployeeRole = new ArrayList<>();

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setStaffId(expectedStaffId);
        employeeDto.setUserID(expectedUserID);
        employeeDto.setPassword(expectedPassword);
        employeeDto.setFirstName(expectedFirstName);
        employeeDto.setMiddleName(expectedMiddleName);
        employeeDto.setLastName(expectedLastName);
        employeeDto.setEmailID(expectedEmailID);
        employeeDto.setEmployeeJobTitle(expectedEmployeeJobTitle);
        employeeDto.setEmployeeRole(expectedEmployeeRole);

        assertEquals(expectedStaffId, employeeDto.getStaffId());
        assertEquals(expectedUserID, employeeDto.getUserID());
        assertEquals(expectedPassword, employeeDto.getPassword());
        assertEquals(expectedFirstName, employeeDto.getFirstName());
        assertEquals(expectedMiddleName, employeeDto.getMiddleName());
        assertEquals(expectedLastName, employeeDto.getLastName());
        assertEquals(expectedEmailID, employeeDto.getEmailID());
        assertEquals(expectedEmployeeJobTitle, employeeDto.getEmployeeJobTitle());
        assertEquals(expectedEmployeeRole, employeeDto.getEmployeeRole());

    }
}