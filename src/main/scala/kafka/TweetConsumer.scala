package kafka

import utils.Configurations
import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import twitter4j.Status
import scala.collection.JavaConverters._

class TweetConsumer {

  def receiveTweets(groupId: String): Unit = {
    val topic: String = Configurations.getTopicName
    val servers: String = Configurations.getServers
    val props = new Properties()
    props.put("bootstrap.servers", servers)
    props.put("group.id", groupId)
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")
    props.put("session.timeout.ms", "30000")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "serialization.TweetDeserializer")
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
    val consumer = new KafkaConsumer[String, Status](props)
    consumer.subscribe(util.Collections.singletonList(topic))

    while (true) {
      val records: ConsumerRecords[String, Status] = consumer.poll(100)
      records.asScala.foreach { record =>
        println(s"Received : ${record.value()}::At offset : ${record.offset()}")
      }
    }

  }

}
