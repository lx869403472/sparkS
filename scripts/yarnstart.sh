#!/bin/bash
hadoop_sbin=/usr/local/src/hadoop-2.6.1/sbin/

function yarnstart(){
	echo "start hadoop path $hadoop_sbin "

	ssh root@master "cd $hadoop_sbin;./start-all.sh "
	sleep 30
	command1=jps
	ssh root@master "$command1 "
	sleep 2
}

yarnstart
