package com.elyseev.sheets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.elyseev.sheets.adapter.SheetAdapter
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import com.elyseev.sheets.util.showed
import kotlinx.android.synthetic.main.sheet_list.view.*
import kotlinx.android.synthetic.main.sheet_view.view.*

internal class SheetMultiple @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var title: String = ""
        set(value) {
            field = value
//            sheetTitle.showed(value.isNotEmpty())
            sheetTitle.text = value
        }

    var items: List<SheetItem> = emptyList()
        set(value) {
            field = value

            sheetOk.showed(items.size > 5)

            SheetAdapter(SheetAdapter.SheetType.MULTIPLE).apply {
                list.adapter = this
                sheetItems = items
                onSelectedItem { selectedItem ->
                    items.first { it.id == selectedItem.id }.isSelected = selectedItem.isSelected
                }
            }
        }

    private var onClickOkListener: (List<Any>) -> Unit = {}

    init {
        inflate(R.layout.sheet_items, true)
    }

    fun buttonOk(title: String = "OK", handler: (List<Any>) -> Unit) {
        sheetOk.show()
        sheetOk.text = title

        onClickOkListener = handler

        sheetOk.setOnClickListener { onClickOkListener.invoke(items) }
    }
}
