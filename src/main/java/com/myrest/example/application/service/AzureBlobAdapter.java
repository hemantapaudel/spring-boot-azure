package com.myrest.example.application.service;

import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.blob.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class AzureBlobAdapter {

    @Autowired
    private CloudBlobClient cloudBlobClient;

    public boolean createContainer(String containerName) throws Exception {
        CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
        return container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
    }

    public URI upload(MultipartFile multipartFile, String containerName) throws Exception {
        CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
        CloudBlockBlob blob = container.getBlockBlobReference(multipartFile.getOriginalFilename());
        blob.upload(multipartFile.getInputStream(), -1);
        URI uri = blob.getUri();
        return uri;
    }

    public List listBlobs(String containerName) throws Exception {
        List uris = new ArrayList();
        CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
        for (ListBlobItem blobItem : container.listBlobs()) {
            uris.add(blobItem.getUri());
        }

        return uris;
    }

    public void deleteBlob(String containerName, String blobName) throws Exception {
        CloudBlobContainer container = cloudBlobClient.getContainerReference(containerName);
        CloudBlockBlob blobToBeDeleted = container.getBlockBlobReference(blobName);
        blobToBeDeleted.deleteIfExists();
    }
}
