package ai.rnt.customerFeedback.service;

import java.util.EnumMap;

import org.springframework.http.ResponseEntity;

import ai.rnt.customerFeedback.enums.ApiResponse;

public interface ProjectTeamService {

	ResponseEntity<EnumMap<ApiResponse, Object>> getProjectListByStaffId(int staffid);

	ResponseEntity<EnumMap<ApiResponse, Object>> getProjectManagerListByProjectId(int staffId, int projectId,
			Integer empPerformenceId);

	ResponseEntity<EnumMap<ApiResponse, Object>> getProjectTeamById(int id);

}
