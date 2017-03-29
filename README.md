#JAVALAND MQTT KAFKA DEMO
A small demo for pumping MQTT-Events to Kafka


#Fat-jar
Take a look at the _build.sbt_ and search for the entry _packageOptions_. Enter the fully qualified class name 
of your primary verticle. This will be used as entry point for a generated fat-jar.

To create the runnable fat-jar use:
```
sbt assembly
```


#Dockerize
The project also contains everything you need to create a Docker-container.
Simply run the following command to package your fat-jar inside a Docker-container
```
sbt docker
```
To run use
```
docker run -p 8666:8666 default/vertx-scala-sbt
```
Point your browser to [http://127.0.0.1:8666/hello](http://127.0.0.1:8666/hello) and enjoy :)
