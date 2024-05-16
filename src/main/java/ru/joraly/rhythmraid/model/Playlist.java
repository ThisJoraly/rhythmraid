package ru.joraly.rhythmraid.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String position;

    @OneToMany(mappedBy = "playlist")
    private Set<SongPlaylist> songPlaylists = new HashSet<>();

}
