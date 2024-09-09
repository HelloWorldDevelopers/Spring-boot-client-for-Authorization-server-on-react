package ai.rnt.customerFeedback.service;

import java.util.EnumMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.dto.RBAC;
import ai.rnt.customerFeedback.enums.ApiResponse;

public interface RoleService {
	ResponseEntity<EnumMap<ApiResponse, Object>> getAllRoles();

	public List<RBAC> getRbacList(Integer staffId);

	List<String> getRoleList(Integer staffId);
}
