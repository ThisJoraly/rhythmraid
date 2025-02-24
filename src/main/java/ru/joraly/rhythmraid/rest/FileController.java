package ru.joraly.rhythmraid.rest;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.joraly.rhythmraid.configuration.MinioConfiguration;
import ru.joraly.rhythmraid.model.Song;
import ru.joraly.rhythmraid.service.SongService;
import ru.joraly.rhythmraid.service.impl.SongServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final MinioConfiguration minioConfiguration;
    private final MinioClient minioClient;
    private final SongServiceImpl songService;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            String bucketName = minioConfiguration.bucketName();
            String objectName = file.getOriginalFilename();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build());
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/audio/{bucket}/{object}")
    public ResponseEntity<byte[]> getAudioFile(
            @PathVariable String bucket,
            @PathVariable String object) {
        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(object)
                            .build());

            byte[] audioBytes = stream.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + object);

            return new ResponseEntity<>(audioBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/audio/{id}")
    public ResponseEntity<byte[]> getAudioFileById(
            @PathVariable Long id) {
        try {
            Song song = songService.getById(id);
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(song.getBucket())
                            .object(song.getObject())
                            .build());

            byte[] audioBytes = stream.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + song.getObject());

            return new ResponseEntity<>(audioBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{bucket}/{object}")
    public ResponseEntity<String> deleteFile(@PathVariable String bucket, @PathVariable String object) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(object)
                    .build());
            return new ResponseEntity<>("File deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("File deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{bucket}")
    public ResponseEntity<List<String>> getAllFiles(@PathVariable String bucket) {
        try {
            List<String> objects = new ArrayList<>();
            for (Result<Item> itemResult : minioClient.listObjects(ListObjectsArgs.builder().bucket(bucket).build())) {
                objects.add(itemResult.get().objectName());
            }
            return new ResponseEntity<>(objects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
