package de.codepitbull.vertx.fastdata

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.lang.scala.ScalaVerticle._

class MainVerticle extends ScalaVerticle {

  override def start(): Unit = {
    vertx.deployVerticle(nameForVerticle[MqttVerticle])
    vertx.deployVerticle(nameForVerticle[KafkaConsumerVerticle])
    vertx.deployVerticle(nameForVerticle[KafkaProducerVerticle])
  }
}
