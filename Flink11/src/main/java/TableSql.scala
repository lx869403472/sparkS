import com.alibaba.fastjson.{JSON, JSONException, JSONObject}
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.table.api.scala._

import java.io.{File, PrintWriter}

object TableSql {
  case class Aisle(aisle_id:String,aisle: String)
  case class Save(aisle_id:String, a: String)

  def main(args: Array[String]): Unit = {
    val benv = ExecutionEnvironment.getExecutionEnvironment

    val path = "/Users/luxu/project/sparkS/Flink11/src/main/resources/aisles.csv"
    val jsonpath=s"/Users/luxu/project/sparkS/Flink11/src/main/resources/test2_json.txt"

    """
      |csv 文本直接读取
      |""".stripMargin
        val input = benv.readCsvFile[Aisle](s"$path",ignoreFirstLine = true)

        """
          |text 文件读取
          |""".stripMargin
//
//    val input = benv.readTextFile(s"$path")
//      .map(x=>{x.replace("\n","").split(",")}.toList)
//      .map(x=>Aisle(x(0),x(1)))

//        """
//          |json 字符串读取，转换
//          |""".stripMargin
//
//        val input = benv.readTextFile(s"$jsonpath")
//            .map(JSON.parseObject(_).values()
//              .toArray()
//              .mkString(",").split(",")
//            )
//          .map(x=>Aisle(x(0),x(1)))


    //




        val tableEnv = TableEnvironment.getTableEnvironment(benv)
        tableEnv.registerDataSet("aisles",input)
        val sqlString =s"select aisle_id,aisle as a  from aisles "
        val res = tableEnv.sqlQuery(s"$sqlString")

        val f=res.toDataSet[Save]
        f.writeAsCsv("/Users/luxu/project/sparkS/Flink11/src/main/resources/s2.csv")


        benv.execute("sss")
  }

}
