package org.jetbrains.anko.cardview.v7

import android.app.Application
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.cardview.widget.CardView
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertSame

@RunWith(RobolectricTestRunner::class)
class CardViewTest {
    private val app: Application = RuntimeEnvironment.getApplication()

    @Test
    fun cardViewIsAddedToItsParent() {
        lateinit var cardView: CardView
        val layout = app.verticalLayout {
            cardView = cardView { radius = 4f }
        }
        assertSame(cardView, layout.getChildAt(0))
        assertIs<_CardView>(cardView)
        assertEquals(4f, cardView.radius)
    }

    @Test
    fun layoutParams() {
        lateinit var lp: ViewGroup.LayoutParams
        app.cardView {
            lp = textView().lparams(matchParent, matchParent, Gravity.CENTER).layoutParams
        }
        assertIs<FrameLayout.LayoutParams>(lp)
        assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, lp.width)
        assertEquals(Gravity.CENTER, lp.gravity)
    }

    @Test
    fun themedCardViewWrapsTheContext() {
        val cardView = app.themedCardView(android.R.style.Theme_Black)
        assertIs<ContextThemeWrapper>(cardView.context)
    }
}
