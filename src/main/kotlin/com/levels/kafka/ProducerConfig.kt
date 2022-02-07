package com.levels.kafka

import com.levels.kafka.Kafka.Feature.kafkaConfig
import java.util.*

fun producerAProps(): Properties {
    val props = Properties()
    props.setProperty("bootstrap.servers", kafkaConfig.bootstrapServer)
    props.setProperty("acks", "all")
    props.setProperty("retries", "0")
    props.setProperty("linger.ms", "1")
    props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    return props
}

fun producerBProps(): Properties {
    val props = Properties()
    props.setProperty("bootstrap.servers", kafkaConfig.bootstrapServer)
    props.setProperty("acks", "all")
    props.setProperty("retries", "0")
    props.setProperty("linger.ms", "1")
    props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")
    return props
}
