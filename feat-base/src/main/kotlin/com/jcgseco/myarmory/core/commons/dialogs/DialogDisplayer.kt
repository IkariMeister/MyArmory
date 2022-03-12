package com.jcgseco.myarmory.core.commons.dialogs

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class DialogDisplayer @Inject constructor(
    @ActivityContext private val context: Context
) {

    private val appCompatActivity: AppCompatActivity = context as AppCompatActivity

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
        else -> with(dialogConfiguration) {
            MaterialAlertDialogBuilder(appCompatActivity)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(negativeButtonLabel) { dialog, _ -> dialog.cancel() }
                .setPositiveButton(positiveButtonLabel) { _, _ -> onClick() }
                .show()
        }
    }

    private fun <T : ViewDataBinding> inflate(layout: Int) =
        DataBindingUtil.inflate<T>(appCompatActivity.layoutInflater, layout, null, false)
}
