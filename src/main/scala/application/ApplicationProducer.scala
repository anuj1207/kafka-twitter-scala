package application

import java.io.IOException

import twitter.TwitterOperations
import twitter4j.TwitterException

object ApplicationProducer{

  @throws[TwitterException]
  @throws[IOException]
  def main(args: Array[String]): Unit = {
    new TwitterOperations().sendToKafka
  }

}
