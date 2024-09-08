#!/bin/bash
# hiveserver2 10000
# meta 9083
# hadoop ui 8088
# hdfs 9000
#kafka 9092
#zookeeper 2181
# mysal 3306
#ssh 22

docker run -itd  \ --name mysql   \ --hostname mysql  \ --add-host master:172.17.0.2\ --add-host slave1:172.17.0.3\ --add-host slave2:172.17.0.4\ --add-host mysql:172.17.0.5\ -p 422:22 \ -p 3306:3306  \ -v /Users/luxu/sys/bd/mysql:/var/lib/mysql \ --privileged=true \ mysql \ /sbin/init

#Error response from daemon: user specified IP address is supported on user defined networks only.

