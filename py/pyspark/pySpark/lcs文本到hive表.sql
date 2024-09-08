create table lcs_data(a string,b string)
row format delimited fields terminated by '\t';

load data local inpath '/home/badou/Documents/data/lcs_data.txt' overwrite into table lcs_data;

select * from lcs_data;