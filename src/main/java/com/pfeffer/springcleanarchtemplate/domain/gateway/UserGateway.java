package com.pfeffer.springcleanarchtemplate.domain.gateway;

import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
import com.pfeffer.springcleanarchtemplate.domain.gateway.support.Gateway;

import java.util.Optional;

public interface UserGateway
        extends Gateway<UserBO, Long> {

    Optional<UserBO> findByUsername(String username);

    Optional<UserBO> findByEmail(String email);

}
