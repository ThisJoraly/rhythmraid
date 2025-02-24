-- Insert albums first
INSERT INTO album (title, release_date, bucket, object)
VALUES ('Album 1', '2020-01-01', 'rhythmraid', 'example3'),
       ('Album 2', '2021-01-01', 'rhythmraid', 'example4');

-- Insert songs with valid album_id references
INSERT INTO song (title, genre, duration, bucket, object, album_id)
VALUES ('Song 1', 'Pop', 200, 'rhythmraid', 'example1', 1),
       ('Song 2', 'Rock', 300, 'rhythmraid', 'example2', 2);

-- Insert authors
INSERT INTO author (name, country)
VALUES ('Author 1', 'USA'),
       ('Author 2', 'UK');

-- Insert playlists
INSERT INTO playlist (name, position)
VALUES ('Playlist 1', 'Top'),
       ('Playlist 2', 'New');

-- Insert song-playlist relationships
INSERT INTO song_playlist (song_id, playlist_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO album_author (album_id, author_id)
VALUES (1, 1),
       (2, 2);

-- Insert users
INSERT INTO users (name, login, password)
VALUES ('User 1', 'user1', 'password1'),
       ('User 2', 'user2', 'password2');
