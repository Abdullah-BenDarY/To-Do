package com.example.todo.util

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.todo.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Fragment.showToast (massage : Any?) {
    Toast.makeText(requireContext(), "$massage", Toast.LENGTH_LONG).show()
}

fun Fragment.hideBottomAppBarViews() {
    requireActivity().findViewById<FloatingActionButton>(R.id.fab_add_task).visibility = View.GONE
    requireActivity().findViewById<BottomAppBar>(R.id.bottomAppBar).visibility = View.GONE

    // set marginBottom = 0
    val constraintLayout = requireActivity().findViewById<ConstraintLayout>(R.id.constraint)
    val params = constraintLayout.layoutParams as ViewGroup.MarginLayoutParams
    params.bottomMargin = 0
    constraintLayout.layoutParams = params
}

fun Fragment.showBottomAppBarViews() {
    requireActivity().findViewById<FloatingActionButton>(R.id.fab_add_task).visibility =
        View.VISIBLE
    requireActivity().findViewById<BottomAppBar>(R.id.bottomAppBar).visibility = View.VISIBLE

    // set marginBottom = actionBarSize
    val typedValue = TypedValue()
    val theme = requireActivity().theme
    theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)
    val actionBarSize =
        TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)

    val constraintLayout = requireActivity().findViewById<ConstraintLayout>(R.id.constraint)
    val params = constraintLayout.layoutParams as ViewGroup.MarginLayoutParams
    params.bottomMargin = actionBarSize
    constraintLayout.layoutParams = params

}

fun Calendar.clearTime() {
    this.set(Calendar.HOUR_OF_DAY, 0)
    this.set(Calendar.MINUTE, 0)
    this.set(Calendar.SECOND, 0)
    this.set(Calendar.MILLISECOND, 0)
}

fun Calendar.clearDate() {
    this.set(Calendar.YEAR, 0)
    this.set(Calendar.MONTH, 0)
    this.set(Calendar.DAY_OF_MONTH, 0)
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

fun Long.formatTimeOnly(): String {
    val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return timeFormat.format(this)
}
fun Long.formatDateOnly(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(this)
}


 fun Fragment.showDateDialog(selectedDate: Calendar,
                             onDateSet: (year: Int, month : Int ,day: Int) -> Unit ) {
    val dialog = DatePickerDialog(
        requireContext(),
        { _, year, month, dayOfMonth ->
            selectedDate.set(Calendar.YEAR, year)
            selectedDate.set(Calendar.MONTH, month)
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            onDateSet(year , month , dayOfMonth)
        }, selectedDate.get(Calendar.YEAR),
        selectedDate.get(Calendar.MONTH),
        selectedDate.get(Calendar.DAY_OF_MONTH)
    )
    dialog.show()
}

fun Fragment.showTimePicker(
    calendar: Calendar,
    onTimeSet: (calendar : Calendar) -> Unit
) {
    TimePickerDialog(
        requireContext(), { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            onTimeSet(calendar)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false
    ).show()
}


