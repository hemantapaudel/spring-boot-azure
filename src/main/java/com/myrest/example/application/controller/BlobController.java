package com.myrest.example.application.controller;

import com.myrest.example.application.service.AzureBlobAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("blobservice")
public class BlobController {


    @Value("${azure.storage.container.name}")
    private String  containerName;

    @Autowired
    private AzureBlobAdapter azureBlobAdapter;

    @PostMapping("/container")
    public ResponseEntity createContainer(@RequestBody String containerName) throws Exception {
        boolean created = azureBlobAdapter.createContainer(containerName);
        return ResponseEntity.ok(created);
    }

    @PostMapping
    public ResponseEntity upload(@RequestParam MultipartFile multipartFile) throws Exception {
        URI url = azureBlobAdapter.upload(multipartFile,containerName);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/blobs")
    public ResponseEntity getAllBlobs(@RequestParam String containerName) throws Exception {
        List uris = azureBlobAdapter.listBlobs(containerName);
        return ResponseEntity.ok(uris);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam String containerName, @RequestParam String blobName) throws Exception {
        azureBlobAdapter.deleteBlob(containerName, blobName);
        return ResponseEntity.ok().build();
    }


}