package com.myrest.example.application.config;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.myrest.example.application.model.People;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Configuration
public class AzureConfig {

    @Value("${azure.storage.connection}")
    private String  azureStorageUrl;


    @Value("${azure.storage.container.name}")
    private String  containerName;

    @Bean
    public CloudStorageAccount storageAccount() throws URISyntaxException, StorageException, InvalidKeyException {
        CloudStorageAccount storageAccount = CloudStorageAccount.parse(azureStorageUrl);
        return storageAccount;
    }

    @Bean
    public CloudBlobClient cloudBlobClient(CloudStorageAccount storageAccount) throws URISyntaxException, StorageException, InvalidKeyException {
        return storageAccount.createCloudBlobClient();
    }

    @Bean
    public CloudBlobContainer cloudBlobContainer(CloudBlobClient cloudBlobClient) throws URISyntaxException, StorageException {
        CloudBlobContainer cloudBlobContainer = cloudBlobClient.getContainerReference(containerName);
        System.out.println("Creating container: " + cloudBlobContainer.getName());
        cloudBlobContainer.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
        return cloudBlobContainer;
    }

    @Bean
    public CloudTableClient tableClient(CloudStorageAccount storageAccount) throws URISyntaxException, StorageException {
        // Create the table client.
        CloudTableClient tableClient = storageAccount.createCloudTableClient();
        // Create the table if it doesn't exist.
        String tableName = "people";
        CloudTable cloudTable = tableClient.getTableReference(tableName);
        boolean iscreated = cloudTable.createIfNotExists();
        createDummyData( tableClient);
        return  tableClient;
    }

    private void createDummyData(CloudTableClient tableClient) throws URISyntaxException, StorageException {
        String tableName = "people";
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


}

