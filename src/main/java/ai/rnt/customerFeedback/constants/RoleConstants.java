package ai.rnt.customerFeedback.constants;

public class RoleConstants {

	private RoleConstants() {

	}

	public static final String CRM_ADMIN = "CustomerFeedBackAdmin";
	public static final String CUSTOMER_PROJECT_END_FEEDBACK = "CustomerProjectEndFeedback";
	public static final String CUSTOMER_SATISFACTION_SURVEY = "CustomerSatisfactionSurvey";
	public static final String CUSTOMER_EMPLOYEE_FEEDBACK = "CustomerEmployeeFeedback";
	public static final String CUSTOMER_VENDOR_ONBOARDING = "CustomerVendorOnboarding";
	
	public static final String CUFEE_PROJECT_ENDFEED_BACK = "CUFEE ProjectEndFeedback";
	public static final String CUFEE_CUSTOMER_SATISFACTIONSURVEY = "CUFEE CustomerSatisfactionSurvey";
	public static final String CUFEE_EMPLOYEE_FEEDBACK = "CUFEE EmployeeFeedback";
	public static final String CUFEE_VENDORONBOARDING = "CUFEE VendorOnboarding";

	public static final String HAS_AUTHORITY = "hasAuthority('";
	
	public static final String HAS_AUTHORITY_CLOSER = "')";

	public static final String CRM_USER = "CustomerFeedBackUser";

	//public static final String CHECK_BOTH_ACCESS = HAS_AUTHORITY + CRM_ADMIN + "')" + " || "+HAS_AUTHORITY+ CRM_USER
	//		+ "')";

	//public static final String CHECK_ADMIN_ACCESS = HAS_AUTHORITY + CRM_ADMIN + "')";

	//public static final String CHECK_USER_ACCESS = HAS_AUTHORITY + CRM_USER + "')";

	public static final String NO_ROLE = "Don't Have Role";
}
