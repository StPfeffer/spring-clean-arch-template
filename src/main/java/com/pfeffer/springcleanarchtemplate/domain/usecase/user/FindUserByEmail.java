package com.pfeffer.springcleanarchtemplate.domain.usecase.user;

import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.UserResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.mapper.UserMapper;
import com.pfeffer.springcleanarchtemplate.domain.exception.LocalizedException;
import com.pfeffer.springcleanarchtemplate.domain.exception.SimpleErrorCode;
import com.pfeffer.springcleanarchtemplate.domain.gateway.UserGateway;

public class FindUserByEmail {

    private final UserGateway gateway;

    public FindUserByEmail(UserGateway gateway) {
        this.gateway = gateway;
    }

    public UserResponseDTO execute(String email) {
        UserBO user = gateway.findByEmail(email)
                .orElseThrow(() -> new LocalizedException(SimpleErrorCode.NOT_FOUND, "user", "email"));

        return UserMapper.toResponseDTO(user);
    }

}
