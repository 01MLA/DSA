package com.newPackage


open class Person(val firstName: String, val lastName: String) {
    val fullName: String = "$firstName $lastName".uppercase()
}

class Ahmad : Person("Ahmad", "Ahmadi") {
//override val full
}

fun main() {
    val person = Person("Mohammad Latif", "Arfani")
    println(person.fullName)
}