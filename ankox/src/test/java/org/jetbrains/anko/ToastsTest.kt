package org.jetbrains.anko

import android.app.Application
import android.widget.Toast
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.shadows.ShadowToast
import kotlin.test.assertEquals
import kotlin.test.assertSame

@RunWith(RobolectricTestRunner::class)
class ToastsTest {
    private val app: Application = RuntimeEnvironment.getApplication()

    @Test
    fun toast() {
        val toast = app.toast("hello")
        assertSame(ShadowToast.getLatestToast(), toast)
        assertEquals("hello", ShadowToast.getTextOfLatestToast())
        assertEquals(Toast.LENGTH_SHORT, toast.duration)
    }

    @Test
    fun toastResource() {
        app.toast(android.R.string.ok)
        assertEquals(app.getString(android.R.string.ok), ShadowToast.getTextOfLatestToast())
    }

    @Test
    fun longToast() {
        val toast = app.longToast("hello")
        assertEquals("hello", ShadowToast.getTextOfLatestToast())
        assertEquals(Toast.LENGTH_LONG, toast.duration)
        assertEquals(Toast.LENGTH_LONG, app.longToast(android.R.string.ok).duration)
    }

    @Test
    fun ankoContextToast() {
        app.UI {}.toast("hello")
        assertEquals("hello", ShadowToast.getTextOfLatestToast())
    }
}
