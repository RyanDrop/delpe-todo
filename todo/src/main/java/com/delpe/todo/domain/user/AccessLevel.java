package com.delpe.todo.domain.user;

import lombok.Getter;

@Getter
public enum AccessLevel {
    GERENTE("Gerente"),
    DEV("Dev"),
    CLIENTE("Cliente");

    private final String displayName;

    AccessLevel(String displayName) {
        this.displayName = displayName;
    }

    public static AccessLevel fromString(String accessLevel) {
        for (AccessLevel level : AccessLevel.values()) {
            if (level.name().equalsIgnoreCase(accessLevel)) {
                return level;
            }
        }
        return null;
    }

}
