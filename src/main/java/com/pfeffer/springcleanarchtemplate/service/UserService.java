package com.pfeffer.springcleanarchtemplate.service;

import com.pfeffer.springcleanarchtemplate.domain.entity.dto.request.UserRequestDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.PaginatedResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.UserResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.gateway.UserGateway;
import com.pfeffer.springcleanarchtemplate.domain.pagination.Pagination;
import com.pfeffer.springcleanarchtemplate.domain.usecase.user.*;
import com.pfeffer.springcleanarchtemplate.infra.database.postgres.gateway.PgUserGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserGateway gateway;

    public UserService(PgUserGateway gateway) {
        this.gateway = gateway;
    }

    public UserResponseDTO findById(Long id) {
        FindUserById findUserById = new FindUserById(gateway);

        return findUserById.execute(id);
    }

    public UserResponseDTO findByEmail(String email) {
        FindUserByEmail findUserByEmail = new FindUserByEmail(gateway);

        return findUserByEmail.execute(email);
    }

    public List<UserResponseDTO> findAll() {
        ListUser listUser = new ListUser(gateway);

        return listUser.execute();
    }

    public PaginatedResponseDTO<UserResponseDTO> findAll(Pagination pagination) {
        ListUser listUser = new ListUser(gateway);

        return listUser.execute(pagination);
    }

    public UserResponseDTO findByUsername(String username) {
        FindUserByUsername findUserByUsername = new FindUserByUsername(gateway);

        return findUserByUsername.execute(username);
    }

    public UserResponseDTO save(UserRequestDTO dto) {
        CreateUser createUser = new CreateUser(gateway);

        return createUser.execute(dto);
    }

    public UserResponseDTO updateById(Long id, UserRequestDTO dto) {
        UpdateUser updateUser = new UpdateUser(gateway);

        return updateUser.execute(id, dto);
    }

    public void deleteById(Long id) {
        DeleteUser deleteUser = new DeleteUser(gateway);

        deleteUser.execute(id);
    }

}
