#!/bin/bash


docker run -tid\
    --name nginx \
    --hostname nginx \
      --add-host master:172.17.0.2\
      --add-host slave1:172.17.0.3\
      --add-host slave2:172.17.0.4\
      --add-host mysql:172.17.0.5\
  -p 8082:80\
      -p 7084:7084\
        -p 8042:8042\
            -p 8088:8088\
  -v /Users/luxu/sys/bd/nginx/default.conf:/etc/nginx/conf.d/default.conf\
      --privileged=true \
  nginx\
  /bin/bash
