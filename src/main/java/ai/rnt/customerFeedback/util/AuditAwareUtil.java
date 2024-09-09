package ai.rnt.customerFeedback.util;

import static ai.rnt.customerFeedback.constants.RoleConstants.NO_ROLE;
import static ai.rnt.customerFeedback.util.RoleUtil.CHECK_ADMIN;
import static ai.rnt.customerFeedback.util.RoleUtil.CHECK_USER;
import static java.util.Objects.nonNull;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import java.security.Principal;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import ai.rnt.customerFeedback.security.JWTTokenHelper;
import ai.rnt.customerFeedback.security.UserDetail;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditAwareUtil {

	private final JWTTokenHelper helper;

	public Integer getLoggedInStaffId() {
		if (nonNull(getContext()) && nonNull(getContext().getAuthentication())
				&& nonNull(getContext().getAuthentication().getDetails())) {
			UserDetail details = (UserDetail) getContext().getAuthentication().getDetails();
			return details.getStaffId();
		}
		return null;
	}

	public String getLoggedInUserRole() {
		if (nonNull(getContext()) && nonNull(getContext().getAuthentication())
				&& nonNull(getContext().getAuthentication().getDetails())) {
			UserDetail details = (UserDetail) getContext().getAuthentication().getDetails();
			return RoleUtil.getSingleRole(
					details.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		}
		return NO_ROLE;
	}

	public String getLoggedInUserName(Principal principal) {
		 
		return principal.getName();
	}

	public boolean isAdmin() {
		return CHECK_ADMIN.test(getLoggedInUserRole());
	}

	public boolean isUser() {
		return CHECK_USER.test(getLoggedInUserRole());
	}

}
