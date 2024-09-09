package ai.rnt.customerFeedback.repository;

import static ai.rnt.customerFeedback.constants.query.RoleMasterQuery.FINDROLEFROMSTAFFID;
import static ai.rnt.customerFeedback.constants.query.RoleMasterQuery.FINDROLEIN;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.rnt.customerFeedback.entity.EmployeeMaster;
import ai.rnt.customerFeedback.entity.RoleMaster;

public interface RoleMasterRepository extends JpaRepository<RoleMaster, Integer> {

	@Query(FINDROLEIN)
	List<EmployeeMaster> findByEmployeeRoleIn(List<String> role);

	@Query(value = FINDROLEFROMSTAFFID, nativeQuery = true)
	List<Object[]> getRoleNameByStaffId(Integer staffId);

	@Query(value = "SELECT u.use_case_name AS use_case, "
			+ " r.read_access,r.write_access, r.edit_access,  r.delete_access "
			+ " FROM rbac_master r, user_role ur , use_cases u , project am  "
			+ " WHERE ur.role_id=r.role_id AND ur.user_id=?1" + " AND u.project_id=am.Project_ID  "
			+ " AND am.project_name='Customer Feedback' " + " AND r.use_case_id=u.use_case_id  "
			+ " AND ur.start_Date <= current_timestamp()  " + " AND ur.end_date >= current_timestamp()  "
			+ " AND ur.role_id=r.role_id  " + " AND r.deleted_by IS NULL  "
			+ " AND u.deleted_by IS NULL;", nativeQuery = true)
	List<Object[]> getRbacList(Integer staffId);

	@Query(value = "SELECT rm.role FROM role_master rm LEFT JOIN user_role ur ON rm.role_id = ur.role_id WHERE ur.deleted_by IS NULL AND ur.end_date>=curdate() AND ur.user_id = ?1", nativeQuery = true)
	List<String> getRoleList(Integer staffId);

}
