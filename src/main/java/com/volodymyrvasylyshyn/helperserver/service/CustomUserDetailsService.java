package com.volodymyrvasylyshyn.helperserver.service;



import com.volodymyrvasylyshyn.helperserver.exeptions.EmailNotFoundException;
import com.volodymyrvasylyshyn.helperserver.model.User;
import com.volodymyrvasylyshyn.helperserver.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new EmailNotFoundException("User not found with email " + email));
    return build(user);

    }

    public User loadUserById(Long id){
        return userRepository.findUserById(id).orElse(null);



    }
    public static User build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
        return new User(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
}
