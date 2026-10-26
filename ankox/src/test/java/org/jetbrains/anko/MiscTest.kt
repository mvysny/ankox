package org.jetbrains.anko

import android.app.Application
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.graphics.Typeface
import android.util.TypedValue
import android.view.ContextThemeWrapper
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
class MiscTest {
    private val app: Application = RuntimeEnvironment.getApplication()

    @Test
    fun buildSpannedSpansTheAppendedText() {
        val bold = StyleSpan(Typeface.BOLD)
        val underline = UnderlineSpan()
        val spanned = buildSpanned {
            append("plain ")
            append("styled", bold, underline)
        }
        assertEquals("plain styled", spanned.toString())
        for (span in listOf(bold, underline)) {
            assertEquals(6, spanned.getSpanStart(span))
            assertEquals(12, spanned.getSpanEnd(span))
            assertEquals(Spanned.SPAN_INCLUSIVE_EXCLUSIVE, spanned.getSpanFlags(span))
        }
    }

    @Test
    fun attrResolvesThemeAttribute() {
        val ctx = ContextThemeWrapper(app, android.R.style.Theme_Black)
        val value = ctx.attr(android.R.attr.windowNoTitle)
        assertEquals(TypedValue.TYPE_INT_BOOLEAN, value.type)
        assertEquals(value.data, android.view.View(ctx).attr(android.R.attr.windowNoTitle).data)
    }

    @Test
    fun attrThrowsOnUnresolved() {
        assertFailsWith<IllegalArgumentException> { app.attr(0x7fffffff) }
    }

    @Test
    fun services() {
        assertNotNull(app.activityManager)
        assertNotNull(app.notificationManager)
        assertNotNull(app.windowManager)
    }
}
