package org.jetbrains.anko.support.v4

import android.app.Application
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.util.ReflectionHelpers
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class SwipeRefreshLayoutTest {
    private val app: Application = RuntimeEnvironment.getApplication()

    @Test
    fun onRefresh() {
        val layout = SwipeRefreshLayout(app)
        var refreshed = false
        layout.onRefresh { refreshed = true }
        // The listener only fires from a user's swipe, so call the one the layout holds.
        ReflectionHelpers.getField<SwipeRefreshLayout.OnRefreshListener>(layout, "mListener").onRefresh()
        assertTrue(refreshed)
    }
}
