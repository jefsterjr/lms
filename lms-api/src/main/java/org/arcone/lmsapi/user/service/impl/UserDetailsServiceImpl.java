package org.arcone.lmsapi.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.arcone.lmsapi.auth.model.UserDetailsImpl;
import org.arcone.lmsapi.user.model.entity.User;
import org.arcone.lmsapi.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(), user.getUsername(), user.getRole());
    }


}
