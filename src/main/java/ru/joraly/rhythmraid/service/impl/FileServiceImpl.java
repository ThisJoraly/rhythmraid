package ru.joraly.rhythmraid.service.impl;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import ru.joraly.rhythmraid.service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;

    @Override
    public void uploadFile(String bucketName, String objectName, InputStream inputStream, long size) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(inputStream, size, -1).build());
    }

    @Override
    public InputStream getFile(String bucketName, String objectName) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    @Override
    public void deleteFile(String bucketName, String objectName) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
}
