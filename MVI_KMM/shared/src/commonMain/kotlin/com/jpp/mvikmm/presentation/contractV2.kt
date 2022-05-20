package com.jpp.mvikmm.presentation

import com.badoo.reaktive.observable.ObservableWrapper
import com.jpp.mvikmm.model.MessageRepository

data class MviViewState(
    val progressVisible: Boolean 
)

data class SiteListView(
    val visible: Boolean,
    val contentList: List<SiteItem>
)

data class SiteItem(
    val name: String,
    val iconUrl: String
)

interface MviLifecycle2 {

}

interface MyPresenter {
    val viewState: ObservableWrapper<MviViewState>
}

class AndroidMviLifecycle2 : MviLifecycle2 {


}

abstract class MyPresenterImpl(
    private val repository: MessageRepository,
    private val lifecycle2: MviLifecycle2
) : MyPresenter {

    override val viewState: ObservableWrapper<MviViewState>
        get() = TODO("Not yet implemented")
}