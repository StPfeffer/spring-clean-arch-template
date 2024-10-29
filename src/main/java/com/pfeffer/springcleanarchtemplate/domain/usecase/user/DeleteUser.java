package com.pfeffer.springcleanarchtemplate.domain.usecase.user;

import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.UserResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.mapper.UserMapper;
import com.pfeffer.springcleanarchtemplate.domain.gateway.UserGateway;

import java.time.LocalDateTime;

public class DeleteUser {

    private final UserGateway gateway;

    public DeleteUser(UserGateway gateway) {
        this.gateway = gateway;
    }

    public void execute(Long id) {
        UserResponseDTO user = new FindUserById(gateway).execute(id);

        user.setDeletedAt(LocalDateTime.now());

        UserBO bo = UserMapper.toBO(user);
        gateway.save(bo);
    }

}
