package com.jcgseco.myarmory.uicomponents.lists

import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.Group
import com.xwray.groupie.GroupDataObserver
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

abstract class GalleryGroup(private val adapter: RecyclerView.Adapter<*>) : Group {

    private var isEmpty: Boolean
    private var groupDataObserver: GroupDataObserver? = null
    private val carouselItem: BindableItem<*>

    private lateinit var adapterDataObserver: RecyclerView.AdapterDataObserver

    init {
        this.carouselItem = GalleryItem(adapter)
        isEmpty = adapter.itemCount == 0
        initDataObserver()
        adapter.registerAdapterDataObserver(adapterDataObserver)
    }

    private fun initDataObserver() {
        adapterDataObserver = object : RecyclerView.AdapterDataObserver() {

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                val empty = adapter.itemCount == 0
                if (empty && !isEmpty) {
                    isEmpty = empty
                    groupDataObserver?.onItemRemoved(carouselItem, 0)
                }
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                val empty = adapter.itemCount == 0
                if (isEmpty && !empty) {
                    isEmpty = empty
                    groupDataObserver?.onItemInserted(carouselItem, 0)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isEmpty) 0 else 1
    }

    override fun getItem(position: Int): Item<*> {
        return if (position == 0 && !isEmpty) {
            carouselItem
        } else {
            throw IndexOutOfBoundsException()
        }
    }

    override fun getPosition(item: Item<*>): Int {
        return if (item === carouselItem && !isEmpty) 0 else -1
    }

    override fun registerGroupDataObserver(groupDataObserver: GroupDataObserver) {
        this.groupDataObserver = groupDataObserver
    }

    override fun unregisterGroupDataObserver(groupDataObserver: GroupDataObserver) {
        this.groupDataObserver = null
    }
}
