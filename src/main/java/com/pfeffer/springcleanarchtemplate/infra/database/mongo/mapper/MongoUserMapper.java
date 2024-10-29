//package com.pfeffer.springcleanarchtemplate.infra.database.mongo.mapper;
//
//import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
//import com.pfeffer.springcleanarchtemplate.domain.entity.vo.CreatedAtVO;
//import com.pfeffer.springcleanarchtemplate.infra.database.mongo.model.MongoUserEntity;
//
//public final class MongoUserMapper {
//
//    public static MongoUserEntity toEntity(UserBO domain) {
//        MongoUserEntity entity = new MongoUserEntity();
//
//        entity.setId(domain.getId());
//        entity.setFirstName(domain.getFirstName());
//        entity.setLastName(domain.getLastName());
//        entity.setUsername(domain.getUsername());
//        entity.setEmail(domain.getEmail());
//        entity.setStatus(domain.getStatus());
//        entity.setCreatedAt(domain.getCreatedAt().value());
//        entity.setUpdatedAt(domain.getUpdatedAt());
//        entity.setDisabledAt(domain.getDisabledAt());
//        entity.setDeletedAt(domain.getDeletedAt());
//        entity.setLastLoginAt(domain.getLastLoginAt());
//
//        return entity;
//    }
//
//    public static UserBO toDomain(MongoUserEntity entity) {
//        return new UserBO.Builder()
//                .id(entity.getId())
//                .firstName(entity.getFirstName())
//                .lastName(entity.getLastName())
//                .username(entity.getUsername())
//                .email(entity.getEmail())
//                .status(entity.getStatus())
//                .createdAt(new CreatedAtVO(entity.getCreatedAt()))
//                .updatedAt(entity.getUpdatedAt())
//                .disabledAt(entity.getDisabledAt())
//                .deletedAt(entity.getDeletedAt())
//                .lastLoginAt(entity.getLastLoginAt())
//                .build();
//    }
//
//}
