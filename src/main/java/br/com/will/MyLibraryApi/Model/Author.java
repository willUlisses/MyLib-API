package br.com.will.MyLibraryApi.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long id;
    private String name;
    private String nacionalidade;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;



}
