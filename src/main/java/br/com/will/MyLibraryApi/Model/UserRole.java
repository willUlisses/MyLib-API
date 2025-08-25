package br.com.will.MyLibraryApi.Model;

public enum UserRole {
    USER("user"),
    ADMIN("admin"),
    AUTHOR("author");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
