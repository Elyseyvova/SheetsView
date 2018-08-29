package com.elyseev.sheets

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.elyseev.sheets.util.SheetsView.Companion.BACKGROUND_COLOR
import com.elyseev.sheets.util.SheetsView.Companion.CANCEL_TEXT_COLOR
import com.elyseev.sheets.util.SheetsView.Companion.TITLE_COLOR
import com.elyseev.sheets.util.hide
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import kotlinx.android.synthetic.main.sheet_message.view.*
import kotlinx.android.synthetic.main.sheet_view.view.*

internal class SheetAlert @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var title: String = ""
        set(value) {
            if (value.isEmpty()) sheetTitle.hide() else sheetTitle.show()
            sheetTitle.text = value
            field = value
        }

    var message: String = ""
        set(value) {
            sheetText.text = value
        }

    var params: Bundle? = null
        set(value) {
            value?.apply {
                root.background.setColorFilter(getInt(BACKGROUND_COLOR, resources.getColor(R.color.background_color)), PorterDuff.Mode.SRC_IN)
                sheetTitle.setTextColor(getInt(TITLE_COLOR, resources.getColor(R.color.title_color)))
                sheetCancel.setTextColor(getInt(CANCEL_TEXT_COLOR, resources.getColor(R.color.cancel_text_color)))
            }
        }


    init {
        inflate(R.layout.sheet_view, true)
        group.addView(View.inflate(context, R.layout.sheet_message, null))
    }

    fun buttonOk(title: String = "Ok", listener: () -> Unit) {
        sheetOk.show()
        sheetOk.text = title
        sheetOk.setOnClickListener { listener.invoke() }
    }

    fun buttonCancel(title: String = "Cancel", listener: () -> Unit) {
        sheetCancel.show()
        sheetCancel.text = title
        sheetCancel.setOnClickListener { listener.invoke() }
    }
}
