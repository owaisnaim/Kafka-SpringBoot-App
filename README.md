# Kafka Spring Boot Application

A Spring Boot application demonstrating real-time message publishing and consumption using **Apache Kafka** and **Spring Kafka**.

---

## 📌 Features

- **Kafka Producer**: Exposes a REST endpoint to publish messages to a Kafka topic.
- **Kafka Consumer**: Listens to the configured Kafka topic and logs consumed messages asynchronously.
- **Configurable Settings**: Custom Kafka consumer factory and listener container configuration.

---

## 🏗️ Architecture & Workflow

```
+-------------+         GET /produce/{message}         +------------------------+
| HTTP Client | -------------------------------------> | Spring Boot Controller |
+-------------+                                        +------------------------+
                                                                   |
                                                          kafkaTemplate.send(...)
                                                                   v
                                                       +------------------------+
                                                       |   Kafka Topic:         |
                                                       |   `owais-topic-1`      |
                                                       +------------------------+
                                                                   |
                                                             @KafkaListener
                                                                   v
                                                       +------------------------+
                                                       |     Kafka Consumer     |
                                                       |  (Logs message in app) |
                                                       +------------------------+
```

---

## 🛠️ Tech Stack & Prerequisites

- **Java**: 8 or higher
- **Spring Boot**: 2.7.1
- **Spring Kafka**
- **Apache Kafka** (running locally on port `9092`)
- **Maven**: 3.x (or bundled `./mvnw`)

---

## ⚙️ Configuration Details

Key application and Kafka settings defined in `application.properties` and `ApplicationConstant.java`:

| Parameter | Value | Description |
| :--- | :--- | :--- |
| **Server Port** | `9091` | HTTP port for the Spring Boot application |
| **Kafka Bootstrap Server** | `localhost:9092` | Kafka broker address |
| **Topic Name** | `owais-topic-1` | Kafka topic for messages |
| **Consumer Group ID** | `group-id-string-1` | Kafka consumer group ID |

---

## 🚀 Getting Started

### 1. Start Apache Kafka

Make sure Apache Kafka and Zookeeper are running locally on default port `9092`.

<details>
<summary><b>Option A: Using Local Kafka Installation</b></summary>

```bash
# 1. Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# 2. Start Kafka Broker
bin/kafka-server-start.sh config/server.properties
```

*(On Windows, use `.bat` scripts in `bin/windows/`)*
</details>

<details>
<summary><b>Option B: Using Docker Compose</b></summary>

Create a `docker-compose.yml` file:
```yaml
version: '3'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:latest
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```

Run with:
```bash
docker compose up -d
```
</details>

---

### 2. Build the Application

```bash
# Using Maven wrapper
./mvnw clean install

# Or with Maven installed
mvn clean install
```

---

### 3. Run the Application

```bash
# Run via Maven wrapper
./mvnw spring-boot:run

# Or run the packaged JAR
java -jar target/producerconsumer-0.0.1-SNAPSHOT.jar
```

The application will start on port `9091`.

---

## 📡 API Usage & Testing

### Send Message (Produce)

Send a `GET` request with the message payload in the URL path:

```http
GET http://localhost:9091/produce/{message}
```

#### Example Request:
```bash
curl http://localhost:9091/produce/hello-kafka
```

#### Response:
```text
Message sent successfully
```

#### Expected Application Log (Consumer):
```log
INFO ... com.examplekafka.producerconsumer.consumer.kafkaConsumer : Message Received using Kafka listener : hello-kafka
```

---

## 📂 Project Structure

```
Kafka-SpringBoot-App/
├── src/
│   ├── main/
│   │   ├── java/com/examplekafka/producerconsumer/
│   │   │   ├── ProducerconsumerApplication.java       # Spring Boot main class
│   │   │   ├── config/
│   │   │   │   └── SpringKafkaConfig.java             # Kafka Consumer configuration
│   │   │   ├── constant/
│   │   │   │   └── ApplicationConstant.java           # Constants (topics, servers, group ID)
│   │   │   ├── consumer/
│   │   │   │   └── kafkaConsumer.java                 # @KafkaListener consumer component
│   │   │   └── producer/
│   │   │       └── KafkaProducer.java                 # REST Controller producing to Kafka
│   │   └── resources/
│   │       └── application.properties                 # Application properties (server.port=9091)
│   └── test/
│       └── java/com/examplekafka/producerconsumer/
│           └── ProducerconsumerApplicationTests.java  # Spring Boot test suite
├── pom.xml                                            # Maven configuration & dependencies
└── README.md
```

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

