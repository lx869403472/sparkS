#!/bin/bash

/root/src/flink-1.4.0/bin/flink run \
	-m yarn-cluster \
	-yn 2  \
	-c s12.KafkaConsumerTest_12 \
	/mnt/hgfs/target/sparkS-1.0-jar-with-dependencies.jar
