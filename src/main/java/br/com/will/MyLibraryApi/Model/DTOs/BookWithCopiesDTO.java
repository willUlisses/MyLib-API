package br.com.will.MyLibraryApi.Model.DTOs;

import br.com.will.MyLibraryApi.Model.Author;

import java.util.List;

public record BookWithCopiesDTO(String title, List<Author> authors, int numberOfCopies) {
}
