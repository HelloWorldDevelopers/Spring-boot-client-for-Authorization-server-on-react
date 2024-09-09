package ai.rnt.customerFeedback.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetail extends User {

	private static final long serialVersionUID = 338308531428207638L;

	private Integer staffId;

	private String emailId;

	public UserDetail(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			Integer staffId, String emailId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.staffId = staffId;
		this.emailId = emailId;
	}

}
