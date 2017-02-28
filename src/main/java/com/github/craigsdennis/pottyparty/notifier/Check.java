package com.github.craigsdennis.pottyparty.notifier;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.github.craigsdennis.pottyparty.model.PottyPartyDao;
import com.github.craigsdennis.pottyparty.model.PottySession;

import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Check {
  private static final Logger logger = Logger.getLogger(Check.class);
  private static AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();

  public static void notifyOpenSessions() {
    logger.info("notifyOpenSessions");
    // Find all sessions that are active
    PottyPartyDao dao = new PottyPartyDao();
    dao.findAllActivePottySessions().stream()
            .filter(session -> session.getNextNotificationTime().isBefore(LocalDateTime.now()))
            .forEach(Check::notifySession);
  }

  private static void notifySession(PottySession pottySession) {
    PottyPartyDao dao = new PottyPartyDao();
    // TODO: Pull communication methods/kid from pottySession
    String testPhoneNumber = System.getenv("TEST_PHONE_NUMBER");

    PublishResult result = sendSMS(testPhoneNumber, "It's potty time!");
    logger.info("Published message: "+ result);
    pottySession.getNotificationsSentAt()
            .add(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    dao.save(pottySession);
  }

  public static PublishResult sendSMS(String phoneNumber, String message) {
    Map<String, MessageAttributeValue> smsAttributes =
            new HashMap<String, MessageAttributeValue>();
    smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
            .withStringValue("PottyParty") //The sender ID shown on the device.
            .withDataType("String"));
    smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
            .withStringValue("0.01") //Sets the max price to 0.01 USD.
            .withDataType("Number"));
    smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
            .withStringValue("Promotional") //Sets the type to promotional.
            .withDataType("String"));
    //sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);
    return snsClient.publish(new PublishRequest()
            .withMessage(message)
            .withPhoneNumber(phoneNumber)
            .withMessageAttributes(smsAttributes));

  }
}
