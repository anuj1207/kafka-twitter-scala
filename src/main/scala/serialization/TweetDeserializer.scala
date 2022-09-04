package serialization

import java.io.{ByteArrayInputStream, ObjectInputStream}
import java.util

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream
import org.apache.kafka.common.serialization.Deserializer
import twitter4j.Status

class TweetDeserializer extends Deserializer[Status]{

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {

  }

  override def deserialize(topic: String, data: Array[Byte]): Status = {
    try{
      val byteIn = new ByteArrayInputStream(data)
      val objIn = new ObjectInputStream(byteIn)
      val obj = objIn.readObject.asInstanceOf[Status]
      byteIn.close()
      objIn.close()
      obj
    }catch{
      case ex: Exception => throw new Exception(ex.getMessage)
    }
  }

  override def close(): Unit = {

  }

}
