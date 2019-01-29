package com.elyseev.sheets.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.redmadrobot.inputmask.MaskedTextChangedListener

/**
 * Created by Elyseev Vladimir on 10.04.18.
 */

// ===================================================================================================================================================
// ViewGroup
// ===================================================================================================================================================

fun ViewGroup.inflate(resource: Int, attachToRoot: Boolean = true): View? = LayoutInflater.from(context).inflate(resource, this, attachToRoot)

// ===================================================================================================================================================
// View
// ===================================================================================================================================================

fun View.enabled(isEnabled: Boolean) {
    this.isEnabled = isEnabled
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.show(isEnabled: Boolean = true) {
    if (isShown) return

    this.visibility = View.VISIBLE
    if (isEnabled) enable() else disable()
}

fun View.showed(isShowed: Boolean = true) {
    if (isShowed) show() else hide()
}

fun EditText.addMaskPhone() {
    addTextChangedListener(MaskedTextChangedListener("+{7} ([000]) [000]-[00]-[00]", true, this, null, null))
    setSelection(text.length)
}
