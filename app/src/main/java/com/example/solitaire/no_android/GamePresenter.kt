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
        checkVictoryState()
    }

    fun onWasteTap() {
        GameModel.onWasteTapped()
        view?.update()
        checkVictoryState()
    }

    fun onFoundationTap(foundationIndex: Int) {
        GameModel.onFoundationTapped(foundationIndex)
        view?.update()
        checkVictoryState()
    }

    fun onTableauTap(tableauIndex: Int, cardIndex: Int) {
        GameModel.onTableauTapped(tableauIndex, cardIndex)
        view?.update()
        checkVictoryState()
    }

    private fun checkVictoryState() {
        if (GameModel.hasWon()) {
            view?.gameOver()
        }
    }
}