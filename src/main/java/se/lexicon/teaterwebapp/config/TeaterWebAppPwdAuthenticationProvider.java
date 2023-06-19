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
import se.lexicon.teaterwebapp.model.Dto.RoleDto;
import se.lexicon.teaterwebapp.model.entity.Member;
import se.lexicon.teaterwebapp.model.entity.Role;
import se.lexicon.teaterwebapp.model.entity.User;
import se.lexicon.teaterwebapp.model.entity.Authority;
import se.lexicon.teaterwebapp.repository.MemberRepository;
import se.lexicon.teaterwebapp.repository.UserRepository;
//import se.lexicon.teaterwebapp.repository.MemberRepository;
//import se.lexicon.teaterwebapp.repository.UserRepository;

import java.util.*;

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
       List<User> userList = userRepository.findByEmail(username);
       if (userList.size() > 0) {
           if (passwordEncoder.matches(password, userList.get(0).getPassword())) {
               Set<Role> roles = userList.get(0).getRoles();
               List<GrantedAuthority> authorities = new ArrayList<>();
               for (Role role : roles) {
                   authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
               }
               return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(userList.get(0).getAuthorities()));
           } else {
               throw new BadCredentialsException("Invalid password!");
           }
       } else {
           throw new BadCredentialsException("No user registered with these details!");
       }
   }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

