package com.levels

import com.levels.kafka.*
import com.levels.kafka.Kafka.Feature.getJob
import com.levels.plugins.*
import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.launch
import org.apache.kafka.clients.producer.KafkaProducer

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        // Logging for HTTP requests
        configureCallLogging()

        // Installation of kafka below
        configureKafka()

        // Create 2 producers and pass them down to the REST routes
        val producerA: KafkaProducer<String, String> = Producer().createProducer(producerAProps())
        val producerB: KafkaProducer<String, Int> = Producer().createProducer(producerBProps())
        configureRouting(producerA, producerB)

        // On startup setup consumer jobs
        environment.monitor.subscribe(ApplicationStarted) {
            // Fetch consumer jobs by group id
            val a = ConsumerA(getJob(groupId = "a-group"))
            val b = ConsumerB(getJob(groupId = "b-group"))

            // Launch consumers in diff coroutine scopes
            launch {
                a.start()
            }
            launch {
                b.start()
            }
        }

        environment.monitor.subscribe(ApplicationStopping) {
            // Shut down each consumer job when application is about to stop
            Kafka.consumerList.forEach { it.shutdown() }
        }
    }.start(wait = true)
}
