#!/bin/bash
 sqoop import \
 -m 1 \
--connect jdbc:mysql://master:3306/luxu \
--username root \
--password 111111 \
--table student \
--num-mappers 1 \
--hive-import \
--fields-terminated-by "," \
--hive-overwrite \
--hive-table default.mysql_student
