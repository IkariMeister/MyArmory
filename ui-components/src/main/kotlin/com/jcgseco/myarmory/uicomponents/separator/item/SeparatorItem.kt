package com.jcgseco.myarmory.uicomponents.separator.item

import android.view.View
import com.jcgseco.myarmory.uicomponents.R
import com.jcgseco.myarmory.uicomponents.databinding.ItemSeparatorBinding
import com.xwray.groupie.viewbinding.BindableItem

class SeparatorItem : BindableItem<ItemSeparatorBinding>() {

    override fun getLayout(): Int = R.layout.item_separator

    override fun initializeViewBinding(view: View): ItemSeparatorBinding = ItemSeparatorBinding.bind(view)

    override fun bind(viewBinding: ItemSeparatorBinding, position: Int) {}
}
