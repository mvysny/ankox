/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("unused")
package org.jetbrains.anko

import android.content.Context

inline val Context.layoutInflater: android.view.LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as android.view.LayoutInflater
