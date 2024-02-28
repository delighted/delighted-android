package com.delighted.sdk.core

import android.content.Context
import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Checkable
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

fun Context.dipFtoPxF(dipValue: Float): Float = (dipValue * resources.displayMetrics.density)
fun Context.dipToPx(dipValue: Float): Int = dipFtoPxF(dipValue).toInt()

fun List<Checkable>.checkThisUncheckOthers(thisIndex: Int) {
    forEachIndexed { index, checkable ->
        checkable.isChecked = thisIndex == index
    }
}

infix fun <T> MutableLiveData<T>.setIfDistinct(value: T) {
    if (this.value != value) this.value = value
}

fun EditText.showKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun EditText.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, InputMethodManager.SHOW_IMPLICIT)
}

fun View.expandHitTarget(dip: Float) {
    val pixels: Int = context.dipToPx(dip)
    val parent = parent as View
    parent.post {
        val r = Rect()
        getHitRect(r)
        r.top -= pixels
        r.left -= pixels
        r.right += pixels
        r.bottom += pixels
        parent.touchDelegate = TouchDelegate(r, this)
    }
}

fun CoroutineDispatcher.scope() = CoroutineScope(this)
