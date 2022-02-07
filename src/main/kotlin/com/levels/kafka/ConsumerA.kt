package com.levels.kafka

import java.time.Duration

class ConsumerA(private val job: ConsumerJob) {

    suspend fun start() = job.start {
        val records = job.consumer.poll(Duration.ofMillis(1000L))

        for (record in records) {
            val value = record.value() as String
            log.info("Consumer A reading message: $value")
        }
    }
}
