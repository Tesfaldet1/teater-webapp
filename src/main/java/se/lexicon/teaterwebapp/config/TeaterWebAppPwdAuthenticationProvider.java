package se.lexicon.teaterwebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.lexicon.teaterwebapp.model.entity.Member;
import se.lexicon.teaterwebapp.model.entity.User;
//import se.lexicon.teaterwebapp.repository.MemberRepository;
//import se.lexicon.teaterwebapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
/*
@Component
public class TeaterWebAppPwdAuthenticationProvider implements AuthenticationProvider{
    @Autowired
    private MemberRepository memberRepository;
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<User> users = userRepository.findByEmail(username);
        if (users.size() > 0) {
            if (passwordEncoder.matches(password, users.get(0).getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(users.get(0).getEmail()));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

 */
