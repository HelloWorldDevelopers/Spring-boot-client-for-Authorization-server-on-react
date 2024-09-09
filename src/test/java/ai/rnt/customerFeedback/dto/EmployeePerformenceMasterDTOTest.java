package ai.rnt.customerFeedback.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class EmployeePerformenceMasterDTOTest {

	@Test
	void testGetAndSetEmpPerformenceId() {
		Integer expectedId = 1;
		EmployeePerformenceMasterDTO dto = new EmployeePerformenceMasterDTO();
		dto.setEmpPerformenceId(expectedId);
		assertEquals(expectedId, dto.getEmpPerformenceId());
	}

	@Test
	void testUpdateParseDate() {
		EmployeePerformenceMasterDTO dto = new EmployeePerformenceMasterDTO();
		LocalDateTime createdDate = LocalDateTime.of(2022, 4, 10, 12, 30);
		dto.setCreatedDate(createdDate);
		String expectedParseDate = createdDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		assertEquals(expectedParseDate, dto.getCreatedDateForm());
	}

	@Test
	void testUpdateParseDate2() {
		EmployeePerformenceMasterDTO dto = new EmployeePerformenceMasterDTO();
		LocalDateTime createdDate = LocalDateTime.of(2022, 4, 10, 12, 30);
		dto.setCreatedDate(createdDate);
		String expectedParseDate = createdDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		java.lang.reflect.Field parseDateField;
		String actualParseDate = null;
		try {
			parseDateField = EmployeePerformenceMasterDTO.class.getDeclaredField("createdDateForm");
			parseDateField.setAccessible(true);
			actualParseDate = (String) parseDateField.get(dto);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		assertEquals(expectedParseDate, actualParseDate);
	}
}
