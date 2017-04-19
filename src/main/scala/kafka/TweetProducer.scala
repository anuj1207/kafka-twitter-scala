package kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import twitter4j.Status
import utils.Configurations

class TweetProducer {

  val servers: String = Configurations.getServers
  val topic: String = Configurations.getTopicName
  val properties = new Properties
  properties.put("bootstrap.servers", servers)
  properties.put("acks", "all")
  properties.put("retries", "0")
  properties.put("batch.size", "16384")
  properties.put("linger.ms", "1")
  properties.put("buffer.memory", "33554432")
  properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  properties.put("value.serializer", "serialization.TweetSerializer")
  val producer = new KafkaProducer[String, Status](properties)

  def send(status : Status): Any = {
    try {
      producer.send(new ProducerRecord[String, Status](topic, status.getId.toString, status))
      println(s"Sent record $status")
    }catch {
      case ex: Exception => ex.printStackTrace()
    }
  }

}
