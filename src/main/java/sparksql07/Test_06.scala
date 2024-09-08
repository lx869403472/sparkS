package sparksql07

object Test_06 {
  def main(args: Array[String]): Unit = {

    val s = "and to adopt it____ for the collected chronicles of the Forsyte family has indulged the Forsytean tenacity that is in all of us. The word Saga might be objected to on the ground that it connotes the heroic and that there is little heroism in these pages."
    val words = s.split(" ")
    val p = "[a-zA-Z0-9]+".r
    println(p.findAllIn("it1____").mkString(""))
    fo()

    //    println(words.map(x=>p.findAllIn(x).toArray).mkString(""))
  }

  def fo() = {
    println("fo")
  }
}
