/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:JvmName("Sdk15ViewsKt")
package org.jetbrains.anko

import org.jetbrains.anko.custom.*
import org.jetbrains.anko.AnkoViewDslMarker
import android.view.ViewManager
import android.app.Activity
import android.content.Context
import android.widget.*

@PublishedApi
internal object `$$Anko$Factories$Sdk15View` {
    val VIEW = { ctx: Context -> android.view.View(ctx) }
    val WEB_VIEW = { ctx: Context -> android.webkit.WebView(ctx) }
    val AUTO_COMPLETE_TEXT_VIEW = { ctx: Context -> android.widget.AutoCompleteTextView(ctx) }
    val BUTTON = { ctx: Context -> android.widget.Button(ctx) }
    val EDIT_TEXT = { ctx: Context -> android.widget.EditText(ctx) }
    val EXPANDABLE_LIST_VIEW = { ctx: Context -> android.widget.ExpandableListView(ctx) }
    val IMAGE_BUTTON = { ctx: Context -> android.widget.ImageButton(ctx) }
    val IMAGE_VIEW = { ctx: Context -> android.widget.ImageView(ctx) }
    val PROGRESS_BAR = { ctx: Context -> android.widget.ProgressBar(ctx) }
    val RADIO_BUTTON = { ctx: Context -> android.widget.RadioButton(ctx) }
    val SPINNER = { ctx: Context -> android.widget.Spinner(ctx) }
    val TEXT_VIEW = { ctx: Context -> android.widget.TextView(ctx) }
}

inline fun ViewManager.view(): android.view.View = view() {}

inline fun ViewManager.view(init: (@AnkoViewDslMarker android.view.View).() -> Unit): android.view.View {
    return ankoView(`$$Anko$Factories$Sdk15View`.VIEW, theme = 0) { init() }
}

inline fun ViewManager.webView(): android.webkit.WebView = webView() {}

inline fun ViewManager.webView(init: (@AnkoViewDslMarker android.webkit.WebView).() -> Unit): android.webkit.WebView {
    return ankoView(`$$Anko$Factories$Sdk15View`.WEB_VIEW, theme = 0) { init() }
}

inline fun Context.webView(): android.webkit.WebView = webView() {}

inline fun Context.webView(init: (@AnkoViewDslMarker android.webkit.WebView).() -> Unit): android.webkit.WebView {
    return ankoView(`$$Anko$Factories$Sdk15View`.WEB_VIEW, theme = 0) { init() }
}

inline fun Activity.webView(): android.webkit.WebView = webView() {}

inline fun Activity.webView(init: (@AnkoViewDslMarker android.webkit.WebView).() -> Unit): android.webkit.WebView {
    return ankoView(`$$Anko$Factories$Sdk15View`.WEB_VIEW, theme = 0) { init() }
}

inline fun ViewManager.autoCompleteTextView(): android.widget.AutoCompleteTextView = autoCompleteTextView() {}

inline fun ViewManager.autoCompleteTextView(init: (@AnkoViewDslMarker android.widget.AutoCompleteTextView).() -> Unit): android.widget.AutoCompleteTextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.AUTO_COMPLETE_TEXT_VIEW, theme = 0) { init() }
}

inline fun ViewManager.button(): android.widget.Button = button() {}

inline fun ViewManager.button(init: (@AnkoViewDslMarker android.widget.Button).() -> Unit): android.widget.Button {
    return ankoView(`$$Anko$Factories$Sdk15View`.BUTTON, theme = 0) { init() }
}

inline fun ViewManager.button(text: CharSequence?): android.widget.Button {
    return ankoView(`$$Anko$Factories$Sdk15View`.BUTTON, theme = 0) {
        setText(text)
    }
}

inline fun ViewManager.button(text: CharSequence?, init: (@AnkoViewDslMarker android.widget.Button).() -> Unit): android.widget.Button {
    return ankoView(`$$Anko$Factories$Sdk15View`.BUTTON, theme = 0) {
        init()
        setText(text)
    }
}

inline fun ViewManager.button(text: Int): android.widget.Button {
    return ankoView(`$$Anko$Factories$Sdk15View`.BUTTON, theme = 0) {
        setText(text)
    }
}

inline fun ViewManager.button(text: Int, init: (@AnkoViewDslMarker android.widget.Button).() -> Unit): android.widget.Button {
    return ankoView(`$$Anko$Factories$Sdk15View`.BUTTON, theme = 0) {
        init()
        setText(text)
    }
}

inline fun ViewManager.editText(): android.widget.EditText = editText() {}

inline fun ViewManager.editText(init: (@AnkoViewDslMarker android.widget.EditText).() -> Unit): android.widget.EditText {
    return ankoView(`$$Anko$Factories$Sdk15View`.EDIT_TEXT, theme = 0) { init() }
}

inline fun ViewManager.editText(text: CharSequence?): android.widget.EditText {
    return ankoView(`$$Anko$Factories$Sdk15View`.EDIT_TEXT, theme = 0) {
        setText(text)
    }
}

inline fun ViewManager.editText(text: CharSequence?, init: (@AnkoViewDslMarker android.widget.EditText).() -> Unit): android.widget.EditText {
    return ankoView(`$$Anko$Factories$Sdk15View`.EDIT_TEXT, theme = 0) {
        init()
        setText(text)
    }
}

inline fun ViewManager.editText(text: Int): android.widget.EditText {
    return ankoView(`$$Anko$Factories$Sdk15View`.EDIT_TEXT, theme = 0) {
        setText(text)
    }
}

inline fun ViewManager.editText(text: Int, init: (@AnkoViewDslMarker android.widget.EditText).() -> Unit): android.widget.EditText {
    return ankoView(`$$Anko$Factories$Sdk15View`.EDIT_TEXT, theme = 0) {
        init()
        setText(text)
    }
}

inline fun ViewManager.expandableListView(): android.widget.ExpandableListView = expandableListView() {}

inline fun ViewManager.expandableListView(init: (@AnkoViewDslMarker android.widget.ExpandableListView).() -> Unit): android.widget.ExpandableListView {
    return ankoView(`$$Anko$Factories$Sdk15View`.EXPANDABLE_LIST_VIEW, theme = 0) { init() }
}

inline fun Context.expandableListView(): android.widget.ExpandableListView = expandableListView() {}

inline fun Context.expandableListView(init: (@AnkoViewDslMarker android.widget.ExpandableListView).() -> Unit): android.widget.ExpandableListView {
    return ankoView(`$$Anko$Factories$Sdk15View`.EXPANDABLE_LIST_VIEW, theme = 0) { init() }
}

inline fun Activity.expandableListView(): android.widget.ExpandableListView = expandableListView() {}

inline fun Activity.expandableListView(init: (@AnkoViewDslMarker android.widget.ExpandableListView).() -> Unit): android.widget.ExpandableListView {
    return ankoView(`$$Anko$Factories$Sdk15View`.EXPANDABLE_LIST_VIEW, theme = 0) { init() }
}

inline fun ViewManager.imageButton(): android.widget.ImageButton = imageButton() {}

inline fun ViewManager.imageButton(init: (@AnkoViewDslMarker android.widget.ImageButton).() -> Unit): android.widget.ImageButton {
    return ankoView(`$$Anko$Factories$Sdk15View`.IMAGE_BUTTON, theme = 0) { init() }
}

inline fun ViewManager.imageButton(imageDrawable: android.graphics.drawable.Drawable?): android.widget.ImageButton {
    return ankoView(`$$Anko$Factories$Sdk15View`.IMAGE_BUTTON, theme = 0) {
        setImageDrawable(imageDrawable)
    }
}

inline fun ViewManager.imageButton(imageDrawable: android.graphics.drawable.Drawable?, init: (@AnkoViewDslMarker android.widget.ImageButton).() -> Unit): android.widget.ImageButton {
    return ankoView(`$$Anko$Factories$Sdk15View`.IMAGE_BUTTON, theme = 0) {
        init()
        setImageDrawable(imageDrawable)
    }
}

inline fun ViewManager.imageButton(imageResource: Int): android.widget.ImageButton {
    return ankoView(`$$Anko$Factories$Sdk15View`.IMAGE_BUTTON, theme = 0) {
        setImageResource(imageResource)
    }
}

inline fun ViewManager.imageButton(imageResource: Int, init: (@AnkoViewDslMarker android.widget.ImageButton).() -> Unit): android.widget.ImageButton {
    return ankoView(`$$Anko$Factories$Sdk15View`.IMAGE_BUTTON, theme = 0) {
        init()
        setImageResource(imageResource)
    }
}

inline fun ViewManager.imageView(): android.widget.ImageView = imageView() {}

inline fun ViewManager.imageView(init: (@AnkoViewDslMarker android.widget.ImageView).() -> Unit): android.widget.ImageView {
    return ankoView(`$$Anko$Factories$Sdk15View`.IMAGE_VIEW, theme = 0) { init() }
}

inline fun ViewManager.imageView(imageDrawable: android.graphics.drawable.Drawable?): android.widget.ImageView {
    return ankoView(`$$Anko$Factories$Sdk15View`.IMAGE_VIEW, theme = 0) {
        setImageDrawable(imageDrawable)
    }
}

inline fun ViewManager.imageView(imageDrawable: android.graphics.drawable.Drawable?, init: (@AnkoViewDslMarker android.widget.ImageView).() -> Unit): android.widget.ImageView {
    return ankoView(`$$Anko$Factories$Sdk15View`.IMAGE_VIEW, theme = 0) {
        init()
        setImageDrawable(imageDrawable)
    }
}

inline fun ViewManager.imageView(imageResource: Int): android.widget.ImageView {
    return ankoView(`$$Anko$Factories$Sdk15View`.IMAGE_VIEW, theme = 0) {
        setImageResource(imageResource)
    }
}

inline fun ViewManager.imageView(imageResource: Int, init: (@AnkoViewDslMarker android.widget.ImageView).() -> Unit): android.widget.ImageView {
    return ankoView(`$$Anko$Factories$Sdk15View`.IMAGE_VIEW, theme = 0) {
        init()
        setImageResource(imageResource)
    }
}

inline fun ViewManager.progressBar(): android.widget.ProgressBar = progressBar() {}

inline fun ViewManager.progressBar(init: (@AnkoViewDslMarker android.widget.ProgressBar).() -> Unit): android.widget.ProgressBar {
    return ankoView(`$$Anko$Factories$Sdk15View`.PROGRESS_BAR, theme = 0) { init() }
}

inline fun ViewManager.radioButton(): android.widget.RadioButton = radioButton() {}

inline fun ViewManager.radioButton(init: (@AnkoViewDslMarker android.widget.RadioButton).() -> Unit): android.widget.RadioButton {
    return ankoView(`$$Anko$Factories$Sdk15View`.RADIO_BUTTON, theme = 0) { init() }
}

inline fun ViewManager.spinner(): android.widget.Spinner = spinner() {}

inline fun ViewManager.spinner(init: (@AnkoViewDslMarker android.widget.Spinner).() -> Unit): android.widget.Spinner {
    return ankoView(`$$Anko$Factories$Sdk15View`.SPINNER, theme = 0) { init() }
}

inline fun Context.spinner(): android.widget.Spinner = spinner() {}

inline fun Context.spinner(init: (@AnkoViewDslMarker android.widget.Spinner).() -> Unit): android.widget.Spinner {
    return ankoView(`$$Anko$Factories$Sdk15View`.SPINNER, theme = 0) { init() }
}

inline fun Activity.spinner(): android.widget.Spinner = spinner() {}

inline fun Activity.spinner(init: (@AnkoViewDslMarker android.widget.Spinner).() -> Unit): android.widget.Spinner {
    return ankoView(`$$Anko$Factories$Sdk15View`.SPINNER, theme = 0) { init() }
}

inline fun ViewManager.textView(): android.widget.TextView = textView() {}

inline fun ViewManager.textView(init: (@AnkoViewDslMarker android.widget.TextView).() -> Unit): android.widget.TextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.TEXT_VIEW, theme = 0) { init() }
}

inline fun ViewManager.themedTextView(theme: Int = 0): android.widget.TextView = themedTextView(theme) {}

inline fun ViewManager.themedTextView(theme: Int = 0, init: (@AnkoViewDslMarker android.widget.TextView).() -> Unit): android.widget.TextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.TEXT_VIEW, theme) { init() }
}

inline fun ViewManager.textView(text: CharSequence?): android.widget.TextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.TEXT_VIEW, theme = 0) {
        setText(text)
    }
}

inline fun ViewManager.textView(text: CharSequence?, init: (@AnkoViewDslMarker android.widget.TextView).() -> Unit): android.widget.TextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.TEXT_VIEW, theme = 0) {
        init()
        setText(text)
    }
}

inline fun ViewManager.themedTextView(text: CharSequence?, theme: Int): android.widget.TextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.TEXT_VIEW, theme) {
        setText(text)
    }
}

inline fun ViewManager.themedTextView(text: CharSequence?, theme: Int, init: (@AnkoViewDslMarker android.widget.TextView).() -> Unit): android.widget.TextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.TEXT_VIEW, theme) {
        init()
        setText(text)
    }
}

inline fun ViewManager.textView(text: Int): android.widget.TextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.TEXT_VIEW, theme = 0) {
        setText(text)
    }
}

inline fun ViewManager.textView(text: Int, init: (@AnkoViewDslMarker android.widget.TextView).() -> Unit): android.widget.TextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.TEXT_VIEW, theme = 0) {
        init()
        setText(text)
    }
}

inline fun ViewManager.themedTextView(text: Int, theme: Int): android.widget.TextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.TEXT_VIEW, theme) {
        setText(text)
    }
}

inline fun ViewManager.themedTextView(text: Int, theme: Int, init: (@AnkoViewDslMarker android.widget.TextView).() -> Unit): android.widget.TextView {
    return ankoView(`$$Anko$Factories$Sdk15View`.TEXT_VIEW, theme) {
        init()
        setText(text)
    }
}

@PublishedApi
internal object `$$Anko$Factories$Sdk15ViewGroup` {
    val FRAME_LAYOUT = { ctx: Context -> _FrameLayout(ctx) }
    val IMAGE_SWITCHER = { ctx: Context -> _ImageSwitcher(ctx) }
    val SCROLL_VIEW = { ctx: Context -> _ScrollView(ctx) }
    val TABLE_LAYOUT = { ctx: Context -> _TableLayout(ctx) }
    val TABLE_ROW = { ctx: Context -> _TableRow(ctx) }
}

inline fun ViewManager.frameLayout(): android.widget.FrameLayout = frameLayout() {}

inline fun ViewManager.frameLayout(init: (@AnkoViewDslMarker _FrameLayout).() -> Unit): android.widget.FrameLayout {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.FRAME_LAYOUT, theme = 0) { init() }
}

inline fun Context.frameLayout(): android.widget.FrameLayout = frameLayout() {}

inline fun Context.frameLayout(init: (@AnkoViewDslMarker _FrameLayout).() -> Unit): android.widget.FrameLayout {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.FRAME_LAYOUT, theme = 0) { init() }
}

inline fun Activity.frameLayout(): android.widget.FrameLayout = frameLayout() {}

inline fun Activity.frameLayout(init: (@AnkoViewDslMarker _FrameLayout).() -> Unit): android.widget.FrameLayout {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.FRAME_LAYOUT, theme = 0) { init() }
}

inline fun ViewManager.imageSwitcher(): android.widget.ImageSwitcher = imageSwitcher() {}

inline fun ViewManager.imageSwitcher(init: (@AnkoViewDslMarker _ImageSwitcher).() -> Unit): android.widget.ImageSwitcher {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.IMAGE_SWITCHER, theme = 0) { init() }
}

inline fun Context.imageSwitcher(): android.widget.ImageSwitcher = imageSwitcher() {}

inline fun Context.imageSwitcher(init: (@AnkoViewDslMarker _ImageSwitcher).() -> Unit): android.widget.ImageSwitcher {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.IMAGE_SWITCHER, theme = 0) { init() }
}

inline fun Activity.imageSwitcher(): android.widget.ImageSwitcher = imageSwitcher() {}

inline fun Activity.imageSwitcher(init: (@AnkoViewDslMarker _ImageSwitcher).() -> Unit): android.widget.ImageSwitcher {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.IMAGE_SWITCHER, theme = 0) { init() }
}

inline fun ViewManager.scrollView(): android.widget.ScrollView = scrollView() {}

inline fun ViewManager.scrollView(init: (@AnkoViewDslMarker _ScrollView).() -> Unit): android.widget.ScrollView {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.SCROLL_VIEW, theme = 0) { init() }
}

inline fun Context.scrollView(): android.widget.ScrollView = scrollView() {}

inline fun Context.scrollView(init: (@AnkoViewDslMarker _ScrollView).() -> Unit): android.widget.ScrollView {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.SCROLL_VIEW, theme = 0) { init() }
}

inline fun Activity.scrollView(): android.widget.ScrollView = scrollView() {}

inline fun Activity.scrollView(init: (@AnkoViewDslMarker _ScrollView).() -> Unit): android.widget.ScrollView {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.SCROLL_VIEW, theme = 0) { init() }
}

inline fun ViewManager.tableLayout(): android.widget.TableLayout = tableLayout() {}

inline fun ViewManager.tableLayout(init: (@AnkoViewDslMarker _TableLayout).() -> Unit): android.widget.TableLayout {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.TABLE_LAYOUT, theme = 0) { init() }
}

inline fun Context.tableLayout(): android.widget.TableLayout = tableLayout() {}

inline fun Context.tableLayout(init: (@AnkoViewDslMarker _TableLayout).() -> Unit): android.widget.TableLayout {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.TABLE_LAYOUT, theme = 0) { init() }
}

inline fun Activity.tableLayout(): android.widget.TableLayout = tableLayout() {}

inline fun Activity.tableLayout(init: (@AnkoViewDslMarker _TableLayout).() -> Unit): android.widget.TableLayout {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.TABLE_LAYOUT, theme = 0) { init() }
}

inline fun ViewManager.tableRow(): android.widget.TableRow = tableRow() {}

inline fun ViewManager.tableRow(init: (@AnkoViewDslMarker _TableRow).() -> Unit): android.widget.TableRow {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.TABLE_ROW, theme = 0) { init() }
}

inline fun Context.tableRow(): android.widget.TableRow = tableRow() {}

inline fun Context.tableRow(init: (@AnkoViewDslMarker _TableRow).() -> Unit): android.widget.TableRow {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.TABLE_ROW, theme = 0) { init() }
}

inline fun Activity.tableRow(): android.widget.TableRow = tableRow() {}

inline fun Activity.tableRow(init: (@AnkoViewDslMarker _TableRow).() -> Unit): android.widget.TableRow {
    return ankoView(`$$Anko$Factories$Sdk15ViewGroup`.TABLE_ROW, theme = 0) { init() }
}

