package ru.joraly.rhythmraid.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private Integer duration;

    private String bucket;

    private String object;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SongAlbum> songAlbums = new HashSet<>();

    @OneToMany(mappedBy = "song")
    private Set<SongAuthor> songAuthors = new HashSet<>();

    @OneToMany(mappedBy = "song")
    private Set<SongPlaylist> songPlaylists = new HashSet<>();

}