package sparksql07

import org.apache.spark.sql.SparkSession

object TestFunc_06 {
  def main(args: Array[String]): Unit = {
    //    实例化sparksession 在client端自动实例化sparksession
    //    Spark session available as 'spark'.
    val spark = SparkSession
      .builder()
      .appName("test")
      .master("local[*]")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select * from orders")
    val priors = spark.sql("select * from products")

    df.show()
    //
    //    """
    //      |4.每个用户根据order_hour_of_day这列的值对order_dow进行排序
    //      |1  2 08
    //      |1  3 07
    //      |
    //
    //      |1 [(2,08),(3,07)]
    //      |
    //      |=> 1 [(3,07),(2,08)] 一个用户最喜爱购买商品的top3
    //      |rdd: (user_id,(order_number,order_hour_of_day))   //DataFrame转RDD
    //    """.stripMargin
    //
    //    import spark.implicits._
    //    val orderNumberSort = df.select("user_id","order_number","order_hour_of_day")
    //      .rdd.map(x=>(x(0).toString,(x(1).toString,x(2).toString)))
    //      .groupByKey()
    //      .mapValues(_.toArray.sortWith(_._2<_._2).slice(0,2))
    //      .toDF("user_id","order_sort_by_hour")
    //      orderNumberSort.show()
    //
    ////    udf
    //    import org.apache.spark.sql.functions._
    //    val plusUDF = udf((col1:String,col2:String)=>col1.toInt+col2.toInt)
    //    df.withColumn("plus",plusUDF(col("order_number"),col("order_dow"))).show()

  }
}
