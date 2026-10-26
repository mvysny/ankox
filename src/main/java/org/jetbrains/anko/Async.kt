/*
 * Derived from Anko 0.10.8 (https://github.com/Kotlin/anko), Copyright 2016 JetBrains s.r.o.,
 * licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
 * Trimmed to the declarations our apps use, and ported from the support library to AndroidX.
 */

@file:Suppress("unused")
package org.jetbrains.anko

import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class AnkoAsyncContext<T>(val weakRef: WeakReference<T>)

private val crashLogger = { throwable : Throwable -> throwable.printStackTrace() }

/**
 * Execute [task] asynchronously.
 *
 * @param exceptionHandler optional exception handler.
 *  If defined, any exceptions thrown inside [task] will be passed to it. If not, exceptions will be ignored.
 * @param task the code to execute asynchronously.
 */
fun <T> T.doAsync(
        exceptionHandler: ((Throwable) -> Unit)? = crashLogger,
        task: AnkoAsyncContext<T>.() -> Unit
): Future<Unit> {
    val context = AnkoAsyncContext(WeakReference(this))
    return BackgroundExecutor.submit {
        return@submit try {
            context.task()
        } catch (thr: Throwable) {
            val result = exceptionHandler?.invoke(thr)
            if (result != null) {
                result
            } else {
                Unit
            }
        }
    }
}

internal object BackgroundExecutor {
    var executor: ExecutorService =
        Executors.newScheduledThreadPool(2 * Runtime.getRuntime().availableProcessors())

    fun <T> submit(task: () -> T): Future<T> = executor.submit(task)
}
