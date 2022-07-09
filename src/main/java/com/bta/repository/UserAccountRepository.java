package com.bta.repository;

import com.bta.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

    UserAccount findByUsernameAndActive(String username, boolean active);
}
