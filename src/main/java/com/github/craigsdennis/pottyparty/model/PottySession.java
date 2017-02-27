package com.github.craigsdennis.pottyparty.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "PottySession")
public class PottySession {
  private String customerId;
  private String status;
  private Set<String> notificationsSentAt;


  @DynamoDBAttribute(attributeName = "CreatedAt")
  public String getCreatedAt() {
    return createdAt;
  }

  private String createdAt;

  @DynamoDBHashKey(attributeName = "CustomerId")
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  // Status is reserved
  @DynamoDBRangeKey(attributeName = "CurrentStatus")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @DynamoDBAttribute(attributeName = "Notifications")
  public Set<String> getNotificationsSentAt() {
    return notificationsSentAt;
  }

  public void setNotificationsSentAt(Set<String> notificationsSentAt) {
    this.notificationsSentAt = notificationsSentAt;
  }


}

