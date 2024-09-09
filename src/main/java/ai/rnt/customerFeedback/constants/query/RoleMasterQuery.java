package ai.rnt.customerFeedback.constants.query;

public class RoleMasterQuery {

	private RoleMasterQuery() {
		
	}
	
	public static final String FINDROLEIN ="SELECT new EmployeeMaster(em.staffId,em.firstName, em.lastName,em.departureDate) "
			+ "FROM #{#entityName} as rm join rm.employees ur,EmployeeMaster as em "
			+ "WHERE rm.roleName In(?#{[0]}) and ur.deletedBy is null and em.staffId=ur "
			+ " and em.deletedBy is null group by em.staffId ";
	
	public static final String FINDROLEFROMSTAFFID ="SELECT r.role,r.role_id FROM role_master r LEFT JOIN employee_master e ON r.role_id=e.role_id WHERE e.staff_id=?1";
}