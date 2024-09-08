# -*- coding: utf-8 -*-
from pyspark.sql.functions import udf
from pyspark.sql.types import IntegerType
from pyspark.sql import SparkSession

# pip install pyspark(管理员下运行cmd)

# 解决编码问题
import sys
# reload(sys)
# sys.setdefaultencoding('utf-8')

spark = SparkSession.builder \
    .appName("LCS Test") \
    .enableHiveSupport() \
    .getOrCreate()

df = spark.sql("select * from orders")
df.show()


def lcs(a, b):
    n = len(a)
    m = len(b)
    l = [[0] * (m + 1) for x in range(n + 1)]
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if a[i - 1] == b[j - 1]:
                l[i][j] = l[i - 1][j - 1] + 1
            else:
                # l[i][j] = max(l[i - 1][j], l[i][j - 1])
                l[i][j] = l[i - 1][j] if l[i - 1][j]>l[i][j - 1] \
                    else l[i][j - 1]
    return l[-1][-1]




lcs_udf = udf(lcs, IntegerType())
df_score = df.withColumn("score", lcs_udf(df.order_id, df.user_id))
df_score.show()