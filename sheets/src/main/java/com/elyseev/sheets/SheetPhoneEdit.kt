package com.elyseev.sheets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.elyseev.sheets.util.addMaskPhone
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import com.elyseev.sheets.util.showed
import kotlinx.android.synthetic.main.sheet_edit.view.*
import kotlinx.android.synthetic.main.sheet_view.view.*

internal class SheetPhoneEdit @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var title: String? = ""
        set(value) {
            sheetTitle.showed(value?.isNotEmpty() != false)
            sheetTitle.text = value
            field = value
        }

    var currentText: String? = ""
        set(value) {
            sheetEdit.setText(value)
            sheetEdit.setSelection(value?.length ?: 0)
        }

    var hint: String? = ""
        set(value) {
            sheetEdit.hint = value
        }

    private var onClickedOkListener: (String) -> Unit = {}

    init {
        inflate(R.layout.sheet_view, true)
        group.addView(View.inflate(context, R.layout.sheet_edit, null))
        sheetEdit.addMaskPhone()
    }

    fun buttonOk(title: String = "OK", listener: (String) -> Unit) {
        onClickedOkListener = listener
        sheetOk.show()
        sheetOk.text = title
        sheetOk.setOnClickListener { onClickedOkListener.invoke(sheetEdit.text.toString()) }
    }
}
