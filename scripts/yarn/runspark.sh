#!/bin/bash
spark-submit \
	--class s17_cf协同过滤.usercf2 \
	--master yarn-cluster\
	--files /usr/local/src/apache-hive-1.2.2-bin/conf/hive-site.xml \
	/mnt/hgfs/target/sparkS-1.0-jar-with-dependencies.jar
