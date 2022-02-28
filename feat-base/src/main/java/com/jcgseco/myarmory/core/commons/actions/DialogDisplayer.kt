package com.jcgseco.myarmory.core.commons.actions

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import javax.inject.Inject

class DialogDisplayer @Inject constructor(
    private val appCompatActivity: AppCompatActivity
) {

    private var alertDialog: AlertDialog? = null

    fun show(dialogConfiguration: DialogConfiguration) {
        alertDialog = configureView(dialogConfiguration)
        alertDialog?.show()
    }

    fun onDestroy() {
        alertDialog?.dismiss()
        alertDialog = null
    }

    private fun configureView(dialogConfiguration: DialogConfiguration): AlertDialog = when (dialogConfiguration) {
        else -> TODO("TBI")
    }

    private fun <T : ViewDataBinding> inflate(layout: Int) =
        DataBindingUtil.inflate<T>(appCompatActivity.layoutInflater, layout, null, false)
}
