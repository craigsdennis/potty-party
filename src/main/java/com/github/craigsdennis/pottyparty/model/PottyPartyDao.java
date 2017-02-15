package com.github.craigsdennis.pottyparty.model;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class PottyPartyDao {
  private static final AmazonDynamoDBClient dynamoDBClient;
  private static final DynamoDBMapper mapper;
  private final Logger logger = Logger.getLogger(PottyPartyDao.class);

  static {
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
            .withKeyConditionExpression("CustomerId = :val1 and CreatedAt > :val2")
            .withExpressionAttributeValues(eav);

    return mapper.query(Status.class, queryExpression);
  }


  public Status createStatusFromIntent(Intent intent) {
    Status status = new Status();
    String type = intent.getSlot("Type").getValue();
    status.setType(type);
    return status;
  }

  public void saveStatus(Session session, Status status) {
    logger.info("saveStatus:" + session.getUser());
    status.setCustomerId(session.getUser().getUserId());
    status.setKid(String.format("%s",
            session.getAttribute("childName"))
    );
    Date today = new Date();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    String todayStr = dateFormatter.format(today);
    status.setCreatedAt(todayStr);
    mapper.save(status);
  }

}

