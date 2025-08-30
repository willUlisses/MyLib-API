package br.com.will.MyLibraryApi.Model.DTOs;

import br.com.will.MyLibraryApi.Model.UserRole;

public record UserDTO(String login, String password, UserRole role) {
}
