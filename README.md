# Ktor kafka example

An example repo for the ktor kafka plugin: 
https://github.com/karlazzampersonal/ktor-kafka

In this example, I spin up 2 producers and a consumer for each one

- One producer that writes a string "Producer A value" to topic `topica`. It's invoked by hitting `localhost:8080/A` 
- Another producer that writes a random int from 1-10 to topic `topicb`. It's invoked by hitting `localhost:8080/B` 

Example logs:
```
2022-02-06 20:19:33.368 [DefaultDispatcher-worker-1] INFO  kafka-consumer-logger-b-group - Consumer A reading message: Producer A value
2022-02-06 20:19:33.526 [eventLoopGroupProxy-4-1] INFO  ktor.application - 200 OK: GET - /A
2022-02-06 20:19:43.927 [eventLoopGroupProxy-4-1] INFO  ktor.application - 200 OK: GET - /B
2022-02-06 20:19:43.937 [DefaultDispatcher-worker-2] INFO  kafka-consumer-logger-b-group - Consumer B reading message: 7
2022-02-06 20:19:50.806 [eventLoopGroupProxy-4-1] INFO  ktor.application - 200 OK: GET - /A
2022-02-06 20:19:50.810 [DefaultDispatcher-worker-1] INFO  kafka-consumer-logger-b-group - Consumer A reading message: Producer A value
```
