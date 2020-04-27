package com.example.solitaire

import android.content.Context
import android.view.ViewManager
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

class TableauPileView(context: Context, var index: Int) : _RelativeLayout(context) {
    init {
        addViews()
    }

    fun update() {
        removeAllViews()
        addViews()
    }

    private fun addViews() {
        val cards = GameModel.tableauPiles[index].cards
        cards.forEachIndexed { i, card ->
            imageView {
                y = (i*dip(24)).toFloat()
                imageResource = if (card.faceUp) getResIdForCard(card) else cardBackDrawable
                onClick {
                    GamePresenter.onTableauTap(index, i)
                }
            }.lparams(context.cardWidth, context.cardHeight)
        }
    }
}


fun ViewManager.tableauPileView(index: Int, init: TableauPileView.() -> Unit = {}) =
    ankoView({ TableauPileView(it, index) }, 0, init)