package org.jetbrains.anko

import android.app.Application
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotSame
import kotlin.test.assertSame

@RunWith(RobolectricTestRunner::class)
class LayoutsTest {
    private val app: Application = RuntimeEnvironment.getApplication()

    @Test
    fun verticalLayoutNestsItsChildren() {
        lateinit var inner: FrameLayout
        val layout = app.verticalLayout {
            textView("a")
            inner = frameLayout {
                button("b")
            }
        }
        assertEquals(LinearLayout.VERTICAL, layout.orientation)
        assertEquals(2, layout.childCount)
        assertSame(inner, layout.getChildAt(1))
        assertEquals(1, inner.childCount)
    }

    @Test
    fun linearLayoutParams() {
        lateinit var lp: LinearLayout.LayoutParams
        app.verticalLayout {
            lp = textView().lparams(matchParent, wrapContent, 1f) {
                gravity = Gravity.END
                margin = 3
            }.layoutParams as LinearLayout.LayoutParams
        }
        assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, lp.width)
        assertEquals(ViewGroup.LayoutParams.WRAP_CONTENT, lp.height)
        assertEquals(1f, lp.weight)
        assertEquals(Gravity.END, lp.gravity)
        assertEquals(listOf(3, 3, 3, 3), listOf(lp.leftMargin, lp.topMargin, lp.rightMargin, lp.bottomMargin))
    }

    @Test
    fun linearLayoutParamsDefaultToWrapContent() {
        lateinit var lp: LinearLayout.LayoutParams
        app.verticalLayout {
            lp = textView().lparams().layoutParams as LinearLayout.LayoutParams
        }
        assertEquals(ViewGroup.LayoutParams.WRAP_CONTENT, lp.width)
        assertEquals(ViewGroup.LayoutParams.WRAP_CONTENT, lp.height)
    }

    @Test
    fun frameLayoutParams() {
        lateinit var lp: FrameLayout.LayoutParams
        app.frameLayout {
            lp = textView().lparams(matchParent, matchParent, Gravity.CENTER) {
                horizontalMargin = 1
                verticalMargin = 2
            }.layoutParams as FrameLayout.LayoutParams
        }
        assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, lp.width)
        assertEquals(Gravity.CENTER, lp.gravity)
        assertEquals(listOf(1, 2, 1, 2), listOf(lp.leftMargin, lp.topMargin, lp.rightMargin, lp.bottomMargin))
    }

    @Test
    fun frameLayoutParamsCopiedFromSource() {
        lateinit var lp: ViewGroup.LayoutParams
        app.frameLayout {
            lp = textView().lparams(ViewGroup.LayoutParams(10, 20)).layoutParams
        }
        assertIs<FrameLayout.LayoutParams>(lp)
        assertEquals(10, lp.width)
        assertEquals(20, lp.height)
    }

    @Test
    fun scrollViewParams() {
        lateinit var lp: ViewGroup.LayoutParams
        val scrollView = app.scrollView {
            lp = verticalLayout().lparams(matchParent, wrapContent).layoutParams
        }
        assertIs<FrameLayout.LayoutParams>(lp)
        assertEquals(1, scrollView.childCount)
    }

    @Test
    fun imageSwitcherParams() {
        lateinit var lp: ViewGroup.LayoutParams
        val switcher = app.imageSwitcher {
            lp = imageView().lparams(matchParent, matchParent).layoutParams
        }
        assertIs<FrameLayout.LayoutParams>(lp)
        assertEquals(1, switcher.childCount)
    }

    @Test
    fun themedLayoutWrapsTheContext() {
        val theme = android.R.style.Theme_Black
        lateinit var child: android.view.View
        val layout = app.verticalLayout(theme) {
            child = verticalLayout(theme)
        }
        assertIs<ContextThemeWrapper>(layout.context)
        assertNotSame<Any>(app, layout.context)
        // An equal theme is not wrapped again.
        assertSame(layout.context, child.context)
    }

    @Test
    fun unthemedLayoutKeepsTheContext() {
        assertSame(app, app.verticalLayout().context)
    }
}
