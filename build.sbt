name := "kafka-twitter-scala"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.twitter4j" % "twitter4j-stream" % "4.0.6",
  "com.typesafe" % "config" % "1.3.1",
  "org.apache.kafka" % "kafka-clients" % "0.10.2.0"
)