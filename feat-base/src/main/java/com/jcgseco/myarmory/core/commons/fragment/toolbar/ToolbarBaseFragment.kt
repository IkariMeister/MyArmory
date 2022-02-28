package com.jcgseco.myarmory.core.commons.fragment.toolbar

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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.jcgseco.myarmory.core.commons.BaseViewModel
import com.jcgseco.myarmory.core.commons.actions.Action
import com.jcgseco.myarmory.core.commons.actions.DialogDisplayer
import com.jcgseco.myarmory.core.commons.actions.MessageDisplayer
import com.jcgseco.myarmory.core.commons.databinding.FragmentBaseToolbarBinding
import com.jcgseco.myarmory.core.commons.navigation.Navigator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { setState(it) }
            }
        }
        setUpObservers()
    }

    protected fun observeViewModelEvents(viewmodel: BaseViewModel) =
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(viewmodel) {
                    action.collect { action ->
                        when (action) {
                            Action.DoNothing ->  { /*Do Nothing*/ }
                            is Action.ShowDialog -> dialogDisplayer.show(action.dialogConfiguration)
                            is Action.ShowMessage -> messageDisplayer.showMessage(requireActivity(), action.message)
                        }
                    }
                    navigateTo.collect { it.navigate(requireActivity()) }
                }
            }
        }


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

    private fun configureNetworkAdvice() {
        parentBinding.errorView.setConf(AdviceViewConfiguration.NETWORK_ERROR)
        parentBinding.errorView.setButtonOnClickListener { onRetryButtonClick() }
    }

    private fun configureUnexpectedError() {
        parentBinding.errorView.setConf(AdviceViewConfiguration.UNEXPECTED_ERROR)
    }

    open fun onRetryButtonClick() {}

    fun setState(state: ViewState) {
        parentBinding.loading.visibility = View.GONE
        parentBinding.errorView.visibility = View.GONE
        parentBinding.fragmentContainer.visibility = View.GONE

        when (state) {
            is ViewState.Loading -> parentBinding.loading.visibility = View.VISIBLE
            is ViewState.Content -> parentBinding.fragmentContainer.visibility = View.VISIBLE
            is ViewState.NetworkError -> {
                configureNetworkAdvice()
                parentBinding.errorView.visibility = View.VISIBLE
            }
            is ViewState.UnexpectedError -> {
                configureUnexpectedError()
                parentBinding.errorView.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        dialogDisplayer.onDestroy()
        super.onDestroy()
    }
}

sealed interface ViewState {
    interface Loading : ViewState
    interface Content : ViewState
    interface NetworkError :ViewState
    interface UnexpectedError : ViewState
}
