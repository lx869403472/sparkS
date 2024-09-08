#!/bin/bash

echo "./bin/kafka-console-consumer.sh --bootstrap-server master:9092  --topic luxu --from-beginning"
cd  /root/src/kafka_2.11-0.10.2.1
./bin/kafka-console-consumer.sh --bootstrap-server master:9092  --topic luxu --from-beginning

