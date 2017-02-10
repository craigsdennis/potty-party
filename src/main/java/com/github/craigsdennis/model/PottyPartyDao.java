package com.github.craigsdennis.model;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class PottyPartyDao {
  private final AmazonDynamoDBClient dynamoDBClient;
  private final DynamoDBMapper mapper;

  public PottyPartyDao() {
    dynamoDBClient = new AmazonDynamoDBClient();
    mapper = new DynamoDBMapper(dynamoDBClient);
  }


}

