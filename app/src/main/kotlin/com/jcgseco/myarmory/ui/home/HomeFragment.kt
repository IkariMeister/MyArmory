package com.jcgseco.myarmory.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jcgseco.myarmory.R
import com.jcgseco.myarmory.core.commons.ui.ToolbarBaseFragment
import com.jcgseco.myarmory.databinding.FragmentHomeBinding
import com.jcgseco.myarmory.ui.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ToolbarBaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()

    private val listAdapter by lazy { HomeAdapter() }

    override fun onChildViewCreated(savedInstanceState: Bundle?) {
        configureList()
        initViewModel()
    }

    override fun setUpObservers() {
        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }
    }

    private fun initViewModel() {
        viewModel.init()
    }

    private fun configureList() {
        val groupLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.itemList.apply {
            adapter = listAdapter
            layoutManager = groupLayoutManager
            isNestedScrollingEnabled = false
            setHasFixedSize(false)
        }
    }
}
