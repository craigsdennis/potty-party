package com.github.craigsdennis.pottyparty.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "StatusLog")
public class Status {
  private String customerId;
  private String kid;
  private String when;
  // TODO: enum?
  private String type;

  @DynamoDBHashKey(attributeName = "CustomerId")
  public String getCustomerId() {
    return customerId;
  }

  @DynamoDBRangeKey(attributeName = "When")
  public String getWhen() {
    return when;
  }


  public void setWhen(String when) {
    this.when = when;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  @DynamoDBAttribute(attributeName = "Kid")
  public String getKid() {
    return kid;
  }

  public void setKid(String kid) {
    this.kid = kid;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
