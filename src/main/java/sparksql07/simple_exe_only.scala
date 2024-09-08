package sparksql07

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{collect_set, _}
//import org.apache.spark.sql.functions._

object simple_exe_only {

  def main(args: Array[String]): Unit = {


    val spark = SparkSession
      .builder()
      .appName("test")
      .master("local[4]")
      .enableHiveSupport()
      .getOrCreate()

    import spark.implicits._

//    1111
    /** product feature
      * 1. 销售量prod_cnt
      * 2. 商品被再次购买（reordered）量prod_sum_rod
      * 3. 统计reordered比率prod_rod_rate   avg=sum/count  [0,1]
      */

      val priors=spark.sql("select * from products")
      val orders =spark.sql("select * from orders")

//    * 1. 销售量prod_cnt
      val prod_cunt=priors.groupBy("product_id")
          .count().withColumnRenamed("count","cc")

//    prod_cunt.show()
    val prodRodCnt=priors.selectExpr("product_id","cast(reordered as int)")
//      .withColumnRenamed("product_id","product_id2")
      .groupBy("product_id")
      .agg(
      sum("reordered").as("reordered_sum"),
      avg("reordered").as("reordered_avg"),
      count("reordered").as("reordered_cnt"),
      collect_set("reordered").as("reordered_list")
    ).limit(10)

    val m=prodRodCnt.drop("product_id").limit(2)  //删除一列
    //    prodRodCnt.show()
    //    m.show()



    //2
    /** user Features:
      * 1. 每个用户购买订单的平均间隔 days orders
      * 2. 每个用户的总订单数
      * 3. 每个用户购买的product商品去重后的集合数据  user_id , set{prod1,prod2....}
      * 4. 用户总商品数量以及去重后的商品数量
      * 5. 每个用户平均每个订单有多少商品
      */

    val ordersNew = orders.selectExpr("*", "if(days_since_prior_order='',0.0,days_since_prior_order) as dspo").drop("days_since_prior_order")

    val userGap = ordersNew.selectExpr("user_id","cast(dspo as int) as dspo").groupBy("user_id").avg("dspo").withColumnRenamed("avg(dspo)","u_avg_day_gap")

    //    2. 每个用户的总订单数
    val userOrdCnt = orders.groupBy("user_id").count()
    //    3. 每个用户购买的product商品去重后的集合数据   用户 product
    val opDF = orders.join(priors,"order_id")
    val up = opDF.select("user_id","product_id")
//    up.show()

    import spark.implicits._
    //    up.rdd.map()从DataFrame转变成rdd的数据，
    // rdd.toDF()从rdd变成DataFrame，这里返回时tuple2，所以在DF中是两列
//    val userUniOrdRecs = up.rdd.map{x=>(x(0).toString,x(1).toString)}.groupByKey().mapValues(_.toSet.mkString(",")).toDF("user_id","prod_records")

    val userUniOrdRecs2=up.groupBy("user_id").agg(
      concat_ws("," ,collect_set("product_id")).as("prod_records2"),
      size(collect_list("product_id")).as("count")

    )
    userUniOrdRecs2.show()


  }

}
