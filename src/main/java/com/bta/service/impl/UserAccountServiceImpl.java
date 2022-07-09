package com.bta.service.impl;

import com.bta.dto.UserAccountDTO;
import com.bta.email.EmailService;
import com.bta.mapper.WebMapper;
import com.bta.model.ActivationLink;
import com.bta.model.UserAccount;
import com.bta.repository.ActivationLinkRepository;
import com.bta.repository.UserAccountRepository;
import com.bta.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private WebMapper<UserAccountDTO, UserAccount> webMapper;

    @Autowired
    private ActivationLinkRepository activationLinkRepository;

    @Autowired
    private EmailService emailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void create(UserAccountDTO userAccountDTO) {
        checkIfUserAccountExist(userAccountDTO);
        final UserAccount newUserAccount = webMapper.toEntity(userAccountDTO);
        processNewUserAccount(newUserAccount);
        final UserAccount saveUserAccount = userAccountRepository.save(newUserAccount);
        final ActivationLink activationLink = createActivationLink(saveUserAccount);
        activationLinkRepository.save(activationLink);
        final String link = "http://localhost:8081/user-account/activate?code=" + activationLink.getCode();
        emailSender.sendEmail(userAccountDTO.getEmail(), link, "Please activate your Account");
    }
@Transactional
    public void activate(String code) {
        if(code ==null || code.isEmpty()){
            throw new RuntimeException("Activation code must not be null or empty!");
        }
        final var activationLink = activationLinkRepository.findByCode(code);
        checkActivationLink(activationLink,code);
        activationLink.getUserAccount().setActive(true);
        activationLinkRepository.delete(activationLink);
    }

    private void checkActivationLink(ActivationLink activationLink, String code){
        if (activationLink == null) {
            throw new RuntimeException("Invalid code on activation link: " + code);
        }

        final Duration between = Duration.between(activationLink.getCreated().toInstant(),ZonedDateTime.now().toInstant());
        final long waitingPeriodInDays = between.toDays();
        if (waitingPeriodInDays >= 1) {
            throw new RuntimeException("Invalid code on activation link " + code + "already expired");
        }
    }

    private ActivationLink createActivationLink(UserAccount userAccount) {
        return ActivationLink.builder()
                .created(ZonedDateTime.now())
                .code(UUID.randomUUID().toString())
                .userAccount(userAccount)
                .build();
    }


    private void checkIfUserAccountExist(UserAccountDTO userAccountDTO) {
        final var username = userAccountDTO.getUsername();
        final var userAccountFromDB = userAccountRepository.findByUsernameAndActive(username, false);
        if (userAccountFromDB != null) {
            throw new RuntimeException("User with username: " + username + " already registered");
        }
    }

    private void processNewUserAccount(UserAccount newUserAccount) {
       newUserAccount.setActive(false);
        newUserAccount.setCreated(ZonedDateTime.now());
        String encryptedPassword = passwordEncoder.encode(newUserAccount.getPassword());
        newUserAccount.setPassword(encryptedPassword);
        newUserAccount.setActive(true);
    }

    public void delete(UserAccountDTO userAccount){
        final var username = userAccount.getUsername();
        final var userAccountFromDB = userAccountRepository.findByUsernameAndActive(username, true);
        userAccountRepository.delete(userAccountFromDB);
    }
}
