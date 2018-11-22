package com.jpp.architecturecomponents.domain

/**
 * This is the unique use case that the application has: simulates that retrieves a series of
 * DomainItems from an external resource (an API).
 * The work this use case executes is time consuming, with the main goal of simulating a server
 * access for it.
 *
 * This class knows nothing about threading
 */
class GetItemsUseCase {
    operator fun invoke(bottom: Int, top: Int): List<DomainItem> {
        Thread.sleep(2000)
        val listOfItems = mutableListOf<DomainItem>()
        for (i in bottom..top) {
            listOfItems.add(DomainItem("name", i))
        }
        return listOfItems
    }
}