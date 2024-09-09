package ai.rnt.customerFeedback.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ai.rnt.customerFeedback.dao.EmployeeDaoService;
import ai.rnt.customerFeedback.dao.RoleMasterDaoService;
import ai.rnt.customerFeedback.dto.EmployeeDto;
import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
import ai.rnt.customerFeedback.repository.EmployeeProfileRepository;
import ai.rnt.customerFeedback.repository.RoleMasterRepository;

class EmployeeServiceImplTest {
	@Autowired
	MockMvc mockMvc;

	@Mock
	private EmployeeDaoService employeeDaoService;

	@Mock
	private EmployeeMasterRepository employeeMasterRepository;

	@Mock
	private RoleMasterRepository roleMasterRepository;

	@Mock
	private RoleMasterDaoService roleMasterDaoService;

	@Mock
	private EmployeeProfileRepository employeeProfileRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeService).build();
	}

	@Test
    void testgetEmployeeByUserId() {
    	when(employeeDaoService.getEmployeebyUserId(any())).thenReturn(Optional.ofNullable(new EmployeeMaster()));
		assertNotNull(employeeService.getEmployeeByUserId("1613"));
		assertNotNull(employeeService.getEmployeeByUserId("1613"));
    }

	@Test
	void testGetAdminAndUser2() {
		assertNotNull(employeeService.getAdminAndUser());
	}

	@Test
	void testgetById() {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setUserID("dn1388");
		employeeDto.setStaffId(1388);
		when(employeeDaoService.getById(1388)).thenReturn(Optional.of(employeeDto));
		assertNotNull(employeeService.getById(anyInt()));
	}

	@Test
    void testgetByIdThrowsException() {
       
        when(employeeDaoService.getById(anyInt())).thenThrow(new RuntimeException("Test exception"));

        CRMException exception = assertThrows(CRMException.class, () -> employeeService.getById(anyInt()));
        assertNotNull(exception.getCause());
        assertEquals("Test exception", exception.getCause().getMessage());

    }

	@Test
    void testGetAdminAndUserThrowsException() {
       
        when(roleMasterDaoService.getAdminAndUser()).thenThrow(new RuntimeException("Test exception"));

        CRMException exception = assertThrows(CRMException.class, () -> employeeService.getAdminAndUser());
        assertNotNull(exception.getCause());
        assertEquals("Test exception", exception.getCause().getMessage());

    }

	@Test
	void testGetAllEmployees2() {
		List<EmployeeMaster> list = new ArrayList<>();
		EmployeeMaster employeeMaster = new EmployeeMaster();
		list.add(employeeMaster);
		when(employeeMasterRepository.findAll(Sort.by("firstName").ascending())).thenReturn(list);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = employeeService.getAllEmployees();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue((Boolean) responseEntity.getBody().get(ApiResponse.SUCCESS));
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));
	}

	@Test
	void testGetEmployeeByStaffId_WhenEmployeeExistsAndIsActive() {
		int staffId = 123;
		EmployeeMaster employeeMaster = new EmployeeMaster();
		employeeMaster.setStaffId(staffId);
		employeeMaster.setEmailId("test@example.com");
		LocalDate today = LocalDate.now();
		LocalDate dateOfDeparture = today.plusDays(1); // Future date
		employeeMaster.setDepartureDate(dateOfDeparture);
		when(employeeMasterRepository.getById(anyInt())).thenReturn(employeeMaster);
		assertNotNull(employeeService.getEmployeeByStaffId(anyInt()));

	}

	@Test
	void testGetProfilePic_EmployeeNotFound() {
		int id = 1;
		when(employeeProfileRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(CRMException.class, () -> employeeService.getProfilePic(id));
	}

}
