package ai.rnt.customerFeedback.security.config;

import static ai.rnt.customerFeedback.constants.RoleConstants.CUFEE_CUSTOMER_SATISFACTIONSURVEY;
import static ai.rnt.customerFeedback.constants.RoleConstants.CUFEE_EMPLOYEE_FEEDBACK;
import static ai.rnt.customerFeedback.constants.RoleConstants.CUFEE_PROJECT_ENDFEED_BACK;
import static ai.rnt.customerFeedback.constants.RoleConstants.CUFEE_VENDORONBOARDING;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.dto.EmployeeDto;
import ai.rnt.customerFeedback.dto.RBAC;
import ai.rnt.customerFeedback.exception.ResourceNotFoundException;
import ai.rnt.customerFeedback.security.UserDetail;
import ai.rnt.customerFeedback.service.EmployeeService;
import ai.rnt.customerFeedback.service.impl.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetails implements UserDetailsService {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RoleServiceImpl roleService;

	@Override
	public UserDetail loadUserByUsername(String userId) throws UsernameNotFoundException {
		try {
			EmployeeDto user = employeeService.getEmployeeByUserId(userId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee", "userId", userId));
			List<String> roles = roleService.getRoleList(user.getStaffId());
			List<RBAC> rbacList = roleService.getRbacList(user.getStaffId());
			rbacList.stream().forEach(e -> {
				if (e.getUseCase().equalsIgnoreCase(CUFEE_PROJECT_ENDFEED_BACK)) {
					roles.add("CustomerProjectEndFeedback");
				}
				if (e.getUseCase().equalsIgnoreCase(CUFEE_CUSTOMER_SATISFACTIONSURVEY)) {
					roles.add("CustomerSatisfactionSurvey");
				}
				if (e.getUseCase().equalsIgnoreCase(CUFEE_EMPLOYEE_FEEDBACK)) {
					roles.add("CustomerEmployeeFeedback");
				}
				if (e.getUseCase().equalsIgnoreCase(CUFEE_VENDORONBOARDING)) {
					roles.add("CustomerVendorOnboarding");
				}
			});

			List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
			if (Objects.nonNull(user))
				return new UserDetail(user.getUserID(), user.getPassword(), true, true, true, true, authorities,
						user.getStaffId(), user.getEmailID());
		} catch (Exception e) {
			log.error("Exception occurred while loading user by name... {}", userId, e);
		}
		throw new UsernameNotFoundException("User not found with username: " + userId);
	}

}
