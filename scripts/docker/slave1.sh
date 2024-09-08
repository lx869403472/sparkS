#!/bin/bash
# hiveserver2 10000
# meta 9083
# hadoop ui 8088
# hdfs 9000
#kafka 9092
#zookeeper 2181
# mysal 3306
#ssh 22

docker run -itd \
    --name slave1 \
    --hostname slave1 \
    -p 7122:22 \
    -p 19000:9000\
    -p 19092:9092\
    -p 12181:2181\
      -p 17084:7084\
             -p 18042:8042\
      --add-host master:172.17.0.2\
      --add-host slave1:172.17.0.3\
      --add-host slave2:172.17.0.4\
      --add-host mysql:172.17.0.5\
    -v /Users/luxu/sys/bd/slave1/src:/usr/local/src \
      -v /Users/luxu/sys/bd/slave1/tmp:/tmp \
      -v /Users/luxu/project/sparkS/scripts:/root/scripts \
    --privileged=true \
    slave1 \
    /sbin/init