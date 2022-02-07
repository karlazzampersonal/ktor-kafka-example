package com.levels.plugins

import com.levels.kafka.Kafka
import com.levels.kafka.consumer
import com.levels.kafka.kafka
import io.ktor.application.*

fun Application.configureKafka() {
    install(Kafka) {
        kafka { bootstrapServer = "localhost:9092" }
        consumer {
            groupId = "a-group"
            topics = listOf("topica")
            keyDeserializer = "org.apache.kafka.common.serialization.StringDeserializer"
            valueDeserializer = "org.apache.kafka.common.serialization.StringDeserializer"
        }
        consumer {
            groupId = "b-group"
            topics = listOf("topicb")
            keyDeserializer = "org.apache.kafka.common.serialization.StringDeserializer"
            valueDeserializer = "org.apache.kafka.common.serialization.IntegerDeserializer"
        }
    }
}
