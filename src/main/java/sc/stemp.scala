package sc

object stemp {


  def a1(): Unit ={

    //1、创建  数组 Array的5个元素的空数组、2赋值
    var  a= new Array[String](5)   //创建一个数组 长度为5
    println(a.length)
    a(1)="hello"
    println(a(1),"a[1]")  //赋值 给index为1的 = hello
    println("-"*10)
    a.foreach(println)

    println("-"*10)
    println(a.size,"size")
    println("=-"*10)
    a.update(4,"33333")

    println(a(4))
  }



  def main(args: Array[String]): Unit = {

      a1()
     var m=Array("a","b","c")


    //二 直接定义一个数组
    var b=Array(1,2,3,4,5,6)
    b.foreach(println)
    println(b.max,"    max")
    println(b.min,"    min")
    println(b.sum,"    sum")
    println("-"*10)

    println(b.mkString("<","--",">"),"  join")


  }

}
