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
    --name slave2 \
    --hostname slave2 \
    -p 7222:22 \
    -p 29000:9000\
    -p 29092:9092\
    -p 22181:2181\
      -p 27084:7084\
             -p 28042:8042\
      --add-host master:172.17.0.2\
      --add-host slave1:172.17.0.3\
      --add-host slave2:172.17.0.4\
      --add-host mysql:172.17.0.5\
    -v /Users/luxu/sys/bd/slave2/src:/usr/local/src \
      -v /Users/luxu/sys/bd/slave2/tmp:/tmp \
      -v /Users/luxu/project/sparkS/scripts:/root/scripts \
    --privileged=true \
    slave2 \
    /sbin/init



