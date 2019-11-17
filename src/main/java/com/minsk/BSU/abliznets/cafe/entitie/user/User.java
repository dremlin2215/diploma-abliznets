package com.minsk.BSU.abliznets.cafe.entitie.user;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String ID_COLUMN = "ID";
    public static final String LOGIN_COLUMN = "Login";
    public static final String PASSWORD_COLUMN = "Password";
    public static final String NAME_COLUMN = "Name";
    public static final String SURNAME_COLUMN = "Surname";
    public static final String ROLE_COLUMN = "Role";
    public static final String SCORE_COLUMN = "Score";
    public static final String IS_BANNED_COLUMN = "IsBanned";
    public static final String ACCOUNT_ID_COLUMN = "AccountID";

    private int ID;
    private String login;
    private String password;
    private String name;
    private String surname;
    private UserRole role;

    private Boolean isBanned;
    private Integer score;
    private Integer accountID;

    public User(int ID, String login, String password, String name, String surname,
                UserRole role, Boolean isBanned, Integer score, Integer accountID) {
        this.ID = ID;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.isBanned = isBanned;
        this.score = score;
        this.accountID = accountID;
    }

    public int getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public UserRole getRole() {
        return role;
    }

    public int getAccountID() {
        return accountID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return ID == user.ID &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                name.equals(user.name) &&
                surname.equals(user.surname) &&
                isBanned == user.isBanned &&
                role == user.role &&
                accountID.equals(user.accountID);
    }

    @Override
    public int hashCode() {
        return 31 * ID
                + (login == null ? 0 : login.hashCode())
                + (password == null ? 0 : password.hashCode())
                + (name == null ? 0 : name.hashCode())
                + (surname == null ? 0 : surname.hashCode())
                + (isBanned == null ? 0 : isBanned.hashCode())
                + (role == null ? 0 : role.hashCode())
                + (accountID == null ? 0 : accountID.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + LOGIN_COLUMN + "=" + login + " " + PASSWORD_COLUMN + "="
                + password + " " + NAME_COLUMN + "=" + name + " " + SURNAME_COLUMN + "=" + surname + " "
                + IS_BANNED_COLUMN + "=" + isBanned + " " + ROLE_COLUMN + "=" + role + " " + ACCOUNT_ID_COLUMN
                + "=" + accountID;
    }
}
