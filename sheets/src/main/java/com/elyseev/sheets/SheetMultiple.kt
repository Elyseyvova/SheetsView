package com.elyseev.sheets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elyseev.sheets.adapter.SheetAdapter
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import com.elyseev.sheets.util.showed
import kotlinx.android.synthetic.main.sheet_items.view.*

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

            sheetAction.showed(items.size > 5)

            SheetAdapter(SheetAdapter.SheetType.MULTIPLE, color).apply {
                list.adapter = this
                list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                sheetItems = items
                onSelectedItem { selectedItem ->
                    items.first { it.id == selectedItem.id }.isSelected = selectedItem.isSelected
                }
            }
        }

    var color: Int = Color.RED


    private var onClickOkListener: (List<Any>) -> Unit = {}

    init {
        inflate(R.layout.sheet_items, true)
    }

    fun buttonOk(title: String = "OK", handler: (List<Any>) -> Unit) {
        sheetAction.show()
        sheetAction.text = title

        onClickOkListener = handler

        sheetAction.setOnClickListener { onClickOkListener.invoke(items) }
    }
}
