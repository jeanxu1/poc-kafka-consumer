# kafka-consumer-sample-app
This application contains sample code that can be used to bootstrap kafka message consumer application.

## Pre-requisites
This application uses java spring-boot and spring cloud stream frameworks. This readme document assumes that you have good understanding of java, maven and spring-boot concepts. 

To run this application you will need java 11 and maven. If you don't have it installed then you can install it by following instructions in below links

* java 11 - https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot
* maven - https://maven.apache.org/install.html

This application is configured to connect to kafka brokers and schema-registry running on your local machine by default. If you dont have these components installed on your machine then you can install them by following below link

https://docs.confluent.io/current/quickstart/ce-quickstart.html#ce-quickstart

You can also configure the application to use lululemon's enterprise kafka cluster and schema-registry instead of local instances by changing below properties in `application.yaml` file

```
 spring.cloud.stream.kafka.binder.brokers: localhost:9093
 spring.cloud.stream.kafka.streams.bindings.avro.consumer.configuration.schema.registry.url: http://localhost:8081
 spring.cloud.stream.schemaRegistryClient.endpoint: http://localhost:8081
```

## Security configuration for kafka-brokers
This application uses SASL based security for connecting to kafka brokers. If you are using local cluster and dont want to configure the security then you can just comment out below properties and change the broker port from 9093 to 9092

```
#binder:
  #configuration:
  #  security.protocol: SASL_SSL #change this SASL_PLAINTEXT if SSL is not enabled for the brokers
  #  sasl.mechanism: PLAIN
  #jaas:
  #  loginModule: org.apache.kafka.common.security.plain.PlainLoginModule
  #  options:
  #    username: ${kafka_username}
  #    password: ${kafka_password}
```

If you are using lululemon enterprise cluster then you will need the security configuration. Also you will need to get the api key and passphrase from the kafka platform team and set below 2 environment variables before running the application

* kafka_username (set this to api key)
* kafka_password (set this to passphrase)

Please never commit these 2 values to version control. In your application you might want to explore vault or k8s secret or aws kms to store these based on your deployment platform.

## Explore the use cases
This application contains code for consuming plain json messages and AVRO messages (using specific and generic deserializer) from kafka topic.

You can run the application using below maven command from the root project folder. (Note: **Do not forget to set the environment variables for broker security if you are using brokers with SASL config enabled.**) 

`mvn spring-boot:run`

After the application is started it will start consuming the messages from below 2 topics and will log the contents of the consumed messages to the console

* product (AVRO message)
* product-plain (plain json message)

Also the consumer is configured with dead letter queue support and it uses below 2 dead letter queue topics

* product-plain-dead-letter
* product-dead-letter

If you are running this sample code against enterprise kafka cluster then please make sure all these 4 kafka topics are created before you run the application

The AVRO schema file is present @  `resources/avro/product.avsc` folder. This application uses auto-generated class files to store the de-serialized the AVRO message. These class files get generated from the AVRO schema file during the application build in `avro.schema` package.