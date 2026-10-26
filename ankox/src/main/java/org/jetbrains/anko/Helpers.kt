/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("unused")
package org.jetbrains.anko

open class AnkoException(message: String = "") : RuntimeException(message)

/**
 * Return the grayscale color with the zero opacity using the single color value.
 * E.g., 0xC0 will be translated to 0xC0C0C0.
 */
val Int.gray: Int
    get() = this or (this shl 8) or (this shl 16)

/**
 * Return the color with 0xFF opacity.
 * E.g., 0xabcdef will be translated to 0xFFabcdef.
 */
val Int.opaque: Int
    get() = this or 0xff000000.toInt()
