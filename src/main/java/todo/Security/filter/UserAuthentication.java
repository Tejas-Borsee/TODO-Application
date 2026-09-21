package todo.Security.filter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    String userId;
    Map<String,Long> map = new HashMap<>();

    public UserAuthentication(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities,
            String userId) {

        super(principal, credentials, authorities);
        this.userId = userId;
    }

    public Long getValue(String key){
        return map.getOrDefault(key,0L);
    }

    public Long setValue(String key,Long value){
        return map.put(key,value);
    }
}
