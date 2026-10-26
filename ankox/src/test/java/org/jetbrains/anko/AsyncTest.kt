package org.jetbrains.anko

import org.junit.Test
import java.util.concurrent.atomic.AtomicReference
import kotlin.test.assertEquals
import kotlin.test.assertSame

class AsyncTest {
    @Test
    fun doAsyncRunsTheTaskWithItsReceiver() {
        val receiver = Any()
        val seen = AtomicReference<Any>()
        receiver.doAsync { seen.set(weakRef.get()) }.get()
        assertSame(receiver, seen.get())
    }

    @Test
    fun exceptionGoesToTheHandler() {
        val caught = AtomicReference<Throwable>()
        val failure = RuntimeException("boom")
        "".doAsync({ caught.set(it) }) { throw failure }.get()
        assertSame(failure, caught.get())
    }

    @Test
    fun exceptionWithoutHandlerIsSwallowed() {
        assertEquals(Unit, "".doAsync(null) { throw RuntimeException("boom") }.get())
    }
}
