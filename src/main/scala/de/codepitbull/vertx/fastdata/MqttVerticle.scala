package de.codepitbull.vertx.fastdata

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.mqtt.MqttServer

import scala.concurrent.Future

class MqttVerticle extends ScalaVerticle{
  val KafkaConsumerAddress = "kafkaConsumer"

  override def startFuture(): Future[Unit] = {
    val mqttServer = MqttServer.create(vertx)
    mqttServer.endpointHandler(ep => {
      ep.publishHandler(msg => {
        val payload:java.lang.String = msg.payload().toString
        println(s"Payload received ${payload}")
        vertx.eventBus().send(KafkaConsumerAddress, payload)
      })
      ep.accept(false)
    }).listenFuture()
      .map(_ => ())
  }
}
