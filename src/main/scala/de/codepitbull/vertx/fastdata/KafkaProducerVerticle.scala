package de.codepitbull.vertx.fastdata

import io.vertx.core.buffer.Buffer
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.kafka.client.producer.{KafkaProducer, KafkaProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import scala.collection.mutable
import scala.util.{Failure, Success}

class KafkaProducerVerticle extends ScalaVerticle{

  val KafkaConsumerAddress = "kafkaConsumer"
  val KafkaTopic = "iotfw"

  override def start(): Unit = {
    val config = mutable.Map("bootstrap.servers" -> "localhost:9092",
      "key.serializer" -> classOf[StringSerializer].getName,
      "value.serializer" -> classOf[StringSerializer].getName,
      "acks" -> "1")

    val producer = KafkaProducer.create[String, String](vertx, config)
    producer.drainHandler(h => println("DRAINING"))
    producer.exceptionHandler(e => e.printStackTrace())
    producer.flush(f => println("FLUSHING"))

    val consumer = vertx
      .eventBus()
      .consumer[String]("kafkaConsumer")
      .handler(msg =>{
          println("WRITING TO KAFKA ")
          producer
            .writeFuture(KafkaProducerRecord.create[String, String](KafkaTopic, msg.body()))
            .onComplete{
              case Success(s) => println(s"Succeess ${s.getOffset}")
              case Failure(t) => t.printStackTrace()
            }
        }
      )
  }
}
