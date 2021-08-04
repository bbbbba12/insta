package com.bk.insta.security.service;

import com.bk.insta.domain.entity.User;
import com.bk.insta.repository.UserRepository;
import com.bk.insta.security.details.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("\"Not Found 'email'\"");
        }

        return new PrincipalDetails(user);
    }
}
