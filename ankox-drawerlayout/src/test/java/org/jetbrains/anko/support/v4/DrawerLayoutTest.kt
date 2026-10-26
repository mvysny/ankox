package org.jetbrains.anko.support.v4

import android.app.Application
import android.view.Gravity
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import org.jetbrains.anko.custom.ankoView
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
class DrawerLayoutTest {
    private val app: Application = RuntimeEnvironment.getApplication()

    @Test
    fun drawerLayoutParams() {
        lateinit var drawer: _DrawerLayout
        lateinit var lp: ViewGroup.LayoutParams
        val layout = app.verticalLayout {
            drawer = ankoView(::_DrawerLayout, theme = 0) {
                lp = textView().lparams(matchParent, matchParent, Gravity.START).layoutParams
            }
        }
        assertSame(drawer, layout.getChildAt(0))
        assertIs<DrawerLayout.LayoutParams>(lp)
        assertEquals(ViewGroup.LayoutParams.MATCH_PARENT, lp.width)
        assertEquals(Gravity.START, lp.gravity)
    }

    @Test
    fun drawerLayoutParamsCopiedFromSource() {
        lateinit var lp: ViewGroup.LayoutParams
        app.verticalLayout {
            ankoView(::_DrawerLayout, theme = 0) {
                lp = textView().lparams(ViewGroup.LayoutParams(10, 20)).layoutParams
            }
        }
        assertIs<DrawerLayout.LayoutParams>(lp)
        assertEquals(10, lp.width)
        assertEquals(20, lp.height)
    }
}
