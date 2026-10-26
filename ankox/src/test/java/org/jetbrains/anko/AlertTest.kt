package org.jetbrains.anko

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Looper
import android.widget.TextView
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowAlertDialog
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertSame
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class AlertTest {
    private val activity: Activity = Robolectric.buildActivity(Activity::class.java).setup().get()

    private fun AlertDialog.message(): String = shadowOf(this).message.toString()
    private fun AlertDialog.title(): String = shadowOf(this).title.toString()
    private fun AlertDialog.buttonText(which: Int): String = getButton(which).text.toString()

    /** Clicks [which]: the dialog delivers the click to its listener through the main looper. */
    private fun AlertDialog.click(which: Int) {
        getButton(which).performClick()
        shadowOf(Looper.getMainLooper()).idle()
    }

    @Test
    fun messageAndTitle() {
        val dialog = activity.alert("message", "title").show()
        assertTrue(dialog.isShowing)
        assertSame(ShadowAlertDialog.getLatestAlertDialog(), dialog)
        assertEquals("message", dialog.message())
        assertEquals("title", dialog.title())
    }

    @Test
    fun messageAndTitleResources() {
        val dialog = activity.alert(android.R.string.ok, android.R.string.cancel).show() as AlertDialog
        assertEquals(activity.getString(android.R.string.ok), dialog.message())
        assertEquals(activity.getString(android.R.string.cancel), dialog.title())
    }

    @Test
    fun builderProperties() {
        val dialog = activity.alert {
            title = "title"
            message = "message"
            isCancelable = false
        }.show() as AlertDialog
        assertEquals("title", dialog.title())
        assertEquals("message", dialog.message())
        assertFalse(shadowOf(dialog).isCancelable)
    }

    @Test
    fun buttons() {
        val clicked = mutableListOf<String>()
        val dialog = activity.alert("message") {
            positiveButton("yes") { clicked += "positive" }
            negativeButton(android.R.string.cancel) { clicked += "negative" }
            neutralPressed("later") { clicked += "neutral" }
        }.show()
        assertEquals("yes", dialog.buttonText(DialogInterface.BUTTON_POSITIVE))
        assertEquals(activity.getString(android.R.string.cancel), dialog.buttonText(DialogInterface.BUTTON_NEGATIVE))
        assertEquals("later", dialog.buttonText(DialogInterface.BUTTON_NEUTRAL))
        dialog.click(DialogInterface.BUTTON_POSITIVE)
        dialog.click(DialogInterface.BUTTON_NEGATIVE)
        dialog.click(DialogInterface.BUTTON_NEUTRAL)
        assertEquals(listOf("positive", "negative", "neutral"), clicked)
    }

    @Test
    fun cannedButtons() {
        var clicked = false
        val dialog = activity.alert("message") {
            okButton { clicked = true }
            cancelButton {}
        }.show()
        assertEquals(activity.getString(android.R.string.ok), dialog.buttonText(DialogInterface.BUTTON_POSITIVE))
        assertEquals(activity.getString(android.R.string.cancel), dialog.buttonText(DialogInterface.BUTTON_NEGATIVE))
        dialog.click(DialogInterface.BUTTON_POSITIVE)
        assertTrue(clicked)
    }

    @Test
    fun items() {
        var selected: Pair<String, Int>? = null
        val dialog = activity.alert {
            items(listOf("a", "b")) { _, item: String, index -> selected = item to index }
        }.show() as AlertDialog
        assertEquals(listOf("a", "b"), shadowOf(dialog).items.map { it.toString() })
        shadowOf(dialog).clickOnItem(1)
        assertEquals("b" to 1, selected)
    }

    @Test
    fun customView() {
        val dialog = activity.alert {
            customView { textView("custom") }
        }.show() as AlertDialog
        assertEquals("custom", (shadowOf(dialog).view as TextView).text.toString())
    }

    @Test
    fun onCancelled() {
        var cancelled = false
        val dialog = activity.alert("message") { onCancelled { cancelled = true } }.show()
        dialog.cancel()
        shadowOf(Looper.getMainLooper()).idle()
        assertTrue(cancelled)
    }

    @Test
    fun buildDoesNotShow() {
        assertFalse(activity.alert("message").build().isShowing)
    }

    @Test
    fun ankoContextAlert() {
        val dialog = activity.UI {}.alert("message", "title").show()
        assertEquals("message", dialog.message())
    }
}
