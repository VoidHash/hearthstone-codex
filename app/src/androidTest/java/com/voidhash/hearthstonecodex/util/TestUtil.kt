package com.voidhash.hearthstonecodex.util

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

object TestUtil {

    private val recyclerViewCount = AtomicInteger(0)

    fun registerIdlingResourceForRecyclerViews(viewContainer: Any, recyclerViewIds: List<Int>) {
        recyclerViewIds.forEach { recyclerViewId ->
            val recyclerViewIdlingResource = RecyclerViewIdlingResource(viewContainer, recyclerViewId)
            IdlingRegistry.getInstance().register(recyclerViewIdlingResource)
            recyclerViewCount.incrementAndGet()
        }
    }

    fun unregisterIdlingResourceForRecyclerViews(viewContainer: Any, recyclerViewIds: List<Int>) {
        recyclerViewIds.forEach { recyclerViewId ->
            val recyclerViewIdlingResource = RecyclerViewIdlingResource(viewContainer, recyclerViewId)
            IdlingRegistry.getInstance().unregister(recyclerViewIdlingResource)
            recyclerViewCount.decrementAndGet()
        }
    }

    fun waitUntilAllRecyclerViewsIdle() {
        while (recyclerViewCount.get() > 0) {
            // Aguarde até que todas as RecyclerViews tenham sido consideradas inativas
            Thread.sleep(100)
        }
    }

    private class RecyclerViewIdlingResource(
        private val viewContainer: Any,
        private val recyclerViewId: Int
    ) : IdlingResource {
        private var resourceCallback: IdlingResource.ResourceCallback? = null

        override fun getName(): String {
            return RecyclerViewIdlingResource::class.java.name + ": $recyclerViewId"
        }

        override fun isIdleNow(): Boolean {
            val recyclerView = findRecyclerViewById(viewContainer, recyclerViewId)

            val idle = recyclerView.adapter != null && recyclerView.adapter?.itemCount ?: 0 > 0

            if (idle && resourceCallback != null) {
                resourceCallback?.onTransitionToIdle()
            }

            return idle
        }

        override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
            this.resourceCallback = callback
        }

        private fun findRecyclerViewById(viewContainer: Any, recyclerViewId: Int): RecyclerView {
            return when (viewContainer) {
                is Activity -> viewContainer.findViewById(recyclerViewId)
                is Fragment -> viewContainer.requireView().findViewById(recyclerViewId)
                else -> throw IllegalArgumentException("Unsupported view container type: $viewContainer")
            }
        }
    }
}