package ai.rnt.customerFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.entity.Project;
import ai.rnt.customerFeedback.entity.ProjectTeam;

public interface ProjectTeamRepository extends JpaRepository<ProjectTeam, Integer> {

	@Query("SELECT p FROM Project p LEFT JOIN p.employeeMaster e WHERE e.staffId = ?1 and p.projectEndDate >= current_date()")
	List<Project> findByEmployeeMasterStaffId(int staffId);

	@Query(value = "SELECT e FROM EmployeeMaster e " + " LEFT JOIN  e.projectTeamList pt " + " LEFT JOIN  pt.project p "
			+ " LEFT JOIN   pt.roleMaster rm " + "WHERE rm.roleName = 'Project Manager' AND p.projectId=?1 ")
	List<EmployeeMaster> getProjectManagerByProjectId(int projectId);

	@Query(value = "SELECT pt.* \r\n" + "FROM project_team pt, employee_master em, project p\r\n"
			+ "WHERE pt.project_id = p.project_id AND pt.staff_id = em.staff_id AND\r\n"
			+ "p.end_date >= current_date() AND pt.staff_id = ?1 AND pt.project_id = ?2", nativeQuery = true)
	List<ProjectTeam> findProjectTeamDetailsForStaff(int staffId, int projectId);

}
