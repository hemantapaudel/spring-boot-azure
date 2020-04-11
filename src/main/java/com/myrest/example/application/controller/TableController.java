package com.myrest.example.application.controller;

import com.microsoft.azure.storage.table.*;
import com.myrest.example.application.model.People;
import com.myrest.example.application.service.AzureBlobAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tableservice")
public class TableController {


    @Autowired
    private CloudTableClient tableClient;

    String tableName ="people";

    @PostMapping("/table")
    public ResponseEntity createContainer(@RequestBody String tableName) throws Exception {
        CloudTable cloudTable = tableClient.getTableReference(tableName);
        boolean isCreated = cloudTable.createIfNotExists();
        return ResponseEntity.ok(isCreated);
    }

    @PostMapping("/putdata")
    public void putData() throws Exception {
        CloudTable cloudTable = tableClient.getTableReference(tableName);
        // Define a batch operation.
        TableBatchOperation batchOperation = new TableBatchOperation();


        // Create a customer entity to add to the table.
        People customer = new People("Smith", "Jeff");
        customer.setEmail("Jeff@contoso.com");
        customer.setPhoneNumber("425-555-0104");
        batchOperation.insertOrReplace(customer);

        // Create another customer entity to add to the table.
        People customer2 = new People("Smith", "Ben");
        customer2.setEmail("Ben@contoso.com");
        customer2.setPhoneNumber("425-555-0102");
        batchOperation.insertOrReplace(customer2);

        // Create a third customer entity to add to the table.
        People customer3 = new People("Smith", "Denise");
        customer3.setEmail("Denise@contoso.com");
        batchOperation.insertOrReplace(customer3);

        // Execute the batch of operations on the "people" table.
        cloudTable.execute(batchOperation);

    }

    @GetMapping("/tables")
    public ResponseEntity getAllTables() throws Exception {
        CloudTable cloudTable = tableClient.getTableReference(tableName);
        TableQuery<People> partitionQuery = TableQuery.from(People.class);
        return ResponseEntity.ok(cloudTable.execute(partitionQuery));
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity deleteTables() throws Exception {
        CloudTable cloudTable = tableClient.getTableReference(tableName);
        return ResponseEntity.ok(cloudTable.deleteIfExists());
    }

}
