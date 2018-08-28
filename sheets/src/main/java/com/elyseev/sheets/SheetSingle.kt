package com.elyseev.sheets

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import com.elyseev.sheets.adapter.SheetAdapter
import com.elyseev.sheets.adapter.SheetAdapter.SheetType.SINGLE
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.util.hide
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import kotlinx.android.synthetic.main.sheet_view.view.*

internal class SheetSingle @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var title: String = ""
        set(value) {
            if (value.isEmpty()) sheetTitle.hide() else sheetTitle.show()
            sheetTitle.text = value
            field = value
        }

    var items: List<SheetItem> = emptyList()
        set(value) {
            field = value

            val viewItems = RecyclerView(context)
            viewItems.layoutManager = LinearLayoutManager(context, VERTICAL, false)

            SheetAdapter(SINGLE, color).apply {
                viewItems.adapter = this
                sheetItems = items

                onSelectedItem { onSelectedListener.invoke(it.id) }
            }

            group.addView(viewItems)
        }

    var color: Int = Color.RED

    private var onSelectedListener: (Int) -> Unit = {}

    init {
        inflate(R.layout.sheet_view, true)
    }

    fun onSelectedItem(handler: (Int) -> Unit) {
        onSelectedListener = handler
    }
}
