/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("unused")
package org.jetbrains.anko

import android.view.View
import android.view.ViewGroup
import java.util.ConcurrentModificationException
import java.util.NoSuchElementException

/**
 * Return the sequence of children of the received [View].
 * Note that the sequence is not thread-safe.
 *
 * @return the [Sequence] of children.
 */
@Deprecated(message = "Use the Android KTX version", replaceWith = ReplaceWith("children", "androidx.core.view.children"))
fun View.childrenSequence(): Sequence<View> = ViewChildrenSequence(this)

private class ViewChildrenSequence(private val view: View) : Sequence<View> {
    override fun iterator(): Iterator<View> {
        if (view !is ViewGroup) return emptyList<View>().iterator()
        return ViewIterator(view)
    }

    private class ViewIterator(private val view: ViewGroup) : Iterator<View> {
        private var index = 0
        private val count = view.childCount

        override fun next(): View {
            if (!hasNext()) throw NoSuchElementException()
            return view.getChildAt(index++)
        }

        override fun hasNext(): Boolean {
            checkCount()
            return index < count
        }

        private fun checkCount() {
            if (count != view.childCount) throw ConcurrentModificationException()
        }
    }
}
