package sparksql07
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object simpleFeature07 {


    def main(args: Array[String]): Unit = {

      val spark = SparkSession
        .builder()
        .appName("test")
        .master("local[2]")
        .enableHiveSupport()
        .getOrCreate()
      val priors = spark.sql("select * from products")
      val orders = spark.sql("select * from orders")
//      priors.show()
//      val priors=spark.sql("show tables")
//      priors.show()
      /** product feature
        * 1. 销售量prod_cnt
        * 2. 商品被再次购买（reordered）量prod_sum_rod
        * 3. 统计reordered比率prod_rod_rate   avg=sum/count  [0,1]
        */
      //    销售量prod_cnt
      val prodCnt = priors.groupBy("product_id").count().withColumnRenamed("count","prod_cntc")
      //   prod_sum_rod
      val prodRodCnt = priors.selectExpr("product_id","cast(reordered as int)")
        .groupBy("product_id")
        .agg(sum("reordered").as("prod_sum_rod"),
          avg("reordered").as("prod_rod_rate"),
          count("product_id").as("prod_cnt")
        )

      /** user Features:
        * 1. 每个用户购买订单的平均间隔 days orders
        * 2. 每个用户的总订单数
        * 3. 每个用户购买的product商品去重后的集合数据  user_id , set{prod1,prod2....}
        * 4. 用户总商品数量以及去重后的商品数量
        * 5. 每个用户平均每个订单有多少商品
        */
      //  异常值处理：将days_since_prior_order中的空值进行处理
      val ordersNew = orders.selectExpr("*",
        "if(days_since_prior_order='',0.0,days_since_prior_order) as dspo")
        .drop("days_since_prior_order")

      //    1.每个用户购买订单的平均间隔 days orders
      val userGap = ordersNew.selectExpr("user_id","cast(dspo as int) as dspo")
        .groupBy("user_id").avg("dspo").withColumnRenamed("avg(dspo)","u_avg_day_gap")

      //    2. 每个用户的总订单数
      val userOrdCnt = orders.groupBy("user_id").count()
      //    3. 每个用户购买的product商品去重后的集合数据   用户 product
      val opDF = orders.join(priors,"order_id")
      val up = opDF.select("user_id","product_id")

      import spark.implicits._
      //    up.rdd.map()从DataFrame转变成rdd的数据，
      // rdd.toDF()从rdd变成DataFrame，这里返回时tuple2，所以在DF中是两列
      val userUniOrdRecs = up.rdd.map{x=>(x(0).toString,x(1).toString)}
        .groupByKey()
        .mapValues(_.toSet.mkString(","))
        .toDF("user_id","prod_records")

      userUniOrdRecs.show()


      //    4. 用户总商品数量以及去重后的商品数量
      val userAllProd = up.groupBy("user_id").count()

      val userUniOrdCnt = up.rdd.map{x=>(x(0).toString,x(1).toString)}
        .groupByKey()
        .mapValues(_.toSet.size)
        .toDF("user_id","prod_dist_cnt")

      //    当有groupByKey的处理逻辑两个类似的方法时，看能不能合并
      //    合并“去重后的集合数据”和“去重后的商品数量”统计逻辑
      //    第一种合并提取公因子
      val userRddGroup = up.rdd.map(x=>(x(0).toString,x(1).toString)).groupByKey().cache()
      userRddGroup.unpersist() // python del userRddGroup
      //    val userUniOrdRecs = userRddGroup.mapValues(_.toSet.mkString(",")).toDF("user_id","prod_records")
      //    val userUniOrdCnt = userRddGroup.mapValues(_.toSet.size).toDF("user_id","prod_dist_cnt")

      // 第二种同时计算两个
      val userProRcdSize = up.rdd.map{x=>(x(0).toString,x(1).toString)}.groupByKey()
        .mapValues{records=>
          val rs = records.toSet;
          (rs.size,rs.mkString(","))
        }.toDF("user_id","tuple")
        .selectExpr("user_id","tuple._1 as prod_dist_size","tuple._2 as prod_records")

      val usergroup = up.groupBy("user_id")
        .agg(size(collect_set("product_id")).as("prod_dist_size"),
          collect_set("product_id").as("prod_records"))
      //    5. 每个用户平均每个订单有多少商品
      //    1)先求每个订单多少商品
      val ordProdCnt = priors.groupBy("order_id").count()
      //    2）求每个用户订单商品数量的平均值
      val userPerOrdProdCnt = orders.join(ordProdCnt,"order_id")
        .groupBy("user_id")
        .agg(avg("count").as("u_avg_ord_prods"))
    }

    def feat(priors:DataFrame,orders:DataFrame):DataFrame={
      priors
    }




}
