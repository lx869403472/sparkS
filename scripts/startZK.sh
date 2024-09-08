#!/bin/bash

function zookeeper ( ){
	zkbin=/usr/local/src/zookeeper-3.4.5/bin
        command="./zkServer.sh $1"
	echo "================ master ========================="
	ssh root@master  "cd  $zkbin; $command"
	sleep 2

	
	echo "================ slave1 ========================="
	
	ssh root@slave1  "cd  $zkbin; $command"
	sleep 2

	echo "================ slave2 ========================="
	ssh root@slave2  "cd  $zkbin; $command"
	sleep 2


}

if [ "x"$1 == "x" ]
	then
	echo "start zk "
	zookeeper start

elif [ "x"$1 == "xstatus"  ]
        then
        echo "status zk "
        zookeeper status

elif [ "x"$1 == "xstop"  ]
        then
        echo "stop zk "
        zookeeper stop

fi

