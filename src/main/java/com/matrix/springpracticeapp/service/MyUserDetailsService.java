package com.matrix.springpracticeapp.service;

import com.matrix.springpracticeapp.entity.AuthorityEntity;
import com.matrix.springpracticeapp.entity.UserEntity;
import com.matrix.springpracticeapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepository.findByUsername(username);
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getEnabled(),
                true, true, true,
                getAuthorities(userEntity)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity userEntity){
        List<AuthorityEntity> roles = userEntity.getAuthorityEntityList();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (AuthorityEntity role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getAuthority().name()));
        }
        return authorities;
    }



}
