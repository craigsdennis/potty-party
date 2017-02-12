package com.github.craigsdennis.pottyparty.model;

import com.amazon.speech.speechlet.Session;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class PottyPartyDao {
  private final AmazonDynamoDBClient dynamoDBClient;
  private final DynamoDBMapper mapper;

  public PottyPartyDao() {
    dynamoDBClient = new AmazonDynamoDBClient();
    mapper = new DynamoDBMapper(dynamoDBClient);
  }

  // TODO:csd - This work for getting every status after today?
  public List<Status> findStatusForToday(Session session) {
    String alexaId = session.getUser().getUserId();
    Date today = new Date();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    String todayStr = dateFormatter.format(today);

    Map<String, AttributeValue> eav = new HashMap<>();
    eav.put(":val1", new AttributeValue().withS(alexaId));
    eav.put(":val2", new AttributeValue().withS(todayStr));

    DynamoDBQueryExpression<Status> queryExpression = new DynamoDBQueryExpression<Status>()
            .withKeyConditionExpression("CustomerId = :val1 and ReplyDateTime > :val2")
            .withExpressionAttributeValues(eav);

    return mapper.query(Status.class, queryExpression);
  }



}

