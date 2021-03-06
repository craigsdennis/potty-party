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

## Day 15
* Created a new IAM role that has access to DynamoDb
* Debugged and resolved problem of using the When keyword for a column name.  Wherps!

## Day 16
* Got logging working for AWS Lambda
* Added an update method and successfully stored Poop in the DynamoDb!

## Day 17
* Refreshed myself on the [Java 8 LocalDateTime updates](http://www.oracle.com/technetwork/articles/java/jf14-date-time-2125367.html)
* Refactored to use LocalDate

## Day 18
* Researched Amazon Simple Queue Service

## Day 19
* Started reading free ebook on [Amazon Simple Queue Service](https://www.amazon.com/Amazon-Simple-Queue-Service-Developer-ebook/dp/B007S1LEEA)

## Day 20
* Some light refactoring of the codebase

## Day 21
* Add feature to always set session to have last kid used/added.

## Day 22
* Started researching [Amazon Push Notification Service - SNS](https://aws.amazon.com/sns/)
* Completed tutorial on [Scheduled Events with Lambda](http://docs.aws.amazon.com/lambda/latest/dg/with-scheduled-events.html)

## Day 23
* Add the concept of [PottySessions](https://trello.com/c/jVu8WUzy/2-as-a-parent-i-should-be-able-to-start-and-stop-a-potty-session-so-that-i-can-be-reminded-to-try-to-get-child-to-attempt-potty-ti) to the model
* Added DynamoDB table to store the sessions.

## Day 24
* Setup new Lambda function triggered by [CloudWatch](http://docs.aws.amazon.com/lambda/latest/dg/with-scheduled-events.html) on a schedule

## Day 25
* Used Amazon SNS to send a test [SMS message](http://docs.aws.amazon.com/sns/latest/dg/sms_publish-to-phone.html#sms_publish_sdk)

## Day 26
* Added Session Management Intent and handlers
* Refactored to use Optionals

## Day 27
* Wrestled some dependency nightmares
* DynamoDb woes with boolean partition keys

## Day 28
* Finally got DynamoDb working with a string status, but not named status that's reserved

## Day 29
* Worked on getting the notification scheduled events working.  Work in progress.