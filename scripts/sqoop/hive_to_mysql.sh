#!/bin/bash
sqoop export \
--connect jdbc:mysql://master:3306/luxu \
--username root \
--password 111111 \
--table student_1 \
--num-mappers 1 \
--export-dir /user/hive/warehouse/mysql_student/part-m-00000 \
--input-fields-terminated-by "\t"