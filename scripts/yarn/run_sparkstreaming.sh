#!/bin/bash


cd /usr/local/src/spark-2.0.2-bin-hadoop2.6/
./bin/spark-submit \
	--class s09.HdfsWordCount09 \
	--master yarn-cluster \
	--files /usr/local/src/apache-hive-1.2.2-bin/conf/hive-site.xml \
	/mnt/hgfs/target/sparkS-1.0-jar-with-dependencies.jar \
	hdfs://master:9000/input/streaming/log
