package com.github.craigsdennis.pottyparty.model;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PottyPartyDao {
  private static final AmazonDynamoDBClient dynamoDBClient;
  private static final DynamoDBMapper mapper;
  private final Logger logger = Logger.getLogger(PottyPartyDao.class);

  static {
    dynamoDBClient = new AmazonDynamoDBClient();
    mapper = new DynamoDBMapper(dynamoDBClient);
  }


  public List<Status> findStatusForDay(Session session, String dayString) {
    String alexaId = session.getUser().getUserId();
    LocalDate start = LocalDate.parse(dayString);
    LocalDate end = start.plusDays(1);

    Map<String, AttributeValue> eav = new HashMap<>();
    eav.put(":customerId", new AttributeValue().withS(alexaId));
    eav.put(":start", new AttributeValue().withS(start.format(DateTimeFormatter.ISO_DATE)));
    eav.put(":end", new AttributeValue().withS(end.format(DateTimeFormatter.ISO_DATE)));
    DynamoDBQueryExpression<Status> queryExpression = new DynamoDBQueryExpression<Status>()
            .withKeyConditionExpression("CustomerId = :customerId and CreatedAt between :start and :end")
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
    LocalDateTime today = LocalDateTime.now();
    String todayStr = today.format(DateTimeFormatter.ISO_DATE_TIME);
    status.setCreatedAt(todayStr);
    mapper.save(status);
  }

}

