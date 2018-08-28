package com.elyseev.sheets.util

import android.content.Context
import android.os.Bundle
import com.elyseev.sheets.*
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.model.SheetSeekUnit

fun Context.showSheetAction(
    title: String,
    items: List<SheetItem>,
    params: Bundle? = null,
    cancelable: Boolean = true,
    listener: (Int) -> Unit
) {
    val dialog = SheetsDialog(this)

    val sheet = SheetAction(this)
    sheet.params = params
    sheet.title = title
    sheet.items = items
    sheet.onSelectedItem {
        listener.invoke(it)
        dialog.dismiss()
    }

    dialog.setCancelable(cancelable)
    dialog.setContentView(sheet)
    dialog.show()
}

fun Context.showSheetSingle(
    title: String,
    items: List<SheetItem>,
    cancelable: Boolean = true,
    listener: (Int) -> Unit)
{
    val dialog = SheetsDialog(this)

    val sheet = SheetSingle(this)
    sheet.title = title
    sheet.items = items
    sheet.onSelectedItem {
        listener.invoke(it)
        dialog.dismiss()
    }

    dialog.setCancelable(cancelable)
    dialog.setContentView(sheet)
    dialog.show()
}

fun Context.showSheetMultiple(
    title: String,
    titleOk: String,
    items: List<SheetItem>,
    listener: (List<SheetItem>) -> Unit
) {
    val dialog = SheetsDialog(this)

    val sheet = SheetMultiple(this)
    sheet.title = title
    sheet.items = items
    sheet.buttonOk(titleOk) {
        listener.invoke(items)
        dialog.dismiss()
    }

    dialog.setContentView(sheet)
    dialog.show()
}

fun Context.showSheetSeek(
    title: String,
    titleOk: String,
    seekUnit: SheetSeekUnit,
    listener: (Int) -> Unit
) {
    val dialog = SheetsDialog(this)

    val sheet = SheetSeekbar(this)
    sheet.title = title
    sheet.currentSeekUnit = seekUnit
    sheet.buttonOk(titleOk) {
        listener.invoke(it)
        dialog.dismiss()
    }

    dialog.setContentView(sheet)
    dialog.show()
}

fun Context.showSheetEdit(
    title: String?,
    titleOk: String,
    currentText: String?,
    hint: String?,
    listener: (String) -> Unit
) {
    val dialog = SheetsDialog(this)

    val sheet = SheetEdit(this)
    sheet.title = title
    sheet.hint = hint
    sheet.currentText = currentText
    sheet.buttonOk(titleOk) {
        listener.invoke(it)
        dialog.dismiss()
    }

    dialog.setContentView(sheet)
    dialog.show()
}


fun Context.showSheetAlert(
    title: String?,
    titleOk: String,
    message: String,
    params: Bundle? = null,
    listenerOk: () -> Unit
) {
    val dialog = SheetsDialog(this)

    val sheet = SheetAlert(this)
    sheet.title = title!!
    sheet.message = message
    sheet.params = params
    sheet.buttonOk(titleOk) {
        listenerOk.invoke()
        dialog.dismiss()
    }

    dialog.setContentView(sheet)
    dialog.show()
}


fun Context.showSheetDialog(
    title: String?,
    titleOk: String,
    titleCancel: String,
    message: String,
    params: Bundle? = null,
    listenerOk: () -> Unit,
    listenerCancel: () -> Unit
) {
    val dialog = SheetsDialog(this)

    val sheet = SheetAlert(this)
    sheet.title = title!!
    sheet.message = message
    sheet.params = params
    sheet.buttonOk(titleOk) {
        listenerOk.invoke()
        dialog.dismiss()
    }

    sheet.buttonCancel(titleCancel) {
        listenerCancel.invoke()
        dialog.dismiss()
    }

    dialog.setCancelable(false)
    dialog.setContentView(sheet)
    dialog.show()
}

