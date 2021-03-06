package com.elyseev.sheets.util

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.elyseev.sheets.*
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.model.SheetSeekUnit
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Context.showSheetAction(
        title: String,
        titleCancel: String = "CANCEL",
        items: List<SheetItem>,
        params: Bundle? = null,
        isCancelable: Boolean = true,
        listenerSelectable: (Int) -> Unit
) {
    val dialog = SheetsDialog(this)

    val sheet = SheetAction(this)
    sheet.params = params
    sheet.title = title
    sheet.items = items
    sheet.onSelectedItem { listenerSelectable.invoke(it); dialog.dismiss() }
    sheet.buttonAction(titleCancel) { dialog.dismiss() }

    dialog.setCancelable(isCancelable)
    dialog.setContentView(sheet)
    dialog.show()
}

fun Context.showSheetSingle(
        title: String,
        titleCancel: String = "CANCEL",
        items: List<SheetItem>,
        checkColor: Int,
        isCancelable: Boolean = true,
        listenerSelectable: (Int) -> Unit
) {
    val dialog = SheetsDialog(this)

    val sheet = SheetSingle(this)
    sheet.color = checkColor
    sheet.title = title
    sheet.items = items
    sheet.onSelectedItem { listenerSelectable.invoke(it); dialog.dismiss() }
    sheet.buttonAction(titleCancel) { dialog.dismiss() }

    dialog.setCancelable(isCancelable)
    dialog.setContentView(sheet)
    dialog.show()
}

fun Context.showSheetMultiple(
        title: String,
        titleOk: String = "OK",
        items: List<SheetItem>,
        checkColor: Int,
        listenerSelectable: (List<SheetItem>) -> Unit
) {
    val dialog = SheetsDialog(this)

    val sheet = SheetMultiple(this)
    sheet.color = checkColor
    sheet.title = title
    sheet.items = items
    sheet.buttonOk(titleOk) { listenerSelectable.invoke(items); dialog.dismiss() }

    dialog.setContentView(sheet)
    dialog.show()
}

fun Context.showSheetSeek(
        title: String,
        titleOk: String,
        seekUnit: SheetSeekUnit,
        listenerSeekable: (Int) -> Unit
) {
    val dialog = SheetsDialog(this)

    val sheet = SheetSeekbar(this)
    sheet.title = title
    sheet.currentSeekUnit = seekUnit
    sheet.buttonOk(titleOk) {
        listenerSeekable.invoke(it)
        dialog.dismiss()
    }

    dialog.setContentView(sheet)
    dialog.show()
}

fun Context.showSheetEdit(
        title: String?,
        titleOk: String = "OK",
        mask: String?,
        currentText: String?,
        hint: String?,
        isAutoDismiss: Boolean = false,
        listenerOk: (String) -> Unit
): BottomSheetDialog {
    val dialog = SheetsDialog(this)
    dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


    val sheet = SheetEdit(this)
    sheet.title = title
    sheet.mask = mask
    sheet.hint = hint
    sheet.currentText = currentText
    sheet.buttonOk(titleOk) {
        listenerOk.invoke(it)
        if (isAutoDismiss) dialog.dismiss()
    }

    dialog.setContentView(sheet)
    dialog.show()

    return dialog
}

fun Context.showSheetPhoneEdit(
        title: String?,
        titleOk: String = "OK",
        currentText: String?,
        hint: String?,
        isAutoDismiss: Boolean = false,
        listenerOk: (String) -> Unit
): BottomSheetDialog {
    val dialog = SheetsDialog(this)

    val sheet = SheetPhoneEdit(this)
    sheet.title = title
    sheet.hint = hint
    sheet.currentText = currentText
    sheet.buttonOk(titleOk) {
        listenerOk.invoke(it)
        if (isAutoDismiss) dialog.dismiss()
    }

    dialog.setContentView(sheet)
    dialog.show()

    return dialog
}


fun Context.showSheetAlert(
        title: String?,
        titleOk: String = "OK",
        message: String,
        params: Bundle? = null,
        isCancelable: Boolean = false,
        listenerOk: () -> Unit = {}
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
    dialog.setCancelable(isCancelable)
    dialog.show()
}


fun Context.showSheetDialog(
        title: String?,
        titleOk: String = "OK",
        titleCancel: String = "CANCEL",
        message: String,
        params: Bundle? = null,
        isCancelable: Boolean = false,
        listenerOk: () -> Unit = {},
        listenerCancel: () -> Unit = {}
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

    dialog.setCancelable(isCancelable)
    dialog.setContentView(sheet)
    dialog.show()
}


fun Context.showSheetCustom(
        title: String?,
        titleOk: String = "OK",
        titleCancel: String = "",
        isCancelable: Boolean = false,
        isAutoDismiss: Boolean = false,
        view: View,
        listenerOk: () -> Unit = {},
        listenerCancel: () -> Unit = {}
): BottomSheetDialog {
    val dialog = SheetsDialog(this)

    val sheet = SheetCustomView(this)
    sheet.title = title!!
    sheet.setCustomView(view)
    sheet.buttonOk(titleOk) {
        listenerOk.invoke()
        if (isAutoDismiss) dialog.dismiss()
    }

    sheet.buttonCancel(titleCancel) {
        listenerCancel.invoke()
        if (isAutoDismiss) dialog.dismiss()
    }

    dialog.setCancelable(isCancelable)
    dialog.setContentView(sheet)
    dialog.show()

    return dialog
}
