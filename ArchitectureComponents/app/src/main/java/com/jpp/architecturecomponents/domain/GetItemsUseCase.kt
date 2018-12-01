package com.jpp.architecturecomponents.domain

import android.os.Looper
import javax.inject.Inject

/**
 * This is the unique use case that the application has: simulates that retrieves a series of
 * DomainItems from an external resource (an API).
 * The work this use case executes is time consuming, with the main goal of simulating a server
 * access for it.
 *
 * This class knows nothing about threading
 */
class GetItemsUseCase @Inject constructor() {
    operator fun invoke(bottom: Int, top: Int): List<DomainItem> {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw RuntimeException("Running on UI thread")
        }

        Thread.sleep(2000)
        val listOfItems = mutableListOf<DomainItem>()
        for (i in bottom..top) {
            listOfItems.add(DomainItem("name", i))
        }
        return listOfItems
    }
}