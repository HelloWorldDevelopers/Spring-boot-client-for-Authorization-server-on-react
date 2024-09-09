package ai.rnt.customerFeedback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RBAC {
	//private int rbacId;
	//private int roleId;
	//private int rbacListById;
	//private String roleName;
	private String readAccess;
	private String writeAccess;
	private String editAccess;
	private String deleteAccess;
	private String useCase;
	private int useCaseId;
	private  String message;
	//private List<Role> roleList;
	//private List<RoleMaster> roleMasterList;
	//private List<RBAC> rBACList;
	//private List<UseCases> useCaseList;
	
	private boolean moduleAccess = false;
}
