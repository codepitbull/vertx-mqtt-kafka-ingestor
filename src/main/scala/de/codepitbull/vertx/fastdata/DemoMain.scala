package de.codepitbull.vertx.fastdata

import io.vertx.scala.core.Vertx
import io.vertx.lang.scala.ScalaVerticle._

object DemoMain {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(nameForVerticle[MainVerticle])
  }
}
