package ru.joraly.rhythmraid.util;

import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.joraly.rhythmraid.configuration.MinioConfiguration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
public class FileComponent {
    private final MinioClient minioClient;
    private final MinioConfiguration minioConfiguration;

    public void uploadFile(String bucketName, String objectName, InputStream inputStream, long size) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(inputStream, size, -1).build());
    }

    public InputStream getFile(String bucketName, String objectName) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    public void deleteFile(String bucketName, String objectName) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
}
