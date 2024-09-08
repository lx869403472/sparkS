package sparksql07

import org.apache.spark.sql.SparkSession

object LocalTest_06 {
  def main(args: Array[String]): Unit = {


    val spark = SparkSession
      .builder()
      .appName("test")
      .master("local[8]")
      .enableHiveSupport()
      .getOrCreate()
    //  sc = spark.sparkContext
    val path = "/Volumes/hd/BaiduNetdiskDownload/11"
    val file = spark.sparkContext.textFile(s"${path}/The_Man_of_Property.txt")


    import spark.implicits._
    val f2 = file.flatMap(line => line.split(" ")).map((_, 1))
      //    (through,1)
      //    (this,1)
      //    (Bosinney.,1)
      //    (As,1)
      //    (for,1)
      //    (telling,1)
      //    (him,1)
      //    (about,1)
      //    (anything,,1)
      //    (not,1)
      //    (a,1)
      //    (bit,1)
      //    (of,1)
      //    (it!,1)
      //    (12,3)
      //    (4,1)
      //    (313,1)

      // 1 groupBy
      //      .groupBy(_._1)
      //1.2
      //     .map(x=>(x._1,x._2.toList.map(_._2).length ))
      //        .mapValues(x=>(x.toList.map(_._2).length))

      // 2.1 groupByKey
      .groupByKey()
      .mapValues(x => x.toList.sum)
      //      .map(x=>(x._1,x._2.toList.sum))
      .toDF("x", "y")




    //2.2  reduceByKey
    //      .reduceByKey(_+_)
    //
    //    file.flatMap(line=>line.split(" ")).map((_,1)).reduceByKey(_+_)
    //
    //    f2.foreach(println)
    //    f2.saveAsTextFile("a2.txt")
    //    f2.persist()
    //    f2.cache()


    //    val n ="12 313 12 12  4".split(" ").map((_,1))
    //      .groupBy(_._1)
    //      .map(x=>(x._1,x._2.map(_._2).length))
    //
    //      n.foreach(println)
  }

}
