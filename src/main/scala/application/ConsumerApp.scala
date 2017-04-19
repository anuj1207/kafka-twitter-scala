package application

import kafka.TweetConsumer

object ConsumerApp {

  def main(args: Array[String]): Unit = {
    val tweetConsumer = new TweetConsumer
    tweetConsumer.receiveTweets("knoldus")
  }

}
