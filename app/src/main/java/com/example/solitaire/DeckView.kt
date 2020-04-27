package com.example.solitaire

import android.content.Context
import android.view.ViewManager
import android.widget.ImageView
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.onClick

// Normally a game presenter object would be passed in to be used by onClick - but given that
// ours is a singleton, there's not much point doing this in this case.
class DeckView(context: Context) : ImageView(context) {
    init {
        imageResource = cardBackDrawable
        onClick {
            GamePresenter.onDeckTap()
        }
    }

    fun update() {
        val cards = GameModel.deck.cardsInDeck
        imageResource = if (cards.size > 0) cardBackDrawable else emptyPileDrawable
    }
}

//val DECKVIEW_FACTORY = { ctx: Context -> DeckView(ctx) }
//
//fun ViewManager.deckView(init: DeckView.() -> Unit = {}) =
//    ankoView(DECKVIEW_FACTORY, 0, init)

fun ViewManager.deckView(init: DeckView.() -> Unit = {}) =
    ankoView({ DeckView(it) }, 0, init)