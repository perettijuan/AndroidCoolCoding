package com.jpp.mvikmm.android

import com.jpp.mvikmm.datasource.MessageDataSourceImpl
import com.jpp.mvikmm.model.MessageRepositoryImpl
import com.jpp.mvikmm.presentation.AndroidMviLifecycle
import com.jpp.mvikmm.presentation.PresenterImpl
import com.jpp.mvikmm.presentation.UserIntentImpl

object ExampleDi {

    private val dataSource = MessageDataSourceImpl()
    private val repository = MessageRepositoryImpl(dataSource)

    fun inject(fragment: MainFragment) {
        val lifecycle = AndroidMviLifecycle(fragment.viewLifecycleOwner.lifecycle)
        fragment.presenter = PresenterImpl(repository, lifecycle)
        fragment.userIntent = UserIntentImpl(repository, lifecycle)
    }
}