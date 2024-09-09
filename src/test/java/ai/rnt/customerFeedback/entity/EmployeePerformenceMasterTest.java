package ai.rnt.customerFeedback.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeePerformenceMasterTest {

	@InjectMocks
	private EmployeePerformenceMaster employeePerformenceMaster;

	@Test
	void testGetAndSetEmpPerformenceId() {
		Integer expectedId = 1;

		employeePerformenceMaster.setEmpPerformenceId(expectedId);

		assertEquals(expectedId, employeePerformenceMaster.getEmpPerformenceId());
	}

	@Test
	void testFilledLogic() {
		employeePerformenceMaster.setFilled(true);
		assertEquals(true, employeePerformenceMaster.getFilled());

		employeePerformenceMaster.setFilled(false);
		assertEquals(false, employeePerformenceMaster.getFilled());

	}

	@Test
	void testParseDate() {
		employeePerformenceMaster.setFilledDate("22-03-2024");
		assertEquals("22-03-2024", employeePerformenceMaster.getFilledDate());

	}

}
