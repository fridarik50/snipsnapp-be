package hackeru.fridarik.snipsnapp.security;

import hackeru.fridarik.snipsnapp.entity.AuthEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserAuthDetails  implements UserDetails {
    private final String email;
    private final String password;
    private final List<GrantedAuthority> authorityList;

    private static final String[] roles = {"BARBER", "CUSTOMER"};
    public UserAuthDetails( AuthEntity user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorityList = Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
