package sc

object lcs01 {

  def lcs1(a:String,b:String): Unit ={

    val na =a.length+1
    val nb=b.length+1
    println(na ,nb)


    // 每na 长度为1组 共有nb组
    var c= new Array[Int](na*nb).grouped(na).toList
//    c.foreach(println)

    val s1=(0 to na).map(x=>0).toList
    var c2=(0 to nb).map(x=>s1).toList
//    c2.foreach(println)



    for ( y <-   0 to (nb-2) ){
      for (x <-  0 to (na-2) ){

          val t =c(y)(x)

        if ( a(x) ==b(y)){
            c(y+1)(x+1) =t+1
        }
        else{
          val t1=c(y+1)(x)
          val t2=c(y)(x+1)
          c(y+1)(x+1)=Array(t1,t2).max
        }
      }

    }

    c.map(_.toList).toList.foreach(println)
//    val r =c(nb-2)(nb-2)



  }


  def main(args: Array[String]): Unit = {

    lcs1("ABCDrddd","AMDrfddddddd")

  }




}
