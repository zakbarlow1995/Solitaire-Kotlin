package com.example.solitaire

//Or just declare them as global variables...
val clubs = "Clubs"
val diamonds = "Diamonds"
val hearts = "Hearts"
val spades = "Spades"
val redSuits = arrayOf(diamonds, hearts)
val blackSuits = arrayOf(clubs, spades)
var cardsMap = mapOf(0 to "A", 1 to "2", 2 to "3" , 3 to "4", 4 to "5", 5 to "6", 6 to "7", 7 to "8", 8 to "9", 9 to "10", 10 to "J", 11 to "Q", 12 to "K")

// data classes - equality operator check on constructor properties (not same object)
data class Card(val value: Int, val suit: String, var faceUp: Boolean = false) {
    // No static variables (static anything!) in Kotlin... neat! Use a companion object instead
//    companion object {
//        val clubs = "Clubs"
//        val diamonds = "Diamonds"
//        val hearts = "Hearts"
//        val spades = "Spades"
//    }

    override fun toString(): String =
        if(faceUp) "${cardsMap[value]}".padEnd(2) + "${getSuitChar(suit)}" else "xxx"

    private fun getSuitChar(suit: String): String = when (suit) {
        diamonds -> "\u2666"
        clubs -> "\u2663"
        hearts -> "\u2665"
        spades -> "\u2660"
        else -> "incorrect suit"
    }
}