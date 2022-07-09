package com.bta.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Getter
@Builder
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_account")
public class UserAccount extends AbstractBaseEntity {

    private String username;
    private String password;
    private boolean active;
    private ZonedDateTime created;
    private String email;

    @OneToOne(mappedBy = "userAccount", fetch = FetchType.LAZY)
    private ActivationLink activationLink;



}
