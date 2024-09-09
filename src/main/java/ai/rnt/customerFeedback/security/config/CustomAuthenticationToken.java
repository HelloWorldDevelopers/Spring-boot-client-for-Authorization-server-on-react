package ai.rnt.customerFeedback.security.config;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Object details;

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Object details) {
        super(principal, credentials, authorities);
        this.details = details;
        setAuthenticated(true);
    }

    @Override
    public Object getDetails() {
        return details;
    }
}
