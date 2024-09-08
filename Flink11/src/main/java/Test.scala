import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
//import org.apache.flink.streaming.api.windowing.time.Time

object Test {
  def main(args: Array[String]): Unit = {
//    val benv = ExecutionEnvironment.getExecutionEnvironment
//    val path = "D:\\data\\data"
//    val data = benv.readTextFile(s"$path\\orders.csv")
////    data.print()
//    val wordCounts = data.map(_.split(",")(4)).map((_,1))
//      .groupBy(0).sum(1)
//    wordCounts.print()

    val senv = StreamExecutionEnvironment.getExecutionEnvironment
    val data = senv.socketTextStream("localhost",9999,'\n')

    val wordCounts = data
      .map((_,10))
      .keyBy(0)
//      .window(TumblingEventTimeWindows.of(Time.seconds(5))).sum(1)
      .timeWindow(Time.seconds(5),Time.seconds(5))
      .sum(1)
//      .countWindowAll(2)

      .print()
//    wordCounts.assignAscendingTimestamps().print()
    senv.execute("test")


  }

}
