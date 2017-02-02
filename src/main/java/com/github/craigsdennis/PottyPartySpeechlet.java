package com.github.craigsdennis;

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

public class PottyPartySpeechlet implements Speechlet {
  @Override
  public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {

  }

  @Override
  public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
    return getWelcomeResponse();
  }

  @Override
  public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
    return getHelpResponse();
  }

  @Override
  public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

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

  private SpeechletResponse getHelpResponse() {
    String speechText = "I can't do anything yet, stupid dev!";

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
