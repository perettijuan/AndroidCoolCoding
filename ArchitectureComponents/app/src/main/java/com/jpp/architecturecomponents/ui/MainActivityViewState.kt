package com.jpp.architecturecomponents.ui

/**
 * Model reflects the State -> This means that this model represents the possible states that
 * the MainActivity (GUI) can show to the user.
 *
 * It is defined using a Kotlin sealed class (similar to a Java enum) in order to represent the
 * idea that the MainActivity can have one (and only one) model or state at a given moment.
 * This means, basically, that the Activity's UI can be in one of the following states:
 */
sealed class MainActivityModel(val loading: Boolean, val error: Boolean, val content: Boolean) {
    object Loading : MainActivityModel(true, false, false)
    object Error : MainActivityModel(false, true, false)
    /*
     * Question: is Items a good name?
     * My thinking: the state name should answer the question 'what is the view showing in this moment?'
     */
    data class Items(val items: List<UiItem>) : MainActivityModel(false, false, true)
}