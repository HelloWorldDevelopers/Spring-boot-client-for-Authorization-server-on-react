package ai.rnt.customerFeedback.constants;

public final class ApiConstants {

	private ApiConstants() {

	}

	public static final String SEPERATOR = "/";

	public static final String API = "api";

	public static final String VERSION = "v1";

	public static final String BASE = SEPERATOR + API + SEPERATOR + VERSION + SEPERATOR;

	public static final String AUTH = BASE + "auth" + SEPERATOR;

	public static final String LOGIN = SEPERATOR + "login";

	public static final String TOKENPARSE = SEPERATOR + "tokenparse";

	public static final String GET_ADMIN_AND_USER = SEPERATOR + "getAdminAndUser";

	public static final String ID = "/{id}";

	public static final String QUESTION = "question";

	public static final String CUSTOMER_FEEDBACK_MASTER = "customerFeedbackMaster";

	public static final String CUSTOMER_FEEDBACK = "customerFeedback";

	public static final String CUSTOMER_SATISFACTION_MASTER = "customerSatisfactionMaster";

	public static final String CUSTOMER_SATISFACTION_SURVEY = "customerSatisfactionSurvey";

	public static final String KYCVENDOR_FORM = "kycvendorform";

	public static final String ALL_CUSTOMER = "/getAllCustomer";

	public static final String PROJECT = "/project";

}
