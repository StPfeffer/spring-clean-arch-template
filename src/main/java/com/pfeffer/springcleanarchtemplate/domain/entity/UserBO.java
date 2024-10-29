package com.pfeffer.springcleanarchtemplate.domain.entity;

import com.pfeffer.springcleanarchtemplate.domain.entity.enums.UserStatus;
import com.pfeffer.springcleanarchtemplate.domain.entity.vo.CreatedAtVO;
import com.pfeffer.springcleanarchtemplate.domain.exception.LocalizedException;
import com.pfeffer.springcleanarchtemplate.domain.exception.SimpleErrorCode;
import com.pfeffer.springcleanarchtemplate.domain.utils.type.StringUtils;

import java.time.LocalDateTime;

public class UserBO extends EntityBO implements NonDeletable<LocalDateTime> {

    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String username;

    private final String email;

    private final UserStatus status;

    private final CreatedAtVO createdAt;

    private final LocalDateTime updatedAt;

    private final LocalDateTime deletedAt;

    private final LocalDateTime disabledAt;

    private final LocalDateTime lastLoginAt;

    private UserBO(Builder builder) {
        id = builder.id;
        firstName = builder.firstName;
        lastName = builder.lastName;
        username = builder.username;
        email = builder.email;
        status = builder.status;
        createdAt = builder.createdAt;
        updatedAt = builder.updatedAt;
        deletedAt = builder.deletedAt;
        disabledAt = builder.disabledAt;
        lastLoginAt = builder.lastLoginAt;

        validate();
    }

    @Override
    public void validate() throws LocalizedException {
        if (!StringUtils.hasLength(firstName)) {
            throw new LocalizedException(SimpleErrorCode.REQUIRED_FIELD, "firstName");
        }
        if (!StringUtils.hasLength(username)) {
            throw new LocalizedException(SimpleErrorCode.REQUIRED_FIELD, "username");
        }
        if (!StringUtils.hasLength(email)) {
            throw new LocalizedException(SimpleErrorCode.REQUIRED_FIELD, "email");
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public CreatedAtVO getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public static final class Builder {

        private Long id;

        private String firstName;

        private String lastName;

        private String username;

        private String email;

        private UserStatus status;

        private CreatedAtVO createdAt;

        private LocalDateTime updatedAt;

        private LocalDateTime deletedAt;

        private LocalDateTime disabledAt;

        private LocalDateTime lastLoginAt;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(CreatedAtVO createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder deletedAt(LocalDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public Builder disabledAt(LocalDateTime disabledAt) {
            this.disabledAt = disabledAt;
            return this;
        }

        public Builder lastLoginAt(LocalDateTime lastLoginAt) {
            this.lastLoginAt = lastLoginAt;
            return this;
        }

        public UserBO build() {
            return new UserBO(this);
        }

    }

}
