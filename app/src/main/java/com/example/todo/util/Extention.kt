package com.example.todo.util

import android.app.AlertDialog
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.content.Context
import java.util.Calendar

fun Fragment.showToast (massage : Any?) {
    Toast.makeText(requireContext(), "$massage", Toast.LENGTH_LONG).show()
}

fun Calendar.clearTime() {
    this.set(Calendar.HOUR_OF_DAY, 0)
    this.set(Calendar.MINUTE, 0)
    this.set(Calendar.SECOND, 0)
    this.set(Calendar.MILLISECOND, 0)
}

fun Calendar.setDate(year: Int, month: Int, day: Int) {
    this.set(Calendar.YEAR, year)
    this.set(Calendar.MONTH, month)
    this.set(Calendar.DAY_OF_MONTH, day)
}

fun Fragment.showDeleteDialog(
    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null
) {
    AlertDialog.Builder(requireContext())
        .setTitle(TITLE)
        .setMessage(MESSAGE)
        .setPositiveButton(POSITIVE_BUTTON_TEXT) { dialog, _ ->
            onPositiveClick?.invoke()
            dialog.dismiss()
        }
        .apply {
            setNegativeButton(NEGATIVE_BUTTON_TEXT) { dialog, _ ->
                onNegativeClick?.invoke()
                dialog.dismiss()
            }
        }
        .create()
        .show()
}


fun Context.showDialog(
    title: String = "Title",
    message: String = "Message",
    positiveButtonText: String = "OK",
    negativeButtonText: String? = null,
    onPositiveClick: (() -> Unit)? = null,
    onNegativeClick: (() -> Unit)? = null
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { dialog, _ ->
            onPositiveClick?.invoke()
            dialog.dismiss()
        }
        .apply {
            negativeButtonText?.let {
                setNegativeButton(it) { dialog, _ ->
                    onNegativeClick?.invoke()
                    dialog.dismiss()
                }
            }
        }
        .create()
        .show()
}

