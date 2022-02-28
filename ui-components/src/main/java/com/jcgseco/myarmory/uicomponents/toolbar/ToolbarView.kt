package com.ninetyninemarkets.app.ui.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ninetyninemarkets.app.core.framework.android.extensions.setSafeOnClickListener
import com.ninetyninemarkets.app.ui.R
import com.ninetyninemarkets.app.ui.databinding.ViewToolbarBinding

class ToolbarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ViewToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        attrs?.let { handleStyledAttrs(it) }
    }

    private fun handleStyledAttrs(attrs: AttributeSet) {
        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.ToolbarView)

        val toolbarTitle = styledAttrs.getString(R.styleable.ToolbarView_toolbar_title)
        toolbarTitle?.let { binding.toolbarTitleTextView.text = toolbarTitle }

        val rightIconDrawable = styledAttrs.getDrawable(R.styleable.ToolbarView_toolbar_icon_right)
        rightIconDrawable?.let { binding.rightToolbarButton.setImageDrawable(it) }
        val leftIconDrawable = styledAttrs.getDrawable(R.styleable.ToolbarView_toolbar_icon_left)
        leftIconDrawable?.let { binding.leftToolbarButton.setImageDrawable(leftIconDrawable) }

        styledAttrs.recycle()
    }

    fun showLogo() {
        binding.logo.visibility = View.VISIBLE
    }

    fun setTitle(@StringRes titleResourceId: Int) {
        binding.toolbarTitleTextView.setText(titleResourceId)
    }

    fun setTitle(title: String) {
        binding.toolbarTitleTextView.text = title
    }

    fun setLeftButtonIcon(@DrawableRes iconResourceId: Int) {
        binding.leftToolbarButton.visibility = VISIBLE
        binding.leftToolbarButton.setImageResource(iconResourceId)
    }

    fun setRightButtonIcon(@DrawableRes iconResourceId: Int) {
        binding.rightToolbarButton.visibility = VISIBLE
        binding.rightToolbarButton.setImageResource(iconResourceId)
    }

    fun setLeftOnClickButton(clickFunction: () -> Unit) {
        binding.leftToolbarButton.setSafeOnClickListener { clickFunction() }
    }

    fun setRightOnClickButton(clickFunction: () -> Unit) {
        binding.rightToolbarButton.setSafeOnClickListener { clickFunction() }
    }

    fun showRightButton() {
        binding.rightToolbarButton.visibility = VISIBLE
    }

    fun hideRightButton() {
        binding.rightToolbarButton.visibility = INVISIBLE
    }

    fun showLeftButton() {
        binding.leftToolbarButton.visibility = VISIBLE
    }

    fun hideLeftButton() {
        binding.leftToolbarButton.visibility = INVISIBLE
    }
}
