package com.jcgseco.myarmory.uicomponents.lists

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jcgseco.myarmory.uicomponents.R
import com.jcgseco.myarmory.uicomponents.databinding.ItemGalleryBinding
import com.xwray.groupie.viewbinding.BindableItem
import com.xwray.groupie.viewbinding.GroupieViewHolder

class GalleryItem(
    private val adapter: RecyclerView.Adapter<*>,
    private val carouselDecoration: RecyclerView.ItemDecoration? = null
) : BindableItem<ItemGalleryBinding>() {

    override fun getLayout(): Int = R.layout.item_gallery

    override fun initializeViewBinding(view: View): ItemGalleryBinding =
        ItemGalleryBinding.bind(view)

    override fun createViewHolder(itemView: View): GroupieViewHolder<ItemGalleryBinding> {
        val viewHolder = super.createViewHolder(itemView)
        val recyclerView = viewHolder.binding.recyclerView
        carouselDecoration?.let { recyclerView.addItemDecoration(it) }
        return viewHolder
    }

    override fun bind(viewBinding: ItemGalleryBinding, position: Int) {
        viewBinding.recyclerView.adapter = adapter
    }
}
