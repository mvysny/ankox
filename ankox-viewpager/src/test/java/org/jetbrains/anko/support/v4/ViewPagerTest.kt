package org.jetbrains.anko.support.v4

import android.app.Application
import android.view.Gravity
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
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
class ViewPagerTest {
    private val app: Application = RuntimeEnvironment.getApplication()

    @Test
    fun viewPagerIsAddedToItsParent() {
        lateinit var viewPager: ViewPager
        val layout = app.verticalLayout {
            viewPager = viewPager()
        }
        assertSame(viewPager, layout.getChildAt(0))
        assertIs<_ViewPager>(viewPager)
    }

    @Test
    fun layoutParams() {
        lateinit var lp: ViewGroup.LayoutParams
        app.viewPager {
            lp = textView().lparams { gravity = Gravity.TOP }.layoutParams
        }
        assertIs<ViewPager.LayoutParams>(lp)
        assertEquals(Gravity.TOP, lp.gravity)
    }
}
