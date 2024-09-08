#!/bin/bash

#docker run -td \
#    --name jenkins \
#    --hostname jenkins \
#    --restart=always\
#  -p 8081:8080 \
#  -p 50001:50000 \
#  -v /Users/luxu/sys/bd/master/src/jdk1.8.0_172:/usr/local/jdk1.8\
#  -v /var/run/docker.sock:/var/run/docker.sock \
#  -v /Users/luxu/sys/bd/jenkins/workspace:/root/.jenkins/workspace\
#  -v /usr/bin/git:/usr/bin/git \
#  -v /Users/luxu/sys/apache-maven-3.6.1:/usr/local/maven3 \
#  --privileged=true \
#  jenkins


docker run -td \
    --name jenkins \
    --hostname jenkins \
    --restart=always\
  -p 8081:8080 \
  -p 50001:50000 \
  -v /Users/luxu/sys/bd/master/src/jdk1.8.0_172:/usr/local/jdk1.8\
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /Users/luxu/sys/bd/jenkins/workspace:/root/.jenkins/workspace\
  -v /usr/bin/git:/usr/bin/git \
  -v /Users/luxu/sys/apache-maven-3.6.1:/usr/local/maven3 \
  --privileged=true \
  centos8.1



