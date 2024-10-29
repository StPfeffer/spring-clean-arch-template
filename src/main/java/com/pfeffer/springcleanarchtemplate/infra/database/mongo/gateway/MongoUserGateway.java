//package com.pfeffer.springcleanarchtemplate.infra.database.mongo.gateway;
//
//import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
//import com.pfeffer.springcleanarchtemplate.domain.gateway.UserGateway;
//import com.pfeffer.springcleanarchtemplate.infra.database.mongo.mapper.MongoUserMapper;
//import com.pfeffer.springcleanarchtemplate.infra.database.mongo.model.MongoUserEntity;
//import com.pfeffer.springcleanarchtemplate.infra.database.mongo.repository.MongoUserRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class MongoUserGateway
//        extends MongoGateway<UserBO, MongoUserEntity, Long>
//        implements UserGateway {
//
//    private final MongoUserRepository repository;
//
//    public MongoUserGateway(MongoUserRepository repository) {
//        super(repository, MongoUserMapper::toDomain, MongoUserMapper::toEntity);
//        this.repository = repository;
//    }
//
//    @Override
//    public Optional<UserBO> findByUsername(String username) {
//        Optional<MongoUserEntity> user = repository.findByUsername(username);
//
//        return user.map(MongoUserMapper::toDomain);
//    }
//
//    @Override
//    public Optional<UserBO> findByEmail(String email) {
//        Optional<MongoUserEntity> user = repository.findByEmail(email);
//
//        return user.map(MongoUserMapper::toDomain);
//    }
//
//}
