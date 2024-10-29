//package com.pfeffer.springcleanarchtemplate.infra.database.mongo.repository;
//
//import com.pfeffer.springcleanarchtemplate.infra.database.mongo.model.MongoUserEntity;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface MongoUserRepository extends MongoRepository<MongoUserEntity, Long> {
//
//    Optional<MongoUserEntity> findByUsername(String username);
//
//    Optional<MongoUserEntity> findByEmail(String email);
//
//}
