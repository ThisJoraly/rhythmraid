package ru.joraly.rhythmraid.service.impl;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import ru.joraly.rhythmraid.service.FileService;
import ru.joraly.rhythmraid.util.FileComponent;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileComponent fileComponent;

    @Override
    public void uploadFile(String bucketName, String objectName, InputStream inputStream, long size) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        fileComponent.uploadFile(bucketName, objectName, inputStream, size);
    }


    @Override
    public InputStream getFile(String bucketName, String objectName) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        return fileComponent.getFile(bucketName, objectName);
    }

    @Override
    public void deleteFile(String bucketName, String objectName) throws IOException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException, XmlParserException, InternalException, ServerException {
        fileComponent.deleteFile(bucketName, objectName);
    }
}
