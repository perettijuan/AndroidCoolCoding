package com.jpp.architecturecomponents.ui

import com.jpp.architecturecomponents.domain.DomainItem

class MainUiMapper {
    operator fun invoke(domainItem: DomainItem): UiItem = UiItem(domainItem.name + domainItem.id)
}