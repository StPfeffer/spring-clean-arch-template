package com.pfeffer.springcleanarchtemplate.domain.usecase.user;

import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.UserResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.mapper.UserMapper;
import com.pfeffer.springcleanarchtemplate.domain.exception.LocalizedException;
import com.pfeffer.springcleanarchtemplate.domain.exception.SimpleErrorCode;
import com.pfeffer.springcleanarchtemplate.domain.gateway.UserGateway;

public class FindUserById {

    private final UserGateway gateway;

    public FindUserById(UserGateway gateway) {
        this.gateway = gateway;
    }

    public UserResponseDTO execute(Long id) {
        UserBO user = gateway.findById(id)
                .orElseThrow(() -> new LocalizedException(SimpleErrorCode.NOT_FOUND, "user", "id"));

        return UserMapper.toResponseDTO(user);
    }

}
