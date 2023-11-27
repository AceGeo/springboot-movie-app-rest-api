package com.springboot.movie.app.springbootmovieapprestapi.security;

import com.springboot.movie.app.springbootmovieapprestapi.entity.User;
import com.springboot.movie.app.springbootmovieapprestapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userNameorEmail) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameOrEmail(userNameorEmail, userNameorEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + userNameorEmail));

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authorities);
    }
}
