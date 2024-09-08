#!/bin/bash
spark-submit \
	--class cf17.usercf2 \
	--master yarn-cluster \
	--executor-memory  1G \
	--total-executor-cores 2 \
	--files $HIVE_HOME/conf/hive-site.xml \
	/mnt/hgfs/target/sparkS-1.0-jar-with-dependencies.jar
