package org.jetbrains.anko

import android.app.Application
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ContextThemeWrapper
import android.webkit.WebView
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ExpandableListView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.sdk15.listeners.onFocusChange
import org.jetbrains.anko.sdk15.listeners.onLongClick
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertSame
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class ViewsTest {
    private val app: Application = RuntimeEnvironment.getApplication()

    private val ok: String get() = app.getString(android.R.string.ok)

    @Test
    fun everyViewIsAddedToItsParent() {
        val layout = app.verticalLayout {
            view()
            textView()
            button()
            editText()
            imageView()
            progressBar()
            webView()
        }
        val types = (0 until layout.childCount).map { layout.getChildAt(it).javaClass }
        assertEquals(listOf(android.view.View::class.java, TextView::class.java, Button::class.java,
            EditText::class.java, ImageView::class.java, ProgressBar::class.java, WebView::class.java), types)
    }

    @Test
    fun moreViewsAreAddedToTheirParent() {
        val layout = app.verticalLayout {
            imageButton()
            spinner()
            radioButton()
            autoCompleteTextView()
            expandableListView()
        }
        val types = (0 until layout.childCount).map { layout.getChildAt(it).javaClass }
        assertEquals(listOf(ImageButton::class.java, Spinner::class.java, RadioButton::class.java,
            AutoCompleteTextView::class.java, ExpandableListView::class.java), types)
    }

    @Test
    fun imageButtonDrawables() {
        val drawable = ColorDrawable(Color.RED)
        lateinit var buttons: List<ImageButton>
        app.verticalLayout {
            buttons = listOf(imageButton(drawable), imageButton(android.R.drawable.ic_delete) {})
        }
        assertSame(drawable, buttons[0].drawable)
        assertEquals(android.R.drawable.ic_delete, shadowOf(buttons[1].drawable).createdFromResId)
    }

    @Test
    fun themedTextViewWrapsTheContext() {
        lateinit var views: List<TextView>
        app.verticalLayout {
            views = listOf(
                themedTextView(android.R.style.Theme_Black) { text = "a" },
                themedTextView("b", android.R.style.Theme_Black),
            )
        }
        assertEquals(listOf("a", "b"), views.map { it.text.toString() })
        views.forEach { assertIs<ContextThemeWrapper>(it.context) }
    }

    @Test
    fun text() {
        lateinit var views: List<TextView>
        app.verticalLayout {
            views = listOf(
                textView("a"), textView(android.R.string.ok),
                button("a"), button(android.R.string.ok),
                editText("a"), editText(android.R.string.ok),
            )
        }
        assertEquals(listOf("a", ok, "a", ok, "a", ok), views.map { it.text.toString() })
    }

    @Test
    fun textIsSetAfterInit() {
        lateinit var views: List<TextView>
        app.verticalLayout {
            views = listOf(
                textView("a") { text = "overridden" },
                textView(android.R.string.ok) { text = "overridden" },
                button("a") { text = "overridden" },
                button(android.R.string.ok) { text = "overridden" },
                editText("a") { setText("overridden") },
                editText(android.R.string.ok) { setText("overridden") },
            )
        }
        assertEquals(listOf("a", ok, "a", ok, "a", ok), views.map { it.text.toString() })
    }

    @Test
    fun imageViewDrawables() {
        val drawable = ColorDrawable(Color.RED)
        lateinit var views: List<ImageView>
        app.verticalLayout {
            views = listOf(
                imageView(drawable), imageView(drawable) {},
                imageView(android.R.drawable.ic_delete), imageView(android.R.drawable.ic_delete) {},
            )
        }
        assertSame(drawable, views[0].drawable)
        assertSame(drawable, views[1].drawable)
        assertEquals(android.R.drawable.ic_delete, shadowOf(views[2].drawable).createdFromResId)
        assertEquals(android.R.drawable.ic_delete, shadowOf(views[3].drawable).createdFromResId)
    }

    @Test
    fun viewOnContext() {
        assertIs<WebView>(app.webView())
    }

    @Test
    fun properties() {
        val text = TextView(app)
        text.backgroundColor = Color.RED
        assertEquals(Color.RED, (text.background as ColorDrawable).color)
        text.backgroundResource = android.R.drawable.ic_delete
        assertEquals(android.R.drawable.ic_delete, shadowOf(text.background).createdFromResId)
        text.textColor = Color.BLUE
        assertEquals(Color.BLUE, text.currentTextColor)
        text.lines = 3
        assertEquals(3, text.minLines)
        assertEquals(3, text.maxLines)
        text.singleLine = true
        assertEquals(1, text.maxLines)
        text.textResource = android.R.string.ok
        assertEquals(ok, text.text.toString())
        val image = ImageView(app)
        image.imageResource = android.R.drawable.ic_delete
        assertEquals(android.R.drawable.ic_delete, shadowOf(image.drawable).createdFromResId)
    }

    @Test
    fun padding() {
        val view = android.view.View(app)
        view.padding = 1
        assertEquals(listOf(1, 1, 1, 1), view.paddings())
        view.horizontalPadding = 2
        assertEquals(listOf(2, 1, 2, 1), view.paddings())
        view.verticalPadding = 3
        assertEquals(listOf(2, 3, 2, 3), view.paddings())
        view.leftPadding = 4
        view.topPadding = 5
        view.rightPadding = 6
        view.bottomPadding = 7
        assertEquals(listOf(4, 5, 6, 7), view.paddings())
        assertEquals(listOf(4, 5, 6, 7), listOf(view.leftPadding, view.topPadding, view.rightPadding, view.bottomPadding))
    }

    private fun android.view.View.paddings() = listOf(paddingLeft, paddingTop, paddingRight, paddingBottom)

    @Test
    fun backgroundDrawable() {
        val view = android.view.View(app)
        val drawable = ColorDrawable(Color.RED)
        view.backgroundDrawable = drawable
        assertSame(drawable, view.backgroundDrawable)
    }

    @Test
    fun listeners() {
        val view = android.view.View(app)
        var clicked = false
        view.onClick { clicked = true }
        view.performClick()
        assertTrue(clicked)

        view.onLongClick { true }
        assertTrue(view.performLongClick())

        var focused: Boolean? = null
        view.onFocusChange { _, hasFocus -> focused = hasFocus }
        view.onFocusChangeListener.onFocusChange(view, false)
        assertFalse(focused!!)
    }
}
