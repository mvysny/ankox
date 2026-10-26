package org.jetbrains.anko

import android.app.Application
import android.view.View
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

// xhdpi: density 2.
@RunWith(RobolectricTestRunner::class)
@Config(qualifiers = "xhdpi")
class DimensionsTest {
    private val ctx: Application = RuntimeEnvironment.getApplication()

    @Test
    fun dip() {
        assertEquals(20, ctx.dip(10))
        assertEquals(3, ctx.dip(1.5f))
        assertEquals(20, View(ctx).dip(10))
        assertEquals(20, ctx.UI {}.dip(10))
    }

    @Test
    fun sp() {
        assertEquals(20, ctx.sp(10))
        assertEquals(3, ctx.sp(1.5f))
        assertEquals(20, View(ctx).sp(10))
        assertEquals(20, ctx.UI {}.sp(10))
    }

    @Test
    fun displayMetricsAndConfiguration() {
        assertEquals(2f, ctx.displayMetrics.density)
        assertEquals(ctx.resources.configuration, ctx.configuration)
        assertEquals(2f, ctx.UI {}.displayMetrics.density)
        assertEquals(ctx.resources.configuration, ctx.UI {}.configuration)
    }
}
