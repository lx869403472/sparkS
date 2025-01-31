package sparksql07

import com.huaban.analysis.jieba.JiebaSegmenter.SegMode
import com.huaban.analysis.jieba.{JiebaSegmenter, SegToken}
import org.apache.spark.SparkConf
import org.apache.spark.sql.functions.{col, udf}
import org.apache.spark.sql.{DataFrame, SparkSession}

object JiebaKry_toHive {
  def main(args: Array[String]): Unit = {
    //    定义结巴分词类的序列化
    val conf = new SparkConf()
      .registerKryoClasses(Array(classOf[JiebaSegmenter]))
      .set("spark.rpc.message.maxSize", "800")
    //    建立sparkSession,并传入定义好的Conf
    val spark = SparkSession
      .builder()
      .appName("Jieba2 UDF")
      .enableHiveSupport()
      .config(conf)
      .getOrCreate()


    // 定义结巴分词的方法，传入的是DataFrame，输出也是DataFrame多一列seg（分好词的一列）
    def jieba_seg(df: DataFrame, colname: String): DataFrame = {

      val segmenter = new JiebaSegmenter()
      val seg = spark.sparkContext.broadcast(segmenter)
      val jieba_udf = udf { (sentence: String) =>
        val segV = seg.value
        segV.process(sentence.toString, SegMode.INDEX)
          .toArray().map(_.asInstanceOf[SegToken].word)
          .filter(_.length > 1).mkString("/")
      }
      df.withColumn("seg", jieba_udf(col(colname)))
    }

    val df = spark.sql("select sentence from new_seg limit 300")
    //    df.show()
    val df_seg = jieba_seg(df, "sentence")
    df_seg.show()
    df_seg.write.mode("overwrite").saveAsTable("news_jieba")
  }
}
