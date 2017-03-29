package de.codepitbull.vertx.fastdata

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.kafka.client.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import scala.collection.mutable
import scala.concurrent.Future

class KafkaConsumerVerticle extends ScalaVerticle {

  val KafkaTopic = "iotfw"

  override def startFuture(): Future[Unit] = {
    val config = mutable.Map("bootstrap.servers" -> "localhost:9092",
      "key.deserializer" -> classOf[StringDeserializer].getName,
      "value.deserializer" -> classOf[StringDeserializer].getName,
      "group.id" -> "my_group",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> "false")

    val consumer = KafkaConsumer.create[String, String](vertx, config)
    consumer.handler(record => println(s"RECEIVED FROM KAFKAE ${record.value()}"))
    consumer.subscribeFuture(KafkaTopic)

  }
}
