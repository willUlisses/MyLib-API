package br.com.will.MyLibraryApi.Model.DTOs;

import br.com.will.MyLibraryApi.Model.Enumerations.UserRole;

public record UserDTO(String login, String password, UserRole role) {
}
