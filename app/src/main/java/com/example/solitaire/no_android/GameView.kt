package com.example.solitaire

interface GameView {
    fun update(model: GameModel = GameModel)
    fun gameOver()
}