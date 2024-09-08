#!/bin/bash

name=$(cat /etc/hostname)
echo "hostname is $name"
if [ $name == "master" ];then
	echo   $name
	echo "1" >/root/src/zookeeper-3.4.5/data/myid 
	sed -i "s/broker.id=0/broker.id=0/g" /usr/local/src/kafka_2.11-0.10.2.1/config/server.properties
elif [ $name == "slave1" ];then
        echo   $name
        echo "2" >/root/src/zookeeper-3.4.5/data/myid
        sed -i "s/broker.id=0/broker.id=1/g" /usr/local/src/kafka_2.11-0.10.2.1/config/server.properties

elif [ $name == "slave2" ];then
        echo   $name
        echo "3" >/root/src/zookeeper-3.4.5/data/myid
        sed -i "s/broker.id=0/broker.id=2/g" /usr/local/src/kafka_2.11-0.10.2.1/config/server.properties

fi

