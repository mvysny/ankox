package org.jetbrains.anko

import android.app.Application
import android.content.Context
import android.content.res.Configuration
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
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

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
        assertNotNull(app.layoutInflater)
    }

    @Test
    fun childrenSequence() {
        lateinit var children: List<android.view.View>
        val layout = app.verticalLayout {
            children = listOf(textView(), button())
        }
        @Suppress("DEPRECATION")
        assertEquals(children, layout.childrenSequence().toList())
        @Suppress("DEPRECATION")
        assertEquals(emptyList(), children[0].childrenSequence().toList())
    }

    @Test
    fun configuration() {
        val config = Configuration()
        config.orientation = Configuration.ORIENTATION_LANDSCAPE
        assertTrue(config.landscape)
        config.orientation = Configuration.ORIENTATION_PORTRAIT
        assertFalse(config.landscape)
        @Suppress("DEPRECATION")
        assertSame<Context>(app, app.ctx)
    }
}
