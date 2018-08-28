package com.elyseev.sheets

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import com.elyseev.sheets.adapter.SheetAdapter
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import com.elyseev.sheets.util.showed
import kotlinx.android.synthetic.main.sheet_view.view.*

internal class SheetMultiple @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var title: String = ""
        set(value) {
            sheetTitle.showed(value.isNotEmpty())
            sheetTitle.text = value
            field = value
        }

    var items: List<SheetItem> = emptyList()
        set(value) {
            field = value

            sheetOkTop.showed(items.size > 5)

            val viewItems = RecyclerView(context)
            viewItems.layoutManager = LinearLayoutManager(context, VERTICAL, false)

            SheetAdapter(SheetAdapter.SheetType.MULTIPLE).apply {
                viewItems.adapter = this
                sheetItems = items
                onSelectedItem { selectedItem ->
                    items.first { it.id == selectedItem.id }.isSelected = selectedItem.isSelected
                }
            }

            group.addView(viewItems)
        }

    private var onClickOkListener: (List<Any>) -> Unit = {}

    init {
        inflate(R.layout.sheet_view, true)
    }

    fun buttonOk(title: String = "OK", handler: (List<Any>) -> Unit) {
        sheetOkTop.show()
        sheetOkTop.text = title

        onClickOkListener = handler

        sheetOkTop.setOnClickListener { onClickOkListener.invoke(items) }
    }
}
