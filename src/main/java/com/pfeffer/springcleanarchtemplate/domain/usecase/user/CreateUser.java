package com.pfeffer.springcleanarchtemplate.domain.usecase.user;

import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.request.UserRequestDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.UserResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.mapper.UserMapper;
import com.pfeffer.springcleanarchtemplate.domain.exception.LocalizedException;
import com.pfeffer.springcleanarchtemplate.domain.exception.SimpleErrorCode;
import com.pfeffer.springcleanarchtemplate.domain.gateway.UserGateway;

public class CreateUser {

    private final UserGateway gateway;

    public CreateUser(UserGateway gateway) {
        this.gateway = gateway;
    }

    public UserResponseDTO execute(UserRequestDTO dto) {
        verifyExisting(dto);

        UserBO bo = UserMapper.toBO(dto);
        bo = gateway.save(bo);

        return UserMapper.toResponseDTO(bo);
    }

    private void verifyExisting(UserRequestDTO dto) {
        UserBO bo = gateway.findByUsername(dto.getUsername())
                .orElse(null);

        if (bo != null) {
            throw new LocalizedException(SimpleErrorCode.ALREADY_EXISTS, "user", "username");
        }

        bo = gateway.findByEmail(dto.getEmail())
                .orElse(null);

        if (bo != null) {
            throw new LocalizedException(SimpleErrorCode.ALREADY_EXISTS, "user", "email");
        }
    }

}
