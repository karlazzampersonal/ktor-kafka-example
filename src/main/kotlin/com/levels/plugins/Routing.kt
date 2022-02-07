package com.levels.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*

fun Application.configureRouting(
    producerA: KafkaProducer<String, String>,
    producerB: KafkaProducer<String, Int>
) {
    routing {

        // Send producer A record (string)
        get("/A") {
            val message = ProducerRecord(
                "topica",
                UUID.randomUUID().toString(),
                "Producer A value"
            )
            producerA.send(message)
            call.respondText("Sent request to producer A")
        }

        // Send producer B record (int)
        get("/B") {
            val message = ProducerRecord(
                "topicb",
                UUID.randomUUID().toString(),
                (0..10).random()
            )
            producerB.send(message)
            call.respondText("Sent request to producer B")
        }
    }
}
