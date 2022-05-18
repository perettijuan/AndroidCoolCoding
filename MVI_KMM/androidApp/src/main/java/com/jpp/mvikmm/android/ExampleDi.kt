package com.jpp.mvikmm.android

import com.jpp.mvikmm.datasource.MessageDataSourceImpl
import com.jpp.mvikmm.model.MessageRepositoryImpl
import com.jpp.mvikmm.presentation.Presenter
import com.jpp.mvikmm.presentation.PresenterImpl
import com.jpp.mvikmm.presentation.UserIntent
import com.jpp.mvikmm.presentation.UserIntentImpl

object ExampleDi {

    private val dataSource = MessageDataSourceImpl()
    private val repository = MessageRepositoryImpl(dataSource)

    val presenter: Presenter = PresenterImpl(repository)
    val userIntent: UserIntent = UserIntentImpl(repository)
}