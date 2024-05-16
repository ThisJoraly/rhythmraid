package ru.joraly.rhythmraid.model;

import jakarta.persistence.*;

@Entity
@Table(name = "song_author")
public class SongAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
