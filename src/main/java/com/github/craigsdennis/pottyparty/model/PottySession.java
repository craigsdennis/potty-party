package com.github.craigsdennis.pottyparty.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "Session")
public class PottySession {
  private String customerId;
  private boolean isActive;
  private Set notificationsSentAt;


  @DynamoDBRangeKey(attributeName = "CreatedAt")
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

  @DynamoDBAttribute(attributeName = "IsActive")
  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @DynamoDBAttribute(attributeName = "Notifications")
  public Set getNotificationsSentAt() {
    return notificationsSentAt;
  }

  public void setNotificationsSentAt(Set notificationsSentAt) {
    this.notificationsSentAt = notificationsSentAt;
  }


}

