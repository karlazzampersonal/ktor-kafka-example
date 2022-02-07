package com.levels.kafka

import java.time.Duration

class ConsumerB(private val job: ConsumerJob) {

    suspend fun start() = job.start {
        val records = job.consumer.poll(Duration.ofMillis(1000L))

        for (record in records) {
            val value = record.value() as Int
            log.info("Consumer B reading message: $value")
        }
    }
}
