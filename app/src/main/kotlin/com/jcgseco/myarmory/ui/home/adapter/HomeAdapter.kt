package com.jcgseco.myarmory.ui.home.adapter

import com.jcgseco.myarmory.ui.home.HomeViewState
import com.jcgseco.myarmory.uicomponents.separator.item.SeparatorItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class HomeAdapter : GroupAdapter<GroupieViewHolder>() {

    init {
        spanCount = 2
    }

    private fun buildSections(state: HomeViewState) {
        clear()
        val separatorItem = SeparatorItem()

        val sectionScheme = arrayListOf<Group>().apply {

        }
        addAll(sectionScheme)
    }

    fun updateState(state: HomeViewState) {
        buildSections(state)
    }
}
