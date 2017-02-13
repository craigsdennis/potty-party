# 15 Minute Challenge Log

## Day 1
* Set up this repo!
* Setup developer account at [Amazon](https://developer.amazon.com)
* Added new Alexa Skill
* Started researching custom Alexa Skills Kit.
* Started looking at the official [Java samples on GitHub](https://github.com/amzn/alexa-skills-kit-java).

## Day 2
* More research of the Java Samples
* Started a Gradle Project

## Day 3
* Forked some example code and got code compiling locally
* Setup Lambda on Amazon

## Day 4
* Installed and used [Shadow JAR](http://imperceptiblethoughts.com/shadow/) to make fat JAR.
* Built an intent and connected it to my [Amazon Lambda](https://aws.amazon.com/lambda/) function.
* It lives!


## Day 5
* Wrote a handler to check status that accepts a dynamic slot on an intent.
* Added a new Intent to my schema that uses an Amazon specific Date slot
* I can now say "what happened today" or "yesterday" and it will call my code and pass in the correct date.

## Day 6
* Researched about Alexa sessions, a lot like web sessions
* Added a new intent that allows addition of child names.

## Day 7
* Researched DynamoDB for longterm storage
* Began reading free Kindle book on [Amazon DynamoDB](https://www.amazon.com/Amazon-DynamoDB-Developer-Guide-Services-ebook/dp/B007Q4JGBM/ref=asap_bc?ie=UTF8)

## Day 8
* More reading of the DynamoDB book.
* Researching Java AWS SDK API

## Day 9
* Researched [Alexa Skills Kit Voice Design Best Practices](https://developer.amazon.com/public/solutions/alexa/alexa-skills-kit/docs/alexa-skills-kit-voice-design-best-practices)

## Day 10
* Created a [Trello board](https://trello.com/b/Ml2HYzP3/potty-party) full of user stories to track the MVP, Minimal Viable Product

## Day 11
* Started working on the DynamoDB DAO pattern introduction.

## Day 12
* Added Alexa Skill Kit "resources" for reference to this repository.
* Reworked my initial swing at DynamoDB definitions

## Day 13
* Worked on my first DynamoDbMapper query.  Whoa.
* Refactored my package schemas.

## Day 14
* Made DAO static as supposedly connection should be reused across requests.
* Fiddled with DynamoDB security.
* Started learning more about [IAM](http://docs.aws.amazon.com/IAM/latest/UserGuide/introduction.html)