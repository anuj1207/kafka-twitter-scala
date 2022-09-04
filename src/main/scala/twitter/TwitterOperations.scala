package twitter

import kafka.TweetProducer
import twitter4j._
import twitter4j.conf.ConfigurationBuilder
import utils.Configurations

class TwitterOperations {

  val tweetProducer = new TweetProducer

  private def getTwitterConf = {
    val configBuilder = new ConfigurationBuilder()
      .setOAuthConsumerKey(Configurations.getConsumerKey)
      .setOAuthConsumerSecret(Configurations.getConsumerSecret)
      .setOAuthAccessToken(Configurations.getAccessToken)
      .setOAuthAccessTokenSecret(Configurations.getAccessSecret)
      .build()
    new TwitterStreamFactory(configBuilder).getInstance()
  }

  def sendToKafka = {
    val listener = new StatusListener {
      override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = {}

      override def onScrubGeo(userId: Long, upToStatusId: Long): Unit = {}

      override def onStatus(status: Status): Unit = tweetProducer.send(status)

      override def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {}

      override def onStallWarning(warning: StallWarning): Unit = {}

      override def onException(ex: Exception): Unit = ex.printStackTrace()
    }
    val twitterStream = getTwitterConf
    twitterStream.addListener(listener)
    println("started listening to tweets")
    twitterStream.sample("en")
  }

}
