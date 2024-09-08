-- 查询学生

SELECT classid,
       sub,
       DATA,
       count(sub)                                                                            AS allpeople,
       sum(pass) / count(sub) * 100                                                          AS p,
       sum(fail) / count(sub) * 100                                                          AS f,
       (CASE WHEN sub = '语文' THEN '是' WHEN sub = '数学' THEN '是' WHEN sub = '英语' THEN '否' END) AS im
FROM (
         SELECT classid,
                DATA,
                sub,
                rating,
                (CASE WHEN rating >= 60 THEN 1 WHEN rating < 60 THEN 0 END) AS pass,
                (CASE WHEN rating < 60 THEN 1 WHEN rating >= 60 THEN 0 END) AS fail

         FROM (
                  SELECT classid,
                         DATA,
                         '语文'    AS sub,
                         chinese AS rating
                  FROM student
                  UNION ALL
                  SELECT classid,
                         DATA,
                         '数学' AS sub,
                         math AS rating
                  FROM student
                  UNION ALL
                  SELECT classid,
                         DATA,
                         '英语'    AS sub,
                         english AS rating
                  FROM student
              ) AS alls
     ) AS cs
GROUP BY classid,
         DATA,
         sub;
-------

select classid,
       sub,
       count(sub)                                                                                        AS allpeople,
       sum(pass) / count(sub) * 100                                                                      AS p,
       sum(fail) / count(sub) * 100                                                                      AS f,
       (CASE WHEN sub = 'chinese' THEN '是' WHEN sub = 'math' THEN '是' WHEN sub = 'english' THEN '否' END) AS im

from (
         select classid,
                data,
                split(num, '_')[0]                                     as sub,
                split(num, '_')[1]                                     as rating,
                (CASE WHEN split(num, '_')[1] >= 60 THEN 1 else 0 END) AS pass,
                (CASE WHEN split(num, '_')[1] < 60 THEN 1 else 0 END)  AS fail
         from student
                  lateral view explode(
                      --split(concat('math_', math, '=', 'chinese_', chinese, '=', 'english_', englist),'=')
                          array(concat('math_', math), concat('chinese_', chinese), concat('english_', english))
                      ) n2 as num
     ) as n3
group by classid, sub;

--- incremental
-----------


--用户访问明细表存储了用户对某个app的页面访问记录，利用sq统计每天每个页面的访问人数和人均访问时长

select dat, page_name, count(uid) as peples, (max(un) - min(un)) / count(uid) as avgtime
from (
         SELECT split(visit_time, ' ')[0]  AS dat,
                uid,
                page_id,
                page_name,
                last_page_id,
                unix_timestamp(visit_time) AS un
         FROM tbl_user_visit) as m1
group by dat, page_name;



select dat, count(dat) as ds, page_name, count(page_name) as ps, count(uid) as us
from (
         SELECT split(visit_time, ' ')[0]  AS dat,
                uid,
                page_id,
                page_name,
                last_page_id,
                unix_timestamp(visit_time) AS un
         FROM tbl_user_visit) as m2
group by dat, page_name;


--字段合并 concat
select concat('a', '-', 'b', 'c') as newcoll;

--正则 regexp_extract
select regexp_extract('abcd0b', 'b', 0) as n;

--正则替换 regexp_replace
select regexp_replace('abcdddb0', 'b', '--b--') as n;

--数据类型转换 cast
select cast('111' as int) as m;

---列表合并为字符串 concat_ws
select concat_ws(' ', split('abbbcde', '')) as n;

---分组中的某列转为列表，collect_list不去重
select collect_list(data)[0] as n
from student
group by classid;


---分组中的某列转为集合，collect_set去重
select collect_set(data) as n
from student
group by classid;


---分组中的某列转为列表，并合并为字符串
select concat_ws(',', collect_list(data)) as n
from student
group by classid;

--map


select   ( map('clientname', 'clientid', 'key', 'value'));

select map_col["sub"]
from (
         select sub, map(sub, classid, data, classid) as map_col
         from student2
     ) t;


-- 数组
select   (array("a", "b", "c", "d", "9", "2", "7")) as x;

select size(split("abcdef", "")) as a;

--sort by
select sub, rating, count(rating) over (distribute by sub sort by rating desc) as row_num
from student2;

--order by
select sub, rating, count(rating) over (partition by sub order by rating desc) as row_num
from student2;


select cast("123" as float);

--分词
select sentences("我1们1爱1中1国", "1") as a;


select user_id,
       collect_list(order_id),
       sum(case order_dow when '0' then 1 else 0 end) as dow_0,
       sum(case order_dow when '1' then 1 else 0 end) as dow_1,
       sum(case order_dow when '2' then 1 else 0 end) as dow_2,
       sum(case order_dow when '3' then 1 else 0 end) as dow_3,
       sum(case order_dow when '4' then 1 else 0 end) as dow_4,
       sum(case order_dow when '5' then 1 else 0 end) as dow_5,
       sum(case order_dow when '6' then 1 else 0 end) as dow_6
from orders
group by user_id
limit 20;

select user_id,
       (case order_dow when '0' then 1 else 0 end) as dow_0,
       (case order_dow when '1' then 1 else 0 end) as dow_1,
       (case order_dow when '2' then 1 else 0 end) as dow_2,
       (case order_dow when '3' then 1 else 0 end) as dow_3,
       (case order_dow when '4' then 1 else 0 end) as dow_4,
       (case order_dow when '5' then 1 else 0 end) as dow_5,
       (case order_dow when '6' then 1 else 0 end) as dow_6
from orders;


create table rank_test as
select user_id, order_dow
from orders
limit 100;

select user_id,
       if(days_since_prior_order = '', '0.0', days_since_prior_order) as dt
from orders
limit 20;

select user_id, product_id, count(1) as prod_buy_cnt
from orders t1
         join products t2
              on t1.order_id = t2.order_id
group by user_id, product_id
limit 20;

select user_id,
       product_id,
       prod_buy_cnt,
       row_number() over (partition by user_id order by prod_buy_cnt desc) as row_num
from (
         select user_id, product_id, count(1) as prod_buy_cnt
         from orders t1
                  join products t2
                       on t1.order_id = t2.order_id
         group by user_id, product_id
     ) t12
limit 20;


select user_id,
       product_id,
       prod_buy_cnt,
       row_number() over (distribute by user_id sort by prod_buy_cnt desc) as row_num
from (
         select user_id, product_id, count(1) as prod_buy_cnt
         from orders t1
                  join products t2
                       on t1.order_id = t2.order_id
         group by user_id, product_id
     ) t12
limit 20;


--sort by
select sub, rating, count(rating) over (distribute by sub sort by rating desc) as row_num
from student2;

--order by
select sub, rating, count(rating) over (partition by sub order by rating desc) as row_num
from student2;

set mapred.reduce.tasks= 1;
select sub, collect_list(rating) as ra
from student2
group by student2.sub
having avg(rating) > 70;

set mapred.reduce.tasks= 1;
select order_id, count(distinct user_id)
from orders
group by order_id;



show tables;
select *
from products;



select (cast("3" as int) + cast("4" as int)) as sum;



drop table order_partition2;

--- 建表  分区表
create table order_partition6
(
    order_id     string,
    user_id      string,
    order_number string

)
    partitioned by (dt string);
--     row format delimited fields terminated by ',';


create table order_partition6
(
    order_id string,
    user_id  string,
    dt       string
)
--     partitioned by(dt string)
    clustered by (dt) into 4 buckets;

select *
from order_partition6;


desc order_partition6;


select count(1)
from order_partition;


create table news_jieba
(
    classid   string,
    studentid string,
    sub       string,
    score     string
)
    row format delimited fields terminated by ',';
select *
from news_jieba;

select *
from orders
limit 10;
select *
from products
limit 10;
------------
--导入数据本地文件系统
load data local inpath "/root/table.csv" into table score;

--hdfs 文件系统导入表
load data  inpath "/root/table.csv" into table score;


select studentid, collect_list(sub) as sub, collect_list(score) as rating
from score
where score < 60
group by studentid
having count(score) >= 2
order by studentid;

select *
from score;


---计算每个班级，每个科目排名第一的 同学
SELECT classid,
       studentid,
       sub,
       score
FROM (
         SELECT classid,
                studentid,
                sub,
                score,
                row_number() over ( distribute BY classid, sub sort BY score desc) AS sortrating
         FROM score
     ) t1
WHERE sortrating = 1;

----------
---计算个科目不及格大于等于2个的学生，并按照学生id 升序，科目倒序

SELECT classid,
       studentid,
       sub,
       score,
       sortrating
FROM (
         SELECT classid,
                studentid,
                sub,
                score,
                rank() over ( distribute BY classid, sub sort BY score desc) AS sortrating
         FROM score
         where score < 60
     ) t1
where sortrating <= 2
order by studentid, sub desc;



--

--

drop table badou.orders;

--拷贝表，查询一个表并新建一个表结构一致的表，并插入数据
create table badou. as
select *
from orders
limit 1000;


select order_id, user_id, order_dow
from badou.orders
limit 300;


select *
from orders;
select 4 * 6 / 3;

select user_id, item_id, rating
from udata
limit 1000000;


--- 查询表，数据打出至本地文件系统,指定分隔符为 "，"
insert overwrite local directory '/root/orders_output/' row format delimited fields terminated by ','
select order_id, user_id, order_dow
from orders;

create table order_bucket
(
    order_id string,
    user_id  string,
    dt       string
)
--     partitioned by(dt string)
    clustered by (dt) into 32 buckets
    row format delimited fields terminated by ',';
drop table order_bucket;

insert overwrite table order_bucket
select order_id, user_id, order_dow
from orders;

load data local inpath '/root/orders_output/order_partition6' into table order_bucket;

select *
from student2;
select *
from order_bucket tablesample (bucket 1 out of 128 on order_id);


select classid, sub, rating, sum(rating) over (partition by classid,sub) as csub
from student2;



select classid, sub, rating, count(rating) over (partition by classid,sub) as csub
from student2;

select *
from order_partition
limit 10;

select rank() over (SORT BY rating)
from student2;


select /*+mapjoin(t) */ *
from order_partition t;



