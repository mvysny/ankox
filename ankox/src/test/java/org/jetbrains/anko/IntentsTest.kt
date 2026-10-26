package org.jetbrains.anko

import android.app.Activity
import android.os.Bundle
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class IntentsTest {
    class TargetActivity : Activity()

    private val activity: Activity = Robolectric.buildActivity(Activity::class.java).setup().get()

    @Test
    fun startActivityWithExtras() {
        val bundle = Bundle()
        activity.startActivity<TargetActivity>(
            "int" to 1,
            "long" to 2L,
            "string" to "s",
            "boolean" to true,
            "double" to 1.5,
            "char" to 'c',
            "bundle" to bundle,
            "null" to null,
            "strings" to arrayOf("a", "b"),
            "ints" to intArrayOf(1, 2),
        )
        val intent = shadowOf(activity).nextStartedActivity
        assertEquals(TargetActivity::class.java.name, intent.component!!.className)
        assertEquals(1, intent.getIntExtra("int", 0))
        assertEquals(2L, intent.getLongExtra("long", 0))
        assertEquals("s", intent.getStringExtra("string"))
        assertTrue(intent.getBooleanExtra("boolean", false))
        assertEquals(1.5, intent.getDoubleExtra("double", 0.0))
        assertEquals('c', intent.getCharExtra("char", ' '))
        assertTrue(intent.hasExtra("null"))
        @Suppress("DEPRECATION")
        assertNull(intent.getSerializableExtra("null"))
        assertContentEquals(arrayOf("a", "b"), intent.getStringArrayExtra("strings"))
        assertContentEquals(intArrayOf(1, 2), intent.getIntArrayExtra("ints"))
    }

    @Test
    fun startActivityWithoutExtras() {
        activity.UI {}.startActivity<TargetActivity>()
        val intent = shadowOf(activity).nextStartedActivity
        assertEquals(TargetActivity::class.java.name, intent.component!!.className)
        assertNull(intent.extras)
    }

    @Test
    fun unsupportedExtraThrows() {
        assertFailsWith<AnkoException> { activity.startActivity<TargetActivity>("x" to Any()) }
    }

    @Test
    fun anyArrayGoesAsSerializable() {
        // Every array is Serializable, a branch Anko checks before its arrays: no AnkoException.
        activity.startActivity<TargetActivity>("x" to arrayOf<Any>(1))
        val intent = shadowOf(activity).nextStartedActivity
        @Suppress("DEPRECATION")
        assertEquals(listOf<Any>(1), (intent.getSerializableExtra("x") as Array<*>).toList())
    }
}
