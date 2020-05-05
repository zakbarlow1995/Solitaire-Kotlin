package com.example.solitaire

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import org.jetbrains.anko.*

val cardBackDrawable = R.drawable.cardback_red4
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

    private val undo = "Undo"
    private val startOver = "Start Over"

    var deckView: DeckView? = null
    var wastePileView: WastePileView? = null
    val foundationPileViews: Array<FoundationPileView?> = arrayOfNulls(4)
    val tableauPileViews: Array<TableauPileView?> = arrayOfNulls(7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the game view
        GamePresenter.setGameView(this)

        //GameModel.resetGame()
        GameModel.resetGameToVictoryState()

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

    override fun update(model: GameModel) {
        deckView!!.update()
        wastePileView!!.update()
        foundationPileViews.forEach { it!!.update() }
        tableauPileViews.forEach { it!!.update() }
    }

    override fun gameOver() {
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("Congratulations!")
            setMessage("You have won the game!")
            setPositiveButton("New Game") { _, _ ->
                GameModel.resetGame()
                update()
            }
            show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(undo)
        menu.add(startOver)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title == undo) {
            Toast.makeText(applicationContext,
                "Undo Pressed", Toast.LENGTH_SHORT).show()
        } else {
            GameModel.resetGame()
            update()
        }
        return true
    }
}
