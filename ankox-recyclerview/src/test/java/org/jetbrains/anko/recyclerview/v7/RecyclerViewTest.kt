package org.jetbrains.anko.recyclerview.v7

import android.app.Application
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertSame

@RunWith(RobolectricTestRunner::class)
class RecyclerViewTest {
    private val app: Application = RuntimeEnvironment.getApplication()

    @Test
    fun recyclerViewIsAddedToItsParent() {
        lateinit var recyclerView: RecyclerView
        val layout = app.verticalLayout {
            recyclerView = recyclerView()
        }
        assertSame(recyclerView, layout.getChildAt(0))
        assertIs<_RecyclerView>(recyclerView)
    }

    @Test
    fun layoutParams() {
        lateinit var lp: ViewGroup.LayoutParams
        app.recyclerView {
            layoutManager = LinearLayoutManager(context)
            lp = textView().lparams(matchParent, wrapContent) { leftMargin = 3 }.layoutParams
        }
        assertIs<RecyclerView.LayoutParams>(lp)
        assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, lp.width)
        assertEquals(ViewGroup.LayoutParams.WRAP_CONTENT, lp.height)
        assertEquals(3, lp.leftMargin)
    }

    @Test
    fun layoutParamsCopiedFromSource() {
        lateinit var lp: ViewGroup.LayoutParams
        app.recyclerView {
            layoutManager = LinearLayoutManager(context)
            lp = textView().lparams(ViewGroup.LayoutParams(10, 20)).layoutParams
        }
        assertIs<RecyclerView.LayoutParams>(lp)
        assertEquals(10, lp.width)
        assertEquals(20, lp.height)
    }
}
