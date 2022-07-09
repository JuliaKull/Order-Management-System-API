package com.bta.mapper;

import com.bta.dto.UserAccountDTO;
import com.bta.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserAccountWebMapper implements WebMapper<UserAccountDTO, UserAccount>{
    @Override
    public UserAccountDTO toDTO(UserAccount entity) {
        return UserAccountDTO.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .created(entity.getCreated())
                .build();
    }

    @Override
    public UserAccount toEntity(UserAccountDTO dto) {
        return UserAccount.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
