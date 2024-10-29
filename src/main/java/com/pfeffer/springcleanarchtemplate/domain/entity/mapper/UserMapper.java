package com.pfeffer.springcleanarchtemplate.domain.entity.mapper;

import com.pfeffer.springcleanarchtemplate.domain.entity.UserBO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.request.UserRequestDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.dto.response.UserResponseDTO;
import com.pfeffer.springcleanarchtemplate.domain.entity.vo.CreatedAtVO;

public final class UserMapper {

    public static UserBO toBO(UserRequestDTO dto) {
        return new UserBO.Builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .status(dto.getStatus())
                .createdAt(new CreatedAtVO(dto.getCreatedAt()))
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .disabledAt(dto.getDisabledAt())
                .lastLoginAt(dto.getLastLoginAt())
                .build();
    }

    public static UserBO toBO(UserResponseDTO dto) {
        return new UserBO.Builder()
                .id(Long.valueOf(dto.getId()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .status(dto.getStatus())
                .createdAt(new CreatedAtVO(dto.getCreatedAt()))
                .updatedAt(dto.getUpdatedAt())
                .deletedAt(dto.getDeletedAt())
                .disabledAt(dto.getDisabledAt())
                .lastLoginAt(dto.getLastLoginAt())
                .build();
    }

    public static UserRequestDTO toRequestDTO(UserBO bo) {
        UserRequestDTO dto = new UserRequestDTO();

        dto.setId(bo.getId());
        dto.setFirstName(bo.getFirstName());
        dto.setLastName(bo.getLastName());
        dto.setUsername(bo.getUsername());
        dto.setEmail(bo.getEmail());
        dto.setStatus(bo.getStatus());
        dto.setCreatedAt(bo.getCreatedAt().value());
        dto.setUpdatedAt(bo.getUpdatedAt());
        dto.setDeletedAt(bo.getDeletedAt());
        dto.setDisabledAt(bo.getDisabledAt());
        dto.setLastLoginAt(bo.getLastLoginAt());

        return dto;
    }

    public static UserResponseDTO toResponseDTO(UserBO bo) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(bo.getStringId());
        dto.setFirstName(bo.getFirstName());
        dto.setLastName(bo.getLastName());
        dto.setUsername(bo.getUsername());
        dto.setEmail(bo.getEmail());
        dto.setStatus(bo.getStatus());
        dto.setCreatedAt(bo.getCreatedAt().value());
        dto.setUpdatedAt(bo.getUpdatedAt());
        dto.setDeletedAt(bo.getDeletedAt());
        dto.setDisabledAt(bo.getDisabledAt());
        dto.setLastLoginAt(bo.getLastLoginAt());

        return dto;
    }

}