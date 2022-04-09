package com.irfan.fourthchallenge.utils

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showMaterialAlertDialog(
    positiveButtonLable: String? = null,
    negativeButtonLable: String? = null,
    title: String,
    message: String? = null,
    actionOnPositiveButton: (() -> Unit)? = null,
    actionOnNegativeButton: (() -> Unit)? = null,
) {
    MaterialAlertDialogBuilder(this).apply {
        setTitle(title)
        setCancelable(false)
        setMessage(message)
        setPositiveButton(positiveButtonLable) { dialog, _ ->
            dialog.cancel()
            if (actionOnPositiveButton != null) {
                actionOnPositiveButton()
            }
        }
        setNegativeButton(negativeButtonLable) { dialog, _ ->
            dialog.cancel()
            if (actionOnNegativeButton != null) {
                actionOnNegativeButton()
            }
        }
    }.show()
}