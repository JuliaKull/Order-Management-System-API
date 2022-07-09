package com.bta.service;


import com.bta.dto.UserAccountDTO;
import com.bta.model.UserAccount;

public interface UserAccountService {

    void create(UserAccountDTO userAccount);

    void activate(String code);

    void delete(UserAccountDTO userAccount);
}
