package com.bta.security;

import com.bta.model.UserAccount;
import com.bta.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       final UserAccount userAccount = userAccountRepository.findByUsernameAndActive(username,true);
       if(userAccount==null){
           throw  new UsernameNotFoundException("User with name: "+username+" was not found in system");
       }

        return mapper.toUserDetails(userAccount);
    }
}
