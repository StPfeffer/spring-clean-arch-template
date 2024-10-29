package com.pfeffer.springcleanarchtemplate.infra.database.postgres.gateway;

import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
import com.pfeffer.springcleanarchtemplate.domain.gateway.UserGateway;
import com.pfeffer.springcleanarchtemplate.infra.database.postgres.mapper.PgUserMapper;
import com.pfeffer.springcleanarchtemplate.infra.database.postgres.model.PgUserEntity;
import com.pfeffer.springcleanarchtemplate.infra.database.postgres.repository.PgUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PgUserGateway
        extends PgGateway<UserBO, PgUserEntity, Long>
        implements UserGateway {

    private final PgUserRepository repository;

    public PgUserGateway(PgUserRepository repository, PgUserRepository repository1) {
        super(repository, PgUserMapper::toDomain, PgUserMapper::toEntity);
        this.repository = repository1;
    }

    @Override
    public Optional<UserBO> findByUsername(String username) {
        Optional<PgUserEntity> user = repository.findByUsername(username);

        return user.map(PgUserMapper::toDomain);
    }

    @Override
    public Optional<UserBO> findByEmail(String email) {
        Optional<PgUserEntity> user = repository.findByEmail(email);

        return user.map(PgUserMapper::toDomain);
    }

}
