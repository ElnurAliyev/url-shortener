package com.bikent.cloudproject.dao;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;

public class UrlRepository {
    public final static String DYNAMODB_TABLE_NAME = "URL_Database";
    public final static String REGION = "us-east-2";
    public final static String HOST = "arn:aws:dynamodb:us-east-2:573198250869:table/URL_Database";
    private final DynamoDB dynamoDb;

    public UrlRepository() {
        super();
        dynamoDb = initDynamoDbClient();
    }

    public PutItemOutcome saveUrl(String shortUrl, String originalUrl)
            throws ConditionalCheckFailedException {
        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
                .putItem(
                        new PutItemSpec().withItem(new Item()
                                .withString("ShortURL", shortUrl)
                                .withString("OriginalURL", originalUrl)));
    }

    private static DynamoDB initDynamoDbClient() {
        return new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
    }
}
