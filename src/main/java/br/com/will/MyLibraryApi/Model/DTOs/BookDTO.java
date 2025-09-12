package br.com.will.MyLibraryApi.Model.DTOs;

import br.com.will.MyLibraryApi.Model.Author;

import java.util.List;

public record BookDTO(String title, List<Author> authors) {
}
