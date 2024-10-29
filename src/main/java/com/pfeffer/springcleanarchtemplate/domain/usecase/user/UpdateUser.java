package com.pfeffer.springcleanarchtemplate.domain.usecase.user;

import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.request.UserRequestDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.UserResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.mapper.UserMapper;
import com.pfeffer.springcleanarchtemplate.domain.gateway.UserGateway;

public class UpdateUser {

    private final UserGateway gateway;

    public UpdateUser(UserGateway gateway) {
        this.gateway = gateway;
    }

    public UserResponseDTO execute(Long id, UserRequestDTO dto) {
        findExisting(id, dto);

        UserBO bo = UserMapper.toBO(dto);
        bo = gateway.save(bo);

        return UserMapper.toResponseDTO(bo);
    }

    private void findExisting(Long id, UserRequestDTO dto) {
        gateway.findById(id)
                .ifPresent(existingUser -> dto.setId(existingUser.getId()));
    }

}
