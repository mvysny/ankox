/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:JvmName("Sdk15ListenersListenersKt")
package org.jetbrains.anko.sdk15.listeners

inline fun android.view.View.onClick(noinline l: (v: android.view.View?) -> Unit) {
    setOnClickListener(l)
}

inline fun android.view.View.onFocusChange(noinline l: (v: android.view.View, hasFocus: Boolean) -> Unit) {
    setOnFocusChangeListener(l)
}

inline fun android.view.View.onLongClick(noinline l: (v: android.view.View?) -> Boolean) {
    setOnLongClickListener(l)
}

