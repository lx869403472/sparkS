#!/bin/bash

sqoop import \
  --connect jdbc:mysql://master:3306/luxu \
  --username root \
  --password 111111 \
   --query 'select * from student where $CONDITIONS limit 1;'\
  --target-dir /input/mysql/student1 \
  --delete-target-dir \
  --columns classid,data \
  --num-mappers 1 \
  --fields-terminated-by ","
