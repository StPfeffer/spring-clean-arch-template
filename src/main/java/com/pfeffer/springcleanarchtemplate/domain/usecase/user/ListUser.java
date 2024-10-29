package com.pfeffer.springcleanarchtemplate.domain.usecase.user;

import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.PaginatedResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.UserResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.mapper.UserMapper;
import com.pfeffer.springcleanarchtemplate.domain.gateway.UserGateway;
import com.pfeffer.springcleanarchtemplate.domain.pagination.PageContent;
import com.pfeffer.springcleanarchtemplate.domain.pagination.Pagination;

import java.util.List;

public class ListUser {

    private final UserGateway gateway;

    public ListUser(UserGateway gateway) {
        this.gateway = gateway;
    }

    public List<UserResponseDTO> execute() {
        List<UserBO> users = gateway.findAll();

        return users.stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    public PaginatedResponseDTO<UserResponseDTO> execute(Pagination pagination) {
        PageContent<UserBO> usersPage = gateway.findAll(pagination);

        return new PaginatedResponseDTO<>(usersPage.map(UserMapper::toResponseDTO));
    }

}
