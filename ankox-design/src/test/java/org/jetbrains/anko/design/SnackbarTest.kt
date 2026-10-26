package org.jetbrains.anko.design

import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import com.google.android.material.R as MaterialR

@RunWith(RobolectricTestRunner::class)
class SnackbarTest {
    // Snackbar insists on an AppCompat theme, and on a parent to show in.
    private val ctx = ContextThemeWrapper(RuntimeEnvironment.getApplication(), MaterialR.style.Theme_MaterialComponents_Light)
    private val view = View(ctx).also { FrameLayout(ctx).addView(it) }

    private val Snackbar.text: String get() = view.findViewById<TextView>(MaterialR.id.snackbar_text).text.toString()
    private val Snackbar.action: Button get() = view.findViewById(MaterialR.id.snackbar_action)

    @Test
    fun snackbar() {
        val snackbar = view.snackbar("hello")
        assertEquals("hello", snackbar.text)
        assertEquals(Snackbar.LENGTH_SHORT, snackbar.duration)
        assertTrue(snackbar.isShownOrQueued)
    }

    @Test
    fun snackbarResource() {
        assertEquals(ctx.getString(android.R.string.ok), view.snackbar(android.R.string.ok).text)
    }

    @Test
    fun longSnackbar() {
        assertEquals(Snackbar.LENGTH_LONG, view.longSnackbar("hello").duration)
        assertEquals(Snackbar.LENGTH_LONG, view.longSnackbar(android.R.string.ok).duration)
    }

    @Test
    fun action() {
        var clicked = false
        val snackbar = view.snackbar("hello", "undo") { clicked = true }
        assertEquals("undo", snackbar.action.text.toString())
        snackbar.action.performClick()
        assertTrue(clicked)
    }

    @Test
    fun actionResource() {
        val snackbar = view.longSnackbar(android.R.string.ok, android.R.string.cancel) {}
        assertEquals(ctx.getString(android.R.string.cancel), snackbar.action.text.toString())
        assertEquals(Snackbar.LENGTH_LONG, snackbar.duration)
    }
}
