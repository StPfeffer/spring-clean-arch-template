package com.pfeffer.springcleanarchtemplate.infra.database.postgres.repository;

import com.pfeffer.springcleanarchtemplate.infra.database.postgres.model.PgUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PgUserRepository extends JpaRepository<PgUserEntity, Long> {

    Optional<PgUserEntity> findByUsername(String username);

    Optional<PgUserEntity> findByEmail(String email);

}
