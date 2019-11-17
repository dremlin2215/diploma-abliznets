package com.minsk.BSU.abliznets.cafe.entitie.user;

import java.io.Serializable;

public enum UserRole implements Serializable {
    ADMINISTRATOR,
    CLIENT,
    ANONYMOUS;

    @Override
    public String toString() {
        return getClass().getName() + "@" + super.toString();
    }
}
