package com.jcgseco.myarmory.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.jcgseco.myarmory.R
import com.jcgseco.myarmory.core.commons.ui.ToolbarBaseFragment
import com.jcgseco.myarmory.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ToolbarBaseFragment<FragmentHomeBinding,HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()

    override fun onChildViewCreated(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun setUpObservers() {
        TODO("Not yet implemented")
    }
}
