package com.example.solitaire

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.*

val cardBackDrawable = R.drawable.cardback_green5
val emptyPileDrawable = R.drawable.cardback_blue1

val Context.cardWidth: Int
        get() = (displayMetrics.widthPixels - dip(8)) / 7
val Context.cardHeight: Int
        get() = cardWidth * 190/140

fun View.getResIdForCard(card: Card): Int {
    val resourceName = "card${card.suit}${cardsMap[card.value]}".toLowerCase()
    return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
}

class MainActivity : AppCompatActivity(), GameView {

    var deckView: DeckView? = null
    var wastePileView: WastePileView? = null
    val foundationPileViews: Array<FoundationPileView?> = arrayOfNulls(4)
    val tableauPileViews: Array<TableauPileView?> = arrayOfNulls(7)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the game view
        GamePresenter.setGameView(this)

        GameModel.resetGame()

//        val cardWidth = (displayMetrics.widthPixels - dip(8)) / 7
//        val cardHeight = cardWidth * 190/140

        verticalLayout {
            leftPadding = dip(4)
            rightPadding = dip(4)
            topPadding = dip(8)

            linearLayout {
                deckView = deckView().lparams(cardWidth, cardHeight)
                wastePileView = wastePileView().lparams(cardWidth, cardHeight)
                view().lparams(cardWidth, 0)

                for (i in 0..3) {
                    foundationPileViews[i] = foundationPileView(i).lparams(cardWidth, cardHeight)
                }
            }
            linearLayout {
                for (i in 0..6) {
                    tableauPileViews[i] = tableauPileView(i).lparams(cardWidth, matchParent)
                }
            }.lparams(height = matchParent) {
                topMargin = cardHeight / 2
            }
        }
    }

//    override fun update(model: GameModel) {
    override fun update() {
        deckView!!.update()
        wastePileView!!.update()
        foundationPileViews.forEach { it!!.update() }
        tableauPileViews.forEach { it!!.update() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add("Start Over")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        GameModel.resetGame()
        update()
        return true
    }
}












//val textViewId = 11


//        print("hello")
//        var counter = 0
//
//        relativeLayout {
//            val counterTextView = textView {
//                id = textViewId
//                text = "0"
//                textSize = 24f
//            }
//            button {
//                onClick {
//                    counter++
//                    counterTextView.text = counter.toString()
//                }
//            }.lparams {
//                below(counterTextView)
//            }
//        }
