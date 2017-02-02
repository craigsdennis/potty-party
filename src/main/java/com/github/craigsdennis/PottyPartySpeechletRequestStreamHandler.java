package com.github.craigsdennis;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public class PottyPartySpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    static {
      supportedApplicationIds.add("amzn1.ask.skill.7c3151cc-97d0-4be6-b028-f9e129fca82c");
    }

  public PottyPartySpeechletRequestStreamHandler() {
    super(new PottyPartySpeechlet(), supportedApplicationIds);
  }
}
