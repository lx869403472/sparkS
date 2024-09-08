package sc

object MapSs {

  def main(args: Array[String]): Unit = {

    //List 不可变，类似 python tuple
    var list1=List("a","b","c","d","e")
    var list2=List("1","2","3","4","5","1","1")
    var lists =List(list1,list1,list2)



//    println(list1,list2,lists)
//
//    println(list1.head)
//
//    println(list2.tail.distinct.slice(0,3).sorted.reverse(0))
//
//    println(list1.zip(list2).toMap)

    //Array
    var array2 = Array("1","2","3","4")
    var array1 = Array("A","b","cx","d")
   var x= array1.:+("66767")
    array2.:+("222")

    println(x.toList)

    println(array1.zip(array2).toMap)


// Map

    var map1=Map("A"->"1")
    map1+=("b"->"2")
    val n = map1++(Map("B"->"c"))
//    map1+=("A"->"4")



  }

}
