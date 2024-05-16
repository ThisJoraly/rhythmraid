package ru.joraly.rhythmraid.service;
import io.minio.errors.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface FileService {

    void uploadFile(String bucketName, String objectName, InputStream inputStream, long size)
            throws IOException, InvalidResponseException, InsufficientDataException,
            NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException,
            XmlParserException, InternalException, ServerException;

    InputStream getFile(String bucketName, String objectName) throws IOException, InvalidResponseException, InsufficientDataException,
            NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException,
            XmlParserException, InternalException, ServerException;

    void deleteFile(String bucketName, String objectName) throws IOException, InvalidResponseException, InsufficientDataException,
            NoSuchAlgorithmException, InvalidKeyException, ErrorResponseException,
            XmlParserException, InternalException, ServerException;

}
