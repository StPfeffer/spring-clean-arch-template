//package com.pfeffer.springcleanarchtemplate.infra.database.mongo.model;
//
//import com.pfeffer.springcleanarchtemplate.domain.entity.enums.UserStatus;
//import jakarta.persistence.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//
//@Document("users")
//public class MongoUserEntity {
//
//    @Id
//    private Long id;
//
//    private String firstName;
//
//    private String lastName;
//
//    private String username;
//
//    private String email;
//
//    private UserStatus status;
//
//    private LocalDateTime createdAt;
//
//    private LocalDateTime updatedAt;
//
//    private LocalDateTime deletedAt;
//
//    private LocalDateTime disabledAt;
//
//    private LocalDateTime lastLoginAt;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public UserStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(UserStatus status) {
//        this.status = status;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public LocalDateTime getDeletedAt() {
//        return deletedAt;
//    }
//
//    public void setDeletedAt(LocalDateTime deletedAt) {
//        this.deletedAt = deletedAt;
//    }
//
//    public LocalDateTime getDisabledAt() {
//        return disabledAt;
//    }
//
//    public void setDisabledAt(LocalDateTime disabledAt) {
//        this.disabledAt = disabledAt;
//    }
//
//    public LocalDateTime getLastLoginAt() {
//        return lastLoginAt;
//    }
//
//    public void setLastLoginAt(LocalDateTime lastLoginAt) {
//        this.lastLoginAt = lastLoginAt;
//    }
//
//}
