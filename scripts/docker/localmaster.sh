#!/bin/bash
# hiveserver2 10000
# meta 9083
# hadoop ui 8088
# hdfs 9000
#kafka 9092
#zookeeper 2181
# mysal 3306
#ssh 22
#worker 8042

docker run -td \
    --name yarn \
    --hostname master \
    -p 8022:22 \
    -p 3306:3306  \
    -p 20000:10000\
    -p 9083:9083\
    -p 9000:9000\
    -p 9090:9090\
    -p 9999:9999\
    -p 9092:9092\
    -p 12181:2181\
    -p 8088:8088\
    -p 8443:8843\
    --privileged=true \
    admluxu-docker.pkg.coding.net/bd/work/master:v2 \
    /sbin/init


docker run -td \
    --name yarn \
    --hostname master \
    -P \
    --privileged=true \
    admluxu-docker.pkg.coding.net/bd/work/master:v2 \
    /sbin/init
