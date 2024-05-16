package ru.joraly.rhythmraid.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AlbumAuthor> albumAuthors = new HashSet<>();

    @OneToMany(mappedBy = "author")
    private Set<SongAuthor> songAuthors = new HashSet<>();

}