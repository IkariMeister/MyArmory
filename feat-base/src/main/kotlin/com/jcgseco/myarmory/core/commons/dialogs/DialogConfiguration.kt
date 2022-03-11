package com.jcgseco.myarmory.core.commons.dialogs

import androidx.annotation.StringRes

sealed class DialogConfiguration(
    @StringRes open val title: Int,
    @StringRes open val message: Int,
    @StringRes open val positiveButtonLabel: Int,
    @StringRes open val negativeButtonLabel: Int,
    open val onClick: () -> Unit
)
