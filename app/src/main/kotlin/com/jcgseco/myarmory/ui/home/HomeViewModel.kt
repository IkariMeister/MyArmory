package com.jcgseco.myarmory.ui.home

import com.jcgseco.myarmory.core.commons.ViewModelDependencies
import com.jcgseco.myarmory.core.commons.ui.BaseViewModel
import com.jcgseco.myarmory.core.commons.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    viewModelDependencies: ViewModelDependencies
) : BaseViewModel(viewModelDependencies) {

    protected override val viewStateFlow = MutableStateFlow<ViewState>(Loading)
}

sealed class HomeViewState
object Loading : HomeViewState(), ViewState.Loading
