#!/bin/bash
sqoop import -m 1 \
  --connect jdbc:mysql://master:3306/luxu \
  --username root \
  --password 111111 \
  --table student \
  --target-dir /usr/hive/warehouse/mysql_student