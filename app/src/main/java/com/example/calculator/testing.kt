package com.example.calculator

// I use this file to test some functionalities without having to wait for the emulator
// fyi I have a potato PC

fun main(){
    var x = "1"
    var y = "2"
    var z = x + y

    var xx = x.toInt()
    var yy = y.toInt()
    println(z)
    println(xx + yy)

    var xf = "30.2"
    var yf = "0.3"
    var zf = xf.toFloat() + yf.toFloat()
    println(zf)

}