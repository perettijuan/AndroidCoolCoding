package com.jpp.architecturecomponents.ui

/**
 * This class represents the events that the MainActivity routes from the user to the application,
 * meaning that each event the user triggers over any UI component shown by the MainActivity
 * will have a corresponding Intent
 */
sealed class MainActivityIntent {
    object LoadMoreItems : MainActivityIntent()
}