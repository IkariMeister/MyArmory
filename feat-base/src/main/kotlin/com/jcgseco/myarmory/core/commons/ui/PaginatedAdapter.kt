package com.jcgseco.myarmory.core.commons.ui

import androidx.viewbinding.ViewBinding
import com.jcgseco.myarmory.core.commons.domain.models.Page
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import com.xwray.groupie.viewbinding.BindableItem

abstract class PaginatedAdapter<DATA : Any> : GroupAdapter<GroupieViewHolder>() {

    protected abstract val countSection: Section?

    protected abstract val mainSection: Section
    private val errorSection = Section().apply {
        setHideWhenEmpty(true)
    }
    private val loadSection = Section().apply {
        setHideWhenEmpty(true)
    }

    abstract val errorItem: BindableItem<out ViewBinding>
    abstract val loadingItem: BindableItem<out ViewBinding>

    open fun buildSections() {
        add(mainSection)
        add(errorSection)
        add(loadSection)
    }

    fun updateState(state: PaginatedListState<DATA>) {
        when (state) {
            is PaginatedListState.Loading -> handleLoading()
            is PaginatedListState.Error -> handleErrorGettingMoreData()
            is PaginatedListState.Content<DATA> -> handleContent(state.page)
        }
    }

    abstract fun handleContent(page: Page<DATA>)

    protected abstract fun bindItemList(page: Page<DATA>): List<BindableItem<out ViewBinding>>

    private fun handleErrorGettingMoreData() {
        loadSection.clear()
        if (errorSection.itemCount == 0) {
            errorSection.add(errorItem)
        }
    }

    private fun handleLoading() {
        if ((loadSection.itemCount == 0)) {
            loadSection.add(loadingItem)
        }
    }
}

sealed class PaginatedListState<out T> {

    object Loading : PaginatedListState<Nothing>()
    object Error : PaginatedListState<Nothing>()
    class Content<T>(val page: Page<T>) : PaginatedListState<T>()
}
