package ai.rnt.customerFeedback.service.impl;

import static ai.rnt.customerFeedback.dto_mapper.EmployeeToDtoMapper.TO_EMPLOYEE_MASTER_DTO_LIST;
import static ai.rnt.customerFeedback.dto_mapper.ProjectDTOMapper.TO_PROJECT_DTO_LIST;
import static ai.rnt.customerFeedback.dto_mapper.ProjectTeamLeadDTOMapper.TO_PROJECT_TEAM_DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.dto.ProjectDto;
import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.entity.EmployeePerformenceMaster;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.entity.ProjectTeam;
import ai.rnt.customerFeedback.enums.ApiResponse;
import ai.rnt.customerFeedback.exception.CRMException;
import ai.rnt.customerFeedback.repository.EmployeeMasterRepository;
import ai.rnt.customerFeedback.repository.EmployeePerformenceMasterRepository;
import ai.rnt.customerFeedback.repository.ProjectRepository;
import ai.rnt.customerFeedback.repository.ProjectTeamRepository;
import ai.rnt.customerFeedback.service.ProjectTeamService;

@Service
public class ProjectTeamServiceImpl implements ProjectTeamService {
	@Autowired
	ProjectTeamRepository projectTeamRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	EmployeePerformenceMasterRepository employeePerformenceMasterRepository;

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getProjectListByStaffId(int staffid) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			List<Project> findByEmployeeMasterStaffId = projectTeamRepository.findByEmployeeMasterStaffId(staffid);
			List<ProjectDto> projectList = TO_PROJECT_DTO_LIST.apply(findByEmployeeMasterStaffId);
			if (projectList.isEmpty()) {
				resultMap.put(ApiResponse.SUCCESS, false);
				resultMap.put(ApiResponse.MESSAGE, "Employee Doesn't Have A Project...{}");
			} else {
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.DATA, projectList);
			}
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getProjectManagerListByProjectId(int staffId, int projectId,
			Integer empPerformenceId) {
		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);

		try {
			List<EmployeeMaster> employeeMasters = new ArrayList<>();
			Project project = projectRepository.findById(projectId).orElse(null);
			if (Objects.nonNull(project)) {

				if (project.getProjectManager() == 1 || project.getProjectManager() == 2
						|| project.getProjectManager() == 3) {
					Iterable<Integer> staffId1 = Arrays.asList(1, 2, 3);
					employeeMasters = employeeMasterRepository.findAllById(staffId1);
				} else {
					Iterable<Integer> staffId1 = Arrays.asList(1, 2, 3, project.getProjectManager());
					employeeMasters = employeeMasterRepository.findAllById(staffId1);
				}
			} else {

				Iterable<Integer> staffId1 = Arrays.asList(1, 2, 3);
				employeeMasters = employeeMasterRepository.findAllById(staffId1);
			}
			if (empPerformenceId != null) {

				EmployeePerformenceMaster employeePerformenceMaster = employeePerformenceMasterRepository
						.findById(empPerformenceId).orElse(null);
				if (Objects.nonNull(employeePerformenceMaster)) {
					EmployeeMaster master = new EmployeeMaster();
					master.setFirstName(employeePerformenceMaster.getProjectManagerName());
					master.setLastName("");
					boolean flag = employeeMasters.stream().map(e -> e.getFirstName() + " " + e.getLastName())
							.anyMatch(fullName -> fullName.equalsIgnoreCase(master.getFirstName().trim()));

					if (!flag) {
						employeeMasters.add(master);
					}

				}
			}

			Collections.sort(employeeMasters, new Comparator<EmployeeMaster>() {
				@Override
				public int compare(EmployeeMaster e1, EmployeeMaster e2) {
					int firstNameComparison = e1.getFirstName().compareTo(e2.getFirstName());
					if (firstNameComparison != 0) {
						return firstNameComparison;
					}
					return e1.getLastName().compareTo(e2.getLastName());
				}
			});

			if (!employeeMasters.isEmpty()) {
				resultMap.put(ApiResponse.SUCCESS, true);
				resultMap.put(ApiResponse.DATA, TO_EMPLOYEE_MASTER_DTO_LIST.apply(employeeMasters));
			} else {
				resultMap.put(ApiResponse.SUCCESS, false);
				resultMap.put(ApiResponse.MESSAGE, "Project Doesn't Have A Project Manager...{}");
			}
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			throw new CRMException(e);
		}
	}

	@Override
	public ResponseEntity<EnumMap<ApiResponse, Object>> getProjectTeamById(int id) {

		EnumMap<ApiResponse, Object> resultMap = new EnumMap<>(ApiResponse.class);
		try {
			ProjectTeam projectTeam = projectTeamRepository.findById(id).orElse(null);
			resultMap.put(ApiResponse.SUCCESS, true);
			resultMap.put(ApiResponse.DATA, TO_PROJECT_TEAM_DTO.apply(projectTeam));
			return new ResponseEntity<>(resultMap, HttpStatus.FOUND);
		} catch (Exception e) {
			throw new CRMException(e);
		}

	}

}
