package ru.joraly.rhythmraid.rest;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.joraly.rhythmraid.configuration.MinioConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

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


    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException,
            InvalidKeyException, ErrorResponseException,
            XmlParserException, InternalException, ServerException {
        String bucketName = minioConfiguration.bucketName();
        String objectName = file.getOriginalFilename();
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(file.getInputStream(),
                        file.getSize(), -1)
                .build());
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("id") String id) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        String bucketName = minioConfiguration.bucketName();
        InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(id).build());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", id);
        return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable("id") String id) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        String bucketName = minioConfiguration.bucketName();
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(id).build());
        return new ResponseEntity<>("File deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllFiles() throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        String bucketName = minioConfiguration.bucketName();
        List<String> objects = new ArrayList<>();
        for (Result<Item> itemResult : minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build())) {
            Item item = itemResult.get();
            objects.add(item.objectName());
        }
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

}
