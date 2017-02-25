package com.github.craigsdennis.pottyparty;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.github.craigsdennis.pottyparty.model.PottyPartyDao;
import com.github.craigsdennis.pottyparty.model.Status;

import org.apache.log4j.Logger;

import java.util.List;

public class PottyPartySpeechlet implements Speechlet {
  private final Logger logger = Logger.getLogger(PottyPartySpeechlet.class);
  @Override
  public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
    PottyPartyDao dao = new PottyPartyDao();
    String kid = dao.getKid(session);
    logger.debug("Session started and kid is set to " + kid);
  }

  @Override
  public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
    return getWelcomeResponse();
  }

  @Override
  public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
    Intent intent = request.getIntent();
    String intentName = intent.getName();
    switch (intentName) {
      case "ManageSession":
        return handleManageSessionIntent(intent, session);
      case "ReportStatus":
        return handleReportStatusIntent(intent, session);
      case "CheckStatus":
        return handleCheckStatusIntent(intent, session);
      case "AddChild":
        return handleAddChildIntent(intent, session);
      default:
        return unknownIntent(intent, session);
    }
  }

  private SpeechletResponse unknownIntent(Intent intent, Session session) {
    String speechText = String.format("%s? Whatchoo talking about Willis?",
            intent.getName());
    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
    speech.setText(speechText);
    Reprompt reprompt = new Reprompt();
    reprompt.setOutputSpeech(speech);
    return SpeechletResponse.newAskResponse(speech, reprompt);
  }

  @Override
  public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

  }

  private SpeechletResponse handleAddChildIntent(Intent intent, Session session) {
    String childName = intent.getSlot("ChildName").getValue();
    String speechText = String.format("You added the child %s", childName);
    session.setAttribute("childName", childName);
    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
    speech.setText(speechText);
    Reprompt reprompt = new Reprompt();
    reprompt.setOutputSpeech(speech);
    return SpeechletResponse.newAskResponse(speech, reprompt);
  }

  private SpeechletResponse handleManageSessionIntent(Intent intent, Session session) {
    PottyPartyDao dao = new PottyPartyDao();
    String sessionType = intent.getSlot("SessionType").getValue();
    String speechText = "Session has begun";
    if (sessionType.equalsIgnoreCase("start")) {
      dao.startPottySession(session);
    } else {
      speechText = "Session has stopped";
      dao.stopPottySessions(session);
    }
    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
    speech.setText(speechText);
    Reprompt reprompt = new Reprompt();
    reprompt.setOutputSpeech(speech);
    return SpeechletResponse.newAskResponse(speech, reprompt);
  }

  private SpeechletResponse handleReportStatusIntent(Intent intent, Session session) {
    PottyPartyDao dao = new PottyPartyDao();
    Status status = dao.createStatusFromIntent(intent);
    dao.saveStatus(session, status);
    String speechText = String.format("Yay %s!  Good job!", status.getKid());
    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
    speech.setText(speechText);
    Reprompt reprompt = new Reprompt();
    reprompt.setOutputSpeech(speech);
    return SpeechletResponse.newAskResponse(speech, reprompt);
  }

  private SpeechletResponse handleCheckStatusIntent(Intent intent, Session session) {
    PottyPartyDao dao = new PottyPartyDao();
    String dateStr = intent.getSlot("Date").getValue();
    List<Status> statuses = dao.findStatusForDay(session, dateStr);
    String speechText = String.format("Your current child is %s, and you checked the status for %s and there are %d available",
            dao.getKid(session),
            dateStr,
            statuses.size());
    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
    speech.setText(speechText);
    Reprompt reprompt = new Reprompt();
    reprompt.setOutputSpeech(speech);
    return SpeechletResponse.newAskResponse(speech, reprompt);
  }

  private SpeechletResponse getWelcomeResponse() {
    String speechText = "Welcome to the Potty Party!";

    // Create the Simple card content.
    SimpleCard card = new SimpleCard();
    card.setTitle("PottyParty");
    card.setContent(speechText);

    // Create the plain text output.
    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
    speech.setText(speechText);

    // Create reprompt
    Reprompt reprompt = new Reprompt();
    reprompt.setOutputSpeech(speech);

    return SpeechletResponse.newAskResponse(speech, reprompt, card);
  }

}
