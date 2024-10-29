package com.pfeffer.springcleanarchtemplate.domain.entity.enums;

public enum UserStatus implements IEnum {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    DISABLED("DISABLED"),
    DELETED("DELETED");

    private final String key;

    UserStatus(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

}
