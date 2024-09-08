select classid,
       sub,
       count(sub)  AS allpeople,
       sum(pass) / count(sub) * 100  AS p,
       sum(fail) / count(sub) * 100   AS f,
       (CASE WHEN sub = 'chinese' THEN '是' WHEN sub = 'math' THEN '是' WHEN sub = 'english' THEN '否' END) AS im
from (select classid,
             data,
             split(num, '_')[0]  as sub,
             split(num, '_')[1]    as rating,
             (CASE WHEN split(num, '_')[1] >= 60 THEN 1 else 0 END) AS pass,
             (CASE WHEN split(num, '_')[1] < 60 THEN 1 else 0 END)  AS fail
      from student lateral view explode(
              split(concat('math_', math, '=', 'chinese_', chinese, '=', 'english_', english), '=')) n2 as num) as n3
group by classid, sub;




----
select  classid,
        max(distinct data),
        sub,
        count( sub ) AS allpeople,
        sum(CASE WHEN rating >= 60 THEN 1 else 0 END)/count(rating)* 100  as pass1,
        sum(CASE WHEN rating <60 THEN 1 else 0 END)/count(rating)* 100  as fail1,
        ( CASE WHEN sub = 'chinese' THEN '是' WHEN sub = 'math' THEN '是' WHEN sub = 'english' THEN '否' END ) AS im

from (
         select classid,
                data,
                split(num, '_')[0] as sub,
                split(num, '_')[1] as rating

         from student
                  lateral view explode(
                          split(concat('math_', math, '=', 'chinese_', chinese, '=', 'english_', english),
                                '=')) n2 as num
     ) as n3
group by classid,sub;



select classid,
       row_number() over (partition by sub order by  rating asc) as s
from student2;

select  * from rank_test;

select  user_id,order_dow,row_number() over (distribute by user_id sort by order_dow) as new_sort from rank_test;
select  user_id,order_dow,row_number() over (partition by user_id order by order_dow) as new_sort from rank_test;

select  user_id,order_dow,rank() over (distribute by user_id sort by order_dow) as new_sort from rank_test;
select  user_id,order_dow,dense_rank() over (partition by user_id order by order_dow) as new_sort from rank_test;

show tables;
desc art_dt;


create table art_dt(sentence string)
    partitioned by (dt string);

insert overwrite table art_dt partition (dt="01")
select user_id from rank_test;
select * from art_dt;


select  /*+STREAMTABLE(o)*/  * from orders  o join products  p on  o.order_id=p.order_id ;
select  /*+MAPJOIN(o)*/  * from orders  o join products  p on  o.order_id=p.order_id ;




create external table  h_orders(user_id string,order_id string,order_dow string)
    STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
        WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,id:order,num:dow")
    TBLPROPERTIES("hbase.table.name" = "orders");

select * from  h_orders;

drop table hive_student;

show tables;

create   table hive_student( classid string,
                             data string, math string,chinese string,englist string
)
    row format delimited fields terminated by ','
;


create table hive_student as  select * from student;

truncate table hive_student;

select * from hive_student;




select * from departments2;

create external table  departments2 (id string,name string)
    row format delimited fields terminated by ',';

load data  local inpath  'departments.csv' into table art_dtp;


create  external table  cvm (id string,name string)
    clustered by (id) into 4 buckets
    row format delimited  fields terminated by ',';


load data local inpath '/root/cvm.txt' into table cvm;

select name from cvm tablesample ( bucket 1 out of 2 on id);



alter table cvm rename to cvm_new;

create   table  cvm2 (name string)
    partitioned by (id string)
    clustered by (id) into 4 buckets
    row format delimited  fields terminated by ',';


select rating,   row_number () over ()from student2;
select * from student2;

select

    sub ,rating,
    sum(rating) over(partition by sub order by rating rows between current row and UNBOUNDED FOLLOWING ) as sample7

from student2;


select classid,
       sub,
       rating,
       lag(rating, 2, "0") over (order by classid) as n,
        lead(rating, 2, "0") over (order by classid) as s
from student2;




select  classid,
        collect_list( case when sub='math' then rating end )as math,
        collect_list( case when sub='english' then rating end) as english,
        collect_list(case when sub='chinese' then rating end )as chinese

from student2 group by  classid;



--- student 建表
drop table student;
create table student ( classid string,
                        data string, chinese string,math string,english string
)  row format delimited  fields terminated by ',' ;

load data local inpath '/root/hivedata/student' into table student;


select classid,
       data,
       num[0]                                     as sub,
       num[1]                                    as rating
--        (CASE WHEN split(num, '_')[1] >= 60 THEN 1 else 0 END) AS pass,
--        (CASE WHEN split(num, '_')[1] < 60 THEN 1 else 0 END)  AS fail
from student
         lateral view explode(
             --split(concat('math_', math, '=', 'chinese_', chinese, '=', 'english_', englist),'=')
                 array(array('math', math), array('chinese', chinese), array('english', english))
             ) n25 as num
;

drop table  mysql_student;

create  table mysql_student
(
    classid string,
    data string,
    math string,
    chinese string,
    englist string
)
    row format delimited  fields terminated by ','
;

load data inpath 'hdfs://master:9000/user/hive/warehouse/mysql_student' into table mysql_student;


create table products(product_id string,product_name string,aisle_id string,department_id string)row format delimited fields terminated by ',';
load data inpath '/root/data/product.csv' into table products;



create table orders(order_id string,user_id	string,eval_set string,order_number string,order_dow string,order_hour_of_day string,days_since_prior_order string)row format delimited fields terminated by ',';
load data local inpath '/data/orders.csv' into table orders;




select * from orders limit 10;

insert overwrite local directory '/root/orders10'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
select *
from orders  limit 10;


select * from orders  where order_hour_of_day between 15 and 70 limit 10;



CREATE  TABLE t_result_analysis_web_stat
(
    appid int,
    type  string
)
    PARTITIONED BY (
        datecol string,identify string)
    ROW FORMAT DELIMITED
        FIELDS TERMINATED BY ',';



insert into  t_result_analysis_web_stat partition (datecol='luxu',identify='28') select  order_id,user_id from orders limit 10;



select * from t_result_analysis_web_stat;

drop table t_result_analysis_web_stat;

select * from orders limit 10;


set mapred.reduce.tasks=5;

select  * from
(select  order_id,cast(user_id as INT) as userid from orders) as t
sort by t.userid;