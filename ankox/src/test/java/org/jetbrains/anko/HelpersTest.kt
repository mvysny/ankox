package org.jetbrains.anko

import org.jetbrains.anko.internals.AnkoInternals
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class HelpersTest {
    @Test
    fun gray() {
        assertEquals(0xC0C0C0, 0xC0.gray)
        assertEquals(0, 0.gray)
    }

    @Test
    fun opaque() {
        assertEquals(0xFFABCDEF.toInt(), 0xABCDEF.opaque)
        assertEquals(0xFFC0C0C0.toInt(), 0xC0.gray.opaque)
    }

    @Test
    fun noGetterThrows() {
        assertFailsWith<AnkoException> { AnkoInternals.noGetter() }
    }
}
