package ai.rnt.customerFeedback.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ai.rnt.customerFeedback.dto.RBAC;
import ai.rnt.customerFeedback.entity.RoleMaster;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.repository.RoleMasterRepository;

class RoleServiceImplTest {
	@Autowired
	MockMvc mockMvc;

	@Mock
	RoleMasterRepository roleMasterRepository;

	@InjectMocks
	RoleServiceImpl roleService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(roleService).build();
	}

	@Test
	void testGetAllRoles() {
		List<RoleMaster> list = new ArrayList<>();
		RoleMaster employeeMaster = new RoleMaster();
		list.add(employeeMaster);
		when(roleMasterRepository.findAll()).thenReturn(list);
		ResponseEntity<EnumMap<ApiResponse, Object>> responseEntity = roleService.getAllRoles();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue((Boolean) responseEntity.getBody().get(ApiResponse.SUCCESS));
		assertNotNull(responseEntity.getBody().get(ApiResponse.DATA));
		verify(roleMasterRepository).findAll();
	}

	@Test
	void testgetRbacList() {
		Integer staffId = 123; // Example staffId
		Object[] data1 = { "useCase1", "Y", "N", "Y", "N" }; // Example data row
		Object[] data2 = { "useCase2", "N", "Y", "N", "Y" }; // Example data row
		List<Object[]> mockData = new ArrayList<>();
		mockData.add(data1);
		mockData.add(data2);

		when(roleMasterRepository.getRbacList(staffId)).thenReturn(mockData);

		List<RBAC> result = roleService.getRbacList(staffId);

		assertNotNull(result);
		assertEquals(2, result.size());

		RBAC rbac1 = result.get(0);
		assertEquals("useCase1", rbac1.getUseCase());
		assertEquals("Y", rbac1.getReadAccess());
		assertEquals("N", rbac1.getWriteAccess());
		assertEquals("Y", rbac1.getEditAccess());
		assertEquals("N", rbac1.getDeleteAccess());
		assertTrue(rbac1.isModuleAccess());

		RBAC rbac2 = result.get(1);
		assertEquals("useCase2", rbac2.getUseCase());
		assertEquals("N", rbac2.getReadAccess());
		assertEquals("Y", rbac2.getWriteAccess());
		assertEquals("N", rbac2.getEditAccess());
		assertEquals("Y", rbac2.getDeleteAccess());
		assertTrue(rbac2.isModuleAccess());

	}

}
