package ai.rnt.customerFeedback.dao;

import java.util.List;

import ai.rnt.customerFeedback.entity.EmployeeMaster;

public interface RoleMasterDaoService {

	List<EmployeeMaster> getAdminAndUser();

}

