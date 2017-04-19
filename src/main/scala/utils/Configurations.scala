package utils

import com.typesafe.config.{Config, ConfigFactory}

object Configurations {

  val config: Config = ConfigFactory.load()

  def getTopicName: String = config.getString("topicName")

  def getServers: String = config.getString("servers")

  def getConsumerKey: String = config.getString("twitter.consumerKey")

  def getConsumerSecret: String = config.getString("twitter.consumerSecret")

  def getAccessToken: String = config.getString("twitter.accessToken")

  def getAccessSecret: String = config.getString("twitter.accessTokenSecret")

}
