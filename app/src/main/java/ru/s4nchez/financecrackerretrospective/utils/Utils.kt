package ru.s4nchez.financecrackerretrospective.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

fun dpToPx(context: Context, valueInDp: Float): Float {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics)
}

fun screenWidth(activity: Activity): Int {
    val displayMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}