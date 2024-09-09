package ai.rnt.customerFeedback.util;

import static ai.rnt.customerFeedback.constants.RoleConstants.CRM_ADMIN;
import static ai.rnt.customerFeedback.constants.RoleConstants.CRM_USER;
import static ai.rnt.customerFeedback.constants.RoleConstants.NO_ROLE;
import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.rnt.customerFeedback.dto.RBAC;

public class RoleUtil {
	private RoleUtil() {
	}

	/**
	 * This Predicate return true if it contains the Role.
	 * 
	 * @since version 1.0
	 */
	public static final Predicate<String> ALLOW_ROLES = s -> {
		if (isNull(s))
			return false;
		return RoleUtil.APP_ROLES.get().stream().anyMatch(s::equalsIgnoreCase);
	};

	public static final Supplier<List<String>> APP_ROLES = () -> Arrays.asList(CRM_ADMIN, CRM_USER);

	public static final UnaryOperator<String> GET_ROLE = s -> {
		if (CRM_ADMIN.equalsIgnoreCase(s))
			return CRM_ADMIN;
		else if (CRM_USER.equalsIgnoreCase(s))
			return CRM_USER;
		return NO_ROLE;
	};

	/**
	 * This Predicate return true if it contains the CRM Admin Role.
	 * 
	 * @since version 1.0
	 */
	public static final Predicate<String> CHECK_ADMIN = s -> {
		if (isNull(s))
			return false;
		return CRM_ADMIN.equalsIgnoreCase(s);
	};
	/**
	 * This Predicate return true if it contains the CRM User Role.
	 * 
	 * @since version 1.0
	 */
	public static final Predicate<String> CHECK_USER = s -> {
		if (isNull(s))
			return false;
		return CRM_USER.equalsIgnoreCase(s);
	};

	public static final String getSingleRole(List<String> roles) {
		for (String s : roles) {
			if (CRM_ADMIN.equalsIgnoreCase(s))
				return CRM_ADMIN;
		}
		for (String s : roles) {
			if (CRM_USER.equalsIgnoreCase(s))
				return CRM_USER;
		}
		return NO_ROLE;
	}

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String serializeObjectToJson(Object obj) throws Exception {
		return objectMapper.writeValueAsString(obj);
	}

	public static List<String> getRoleListFromJsonArray(JSONArray jArray) throws JSONException {
		List<String> returnList = new ArrayList<>();
		if (Objects.nonNull(jArray)) {
			for (int i = 0; i < jArray.length(); i++) {
				String val = jArray.getString(i);
				returnList.add(val);
			}
		}
		return returnList;
	}

	public static List<RBAC> convertJsonToList(JSONArray rbacArray2) {
		List<RBAC> rbacList = new ArrayList<>();
		try {
			for (int i = 0; i < rbacArray2.length(); i++) {
				JSONObject rbacItem = rbacArray2.getJSONObject(i);
				RBAC r = new RBAC();
				String editAccess = rbacItem.getString("editAccess");
				String useCase = rbacItem.getString("useCase");
				boolean moduleAccess = rbacItem.getBoolean("moduleAccess");
				int useCaseId = rbacItem.getInt("useCaseId");
				String readAccess = rbacItem.getString("readAccess");
				String deleteAccess = rbacItem.getString("deleteAccess");
				String writeAccess = rbacItem.getString("writeAccess");
				String message = rbacItem.optString("message");

				r.setEditAccess(editAccess);
				r.setDeleteAccess(deleteAccess);
				r.setReadAccess(readAccess);
				r.setWriteAccess(writeAccess);
				r.setUseCase(useCase);
				r.setModuleAccess(moduleAccess);
				r.setUseCaseId(useCaseId);
				r.setMessage(message);

				rbacList.add(r);

			}
		} catch (JSONException e) {

		}
		return rbacList;
	}

}
