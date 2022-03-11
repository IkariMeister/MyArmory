package com.jcgseco.myarmory.core.commons.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jcgseco.myarmory.core.commons.dialogs.DialogDisplayer
import com.jcgseco.myarmory.core.commons.messages.MessageDisplayer
import com.jcgseco.myarmory.core.commons.navigation.Navigator
import javax.inject.Inject

abstract class ToolbarBaseFragment<T : ViewDataBinding, V : BaseViewModel>(
    private val layoutResourceId: Int
) : Fragment() {

    private lateinit var parentBinding: FragmentBaseToolbarBinding
    lateinit var binding: T
    abstract val viewModel: V

    @Inject
    internal lateinit var navigatorProvider: Navigator.Provider

    @Inject
    protected lateinit var messageDisplayer: MessageDisplayer

    @Inject
    protected lateinit var dialogDisplayer: DialogDisplayer

    private var showShadowAlways = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        parentBinding = FragmentBaseToolbarBinding.inflate(inflater, container, false)
        parentBinding.lifecycleOwner = this
        return parentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViewStub()
        configureToolbar()
        initViewModel()
        onChildViewCreated(savedInstanceState)
    }

    private fun initViewModel() {
        observeViewModelEvents(viewModel)
//        viewModel.viewState.observe(viewLifecycleOwner) { setState(it) }
        setUpObservers()
    }

    protected fun observeViewModelEvents(viewmodel: BaseViewModel) =
//        with(viewmodel) {
//            observe(action) {
//                it.getContentIfNotHandled()?.run {
//                    when (this) {
//                        is Action.ShowMessage -> messageDisplayer.showMessage(requireActivity(), this.message)
//                        is Action.ShowDialog -> dialogDisplayer.show(dialogConfiguration)
//                    }
//                }
//            }
//            observe(navigateTo) { it.getContentIfNotHandled()?.navigate(requireActivity()) }
//        }

    abstract fun setUpObservers()

    open fun configureToolbar() {}

    abstract fun onChildViewCreated(savedInstanceState: Bundle?)

    fun setLeftButton(@DrawableRes iconResourceId: Int, function: () -> Unit) {
        parentBinding.toolbar.setLeftButtonIcon(iconResourceId)
        parentBinding.toolbar.setLeftOnClickButton(function)
    }

    fun setRightButton(@DrawableRes iconResourceId: Int, function: () -> Unit) {
        parentBinding.toolbar.setRightButtonIcon(iconResourceId)
        parentBinding.toolbar.setRightOnClickButton(function)
    }

    fun showRightButton() {
        parentBinding.toolbar.showRightButton()
    }

    fun hideRightButton() {
        parentBinding.toolbar.hideRightButton()
    }

    fun showLeftButton() {
        parentBinding.toolbar.showLeftButton()
    }

    fun hideLeftButton() {
        parentBinding.toolbar.hideLeftButton()
    }

    fun showLogo() {
        parentBinding.toolbar.showLogo()
    }

    fun setTitle(title: String) {
        parentBinding.toolbar.setTitle(title)
    }

    fun setTitle(@StringRes titleResourceId: Int) {
        parentBinding.toolbar.setTitle(titleResourceId)
    }

    fun hideToolbar() {
        parentBinding.toolbar.visibility = View.GONE
    }

    fun showBlockerLoading() {
        parentBinding.blockerLoading.visibility = View.VISIBLE
    }

    fun hideBlockerLoading() {
        parentBinding.blockerLoading.visibility = View.GONE
    }

    private fun showShadow() {
        if (!showShadowAlways) parentBinding.divider.visibility = View.VISIBLE
    }

    private fun hideShadow() {
        if (!showShadowAlways) parentBinding.divider.visibility = View.GONE
    }

    fun setShowShadowAlways(showShadowAlways: Boolean) {
        this.showShadowAlways = showShadowAlways
        if (!showShadowAlways) hideShadow()
    }

    fun setScrollView(scrollView: View) {
        if (scrollView is RecyclerView) {
            scrollView.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (scrollView.computeVerticalScrollOffset() == 0) hideShadow() else showShadow()
                    }
                }
            )
        } else {
            scrollView.viewTreeObserver.addOnScrollChangedListener {
                if (scrollView.scrollY == 0) hideShadow() else showShadow()
            }
        }
    }

    fun setToolbarColor(@ColorRes colorResId: Int) {
        val color = ContextCompat.getColor(context as AppCompatActivity, colorResId)
        parentBinding.toolbar.setBackgroundColor(color)
    }

    private fun configureViewStub() {
        with(parentBinding.fragmentViewStub.viewStub!!) {
            layoutResource = layoutResourceId
            setOnInflateListener { _, inflated ->
                binding = DataBindingUtil.bind(inflated) ?: throw NullPointerException()
                binding.lifecycleOwner = this@ToolbarBaseFragment
            }
            inflate()
        }
    }

    open fun onRetryButtonClick() {}

    fun setState(state: ViewState) {
        parentBinding.loading.visibility = View.GONE
        parentBinding.errorView.visibility = View.GONE
        parentBinding.fragmentContainer.visibility = View.GONE

//        when (state) {
//            ViewState.LOADING -> parentBinding.loading.visibility = View.VISIBLE
//            ViewState.CONTENT -> parentBinding.fragmentContainer.visibility = View.VISIBLE
//            ViewState.NETWORK_ERROR -> {
//                configureNetworkAdvice()
//                parentBinding.errorView.visibility = View.VISIBLE
//            }
//            ViewState.UNEXPECTED_ERROR -> {
//                configureUnexpectedError()
//                parentBinding.errorView.visibility = View.VISIBLE
//            }
//        }
//    }

    override fun onDestroy() {
        dialogDisplayer.onDestroy()
        super.onDestroy()
    }
}
