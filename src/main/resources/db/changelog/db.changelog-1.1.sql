INSERT INTO song (title, genre, duration, bucket, object)
VALUES ('Song 1', 'Pop', 200, 'bucket1', 'path1'),
       ('Song 2', 'Rock', 300, 'bucket2', 'path2');

INSERT INTO author (name, country)
VALUES ('Author 1', 'USA'),
       ('Author 2', 'UK');

INSERT INTO song_author (song_id, author_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO album (title, release_date)
VALUES ('Album 1', '2020-01-01'),
       ('Album 2', '2021-01-01');

INSERT INTO playlist (name, position)
VALUES ('Playlist 1', 'Top'),
       ('Playlist 2', 'New');

INSERT INTO song_album (song_id, album_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO album_author (album_id, author_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO song_playlist (song_id, playlist_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO users (name, login, password)
VALUES ('User 1', 'user1', 'password1'),
       ('User 2', 'user2', 'password2');