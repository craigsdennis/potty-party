package com.github.craigsdennis.pottyparty.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "PottySession")
public class PottySession {
  private String customerId;
  private boolean isActive;
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

  @DynamoDBRangeKey(attributeName = "IsActive")
  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @DynamoDBAttribute(attributeName = "Notifications")
  public Set<String> getNotificationsSentAt() {
    return notificationsSentAt;
  }

  public void setNotificationsSentAt(Set<String> notificationsSentAt) {
    this.notificationsSentAt = notificationsSentAt;
  }


}

