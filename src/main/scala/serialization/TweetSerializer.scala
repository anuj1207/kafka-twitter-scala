package serialization

import java.io.ObjectOutputStream
import java.util

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import org.apache.kafka.common.serialization.Serializer
import twitter4j.Status


class TweetSerializer extends Serializer[Status]{

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {

  }

  override def serialize(topic: String, data: Status): Array[Byte] = {
    try{
      val byteOut = new ByteOutputStream()
      val objOut = new ObjectOutputStream(byteOut)
      objOut.writeObject(data)
      objOut.close()
      byteOut.close()
      byteOut.getBytes
    }catch{
      case ex: Exception => throw new Exception(ex.getMessage)
    }
  }

  override def close(): Unit = {

  }

}
