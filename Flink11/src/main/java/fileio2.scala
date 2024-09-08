import java.io._
import scala.io._
import com.alibaba.fastjson.{JSON, JSONException, JSONObject}

object fileio2 {

  var m: List[Any] = List()


  def main(args: Array[String]): Unit = {
    JsonString_toArray()
    Array_toJsonString()
  }


  def JsonString_toArray(): Unit = {

    """
      |json String to Map,write file
      |""".stripMargin


    val path = s"/Users/luxu/project/sparkS/Flink11/src/main/resources/test2_json.txt"
    val path2 = s"/Users/luxu/project/sparkS/Flink11/src/main/resources/aisles2.csv"
    val fp = new PrintWriter(new File(s"$path2"))

    Source.fromFile(path).getLines().toList
      .map{JSON.parseObject(_).values().toArray.mkString(",") + "\n"}
      .map(fp.write(_))
    fp.close()
  }



  def Array_toJsonString(): Unit ={

    val path = s"/Users/luxu/project/sparkS/Flink11/src/main/resources/aisles.csv"
    val path2 = s"/Users/luxu/project/sparkS/Flink11/src/main/resources/test2_json.txt"
    val fp = new PrintWriter(new File(s"$path2"))
    val header=List("aisle_id","aisle")


    val f =Source.fromFile(path).getLines().toList
      .map{x=>
        val json = new JSONObject
        val data= x.replace("\n","").split(",")
        (header zip data).map(y=>json.put(y._1,y._2))
        json.toString+"\n"
      }

    f .foreach(print)
    f.map(fp.write(_))
    fp.close()


  }

}
