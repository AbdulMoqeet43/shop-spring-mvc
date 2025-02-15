package com.code.model;

public class UserInfo {
    private int id;
    private String username;
    private String password;
    private UserRoles role;

    public UserInfo(int id, String username, UserRoles role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + username + '\'' +
                '}';
    }
}
