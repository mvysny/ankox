package org.jetbrains.anko

import android.app.Activity
import android.app.Application
import android.content.ContextWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertSame

@RunWith(RobolectricTestRunner::class)
class AnkoContextTest {
    private val app: Application = RuntimeEnvironment.getApplication()

    private fun activity(): Activity = Robolectric.buildActivity(Activity::class.java).setup().get()

    private fun Activity.contentView(): View = findViewById<ViewGroup>(android.R.id.content).getChildAt(0)

    @Test
    fun uiHoldsItsOneView() {
        val ui = app.UI { textView("hello") }
        assertEquals("hello", (ui.view as TextView).text.toString())
        assertSame(app, ui.ctx)
        assertSame(app, ui.owner)
    }

    @Test
    fun viewBeforeAddingThrows() {
        assertFailsWith<IllegalStateException> { app.UI {}.view }
    }

    @Test
    fun secondViewThrows() {
        assertFailsWith<IllegalStateException> {
            app.UI {
                textView()
                textView()
            }
        }
    }

    @Test
    fun updateAndRemoveViewAreUnsupported() {
        val ui = app.UI { textView() }
        assertFailsWith<UnsupportedOperationException> { ui.removeView(ui.view) }
        assertFailsWith<UnsupportedOperationException> { ui.updateViewLayout(ui.view, ViewGroup.LayoutParams(1, 1)) }
    }

    @Test
    fun setContentViewOnActivity() {
        val activity = activity()
        val ui = activity.UI(setContentView = true) { textView("hello") }
        assertSame(ui.view, activity.contentView())
    }

    @Test
    fun setContentViewThroughContextWrapper() {
        val activity = activity()
        val ui = ContextWrapper(activity).UI(setContentView = true) { textView("hello") }
        assertSame(ui.view, activity.contentView())
    }

    @Test
    fun setContentViewWithoutActivityThrows() {
        assertFailsWith<IllegalStateException> { app.UI(setContentView = true) { textView() } }
    }

    @Test
    fun createWithOwner() {
        val owner = Any()
        val ui = AnkoContext.create(app, owner)
        ui.textView()
        assertSame(owner, ui.owner)
        assertSame(app, AnkoContext.create(app).owner)
    }

    @Test
    fun componentSetContentView() {
        val activity = activity()
        val component = object : AnkoComponent<Activity> {
            override fun createView(ui: AnkoContext<Activity>): View = with(ui) {
                verticalLayout { textView("hello") }
            }
        }
        val view = component.setContentView(activity)
        assertSame(view, activity.contentView())
    }

    @Test
    fun activityDslSetsContentView() {
        val activity = activity()
        val layout = activity.verticalLayout { textView("hello") }
        assertSame(layout, activity.contentView())
    }
}
