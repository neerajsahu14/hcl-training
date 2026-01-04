package org.opp

import org.oop.interfaces.User

fun main(){
    val user : User = User("Opp", "")
    println("Hello, Opp!")
    try {
        println(10 / 0)
    }
    catch (e: ArithmeticException){
        println(e.message)
    }
}