package com.example.solitaire

// Singleton
object GamePresenter {

    var view: GameView? = null

    fun setGameView(gameView: GameView) {
        view = gameView
    }

    fun onDeckTap() {
        GameModel.onDeckTap()
        view?.update()
    }

    fun onWasteTap() {
        GameModel.onWasteTapped()
        view?.update()
    }

    fun onFoundationTap(foundationIndex: Int) {
        GameModel.onFoundationTapped(foundationIndex)
        view?.update()
    }

    fun onTableauTap(tableauIndex: Int, cardIndex: Int) {
        GameModel.onTableauTapped(tableauIndex, cardIndex)
        view?.update()
    }
}