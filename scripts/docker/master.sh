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
    --name master \
    --hostname master \
    -p 7022:22 \
    -p 3306:3306  \
    -p 10000:10000\
    -p 9083:9083\
    -p 9000:9000\
    -p 9999:9999\
    -p 9092:9092\
    -p 2181:2181\
      --add-host master:172.17.0.2\
      --add-host slave1:172.17.0.3\
      --add-host slave2:172.17.0.4\
    -v /Users/luxu/sys/bd/master/src:/usr/local/src \
     -v /Users/luxu/sys/bd/master/hivedata:/root/hivedata \
      -v /Users/luxu/sys/bd/master/tmp:/tmp \
        -v /Users/luxu/sys/bd/mysql:/var/lib/mysql \
         -v /Users/luxu/project/sparkS/scripts:/root/scripts \
          -v /Users/luxu/project/sparkS/target:/mnt/hgfs/target \
    --privileged=true \
    master \
    /sbin/init

#    -p master:8088:8088\
#    -p master:7084:7084\
#        -p 8042:8042\
