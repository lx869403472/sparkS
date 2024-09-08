function study() {
var x= "100"
document.write("hello javascript "+ typeof x);
document.write("<br>");
document.write("<br>");
document.write("变量x 的长度 "+ x.length);
document.write("<br>");
document.write("转化为字符串 "+ x.toString())
document.write("<br>");
document.write("转化为字符串 "+ Number(x)**4)
}

function defc(params="111") {
    document.write("<br>");
    document.write("udf "+params)
    document.write("<br>");
    return params
}
var n=defc()

document.write("def return "+ n.toString())
document.write("<br>");

function pic() {
    document.write(" <img loading=\"lazy\" src=\"bug.png\" />")

}
