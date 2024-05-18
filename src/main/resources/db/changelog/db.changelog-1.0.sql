CREATE TABLE song (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    duration INTEGER NOT NULL,
    bucket VARCHAR(255) NOT NULL,
    object VARCHAR(255) NOT NULL
);

CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

CREATE TABLE song_author (
    id SERIAL PRIMARY KEY,
    song_id INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    FOREIGN KEY (song_id) REFERENCES song(id),
    FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE album (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    release_date VARCHAR(255) NOT NULL,
    bucket VARCHAR(255) NOT NULL,
    object VARCHAR(255) NOT NULL
);

CREATE TABLE playlist (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL
);

CREATE TABLE song_album (
    id SERIAL PRIMARY KEY,
    song_id INTEGER NOT NULL,
    album_id INTEGER NOT NULL,
    FOREIGN KEY (song_id) REFERENCES song(id),
    FOREIGN KEY (album_id) REFERENCES album(id)
);

CREATE TABLE album_author (
    id SERIAL PRIMARY KEY,
    album_id INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    FOREIGN KEY (album_id) REFERENCES album(id),
    FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE song_playlist (
    id SERIAL PRIMARY KEY,
    song_id INTEGER NOT NULL,
    playlist_id INTEGER NOT NULL,
    FOREIGN KEY (song_id) REFERENCES song(id),
    FOREIGN KEY (playlist_id) REFERENCES playlist(id)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);