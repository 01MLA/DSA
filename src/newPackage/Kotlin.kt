package com.newPackage

open class Person(val firstName: String, val lastName: String) {
    val fullName: String = "$firstName $lastName".uppercase()
}

fun main() {
    val person = Person("Mohammad Latif", "Arfani")
    println(person.fullName)
    val myMap = mutableMapOf(1 to 10, 2 to 20, 3 to 30, 4 to 40, 5 to 50)
    val values = myMap.keys
}