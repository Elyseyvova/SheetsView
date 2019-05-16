package com.elyseev.sheets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elyseev.sheets.adapter.SheetAdapter
import com.elyseev.sheets.adapter.SheetAdapter.SheetType.SINGLE
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.util.hide
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import kotlinx.android.synthetic.main.sheet_items.view.*

internal class SheetSingle @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr) {

    var title: String = ""
        set(value) {
            if (value.isEmpty()) {
                titleGroup.hide()
            } else {
                titleGroup.show()
            }
            sheetTitle.text = value
            field = value
        }

    var items: List<SheetItem> = emptyList()
        set(value) {
            field = value

            SheetAdapter(SINGLE, color).apply {
                list.adapter = this
                list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                sheetItems = items

                onSelectedItem { onSelectedListener.invoke(it.id) }
            }
        }

    var color: Int = Color.RED

    private var onSelectedListener: (Int) -> Unit = {}

    init {
        inflate(R.layout.sheet_items, true)
    }

    fun onSelectedItem(handler: (Int) -> Unit) {
        onSelectedListener = handler
    }

    fun buttonAction(title: String = "OK", listener: () -> Unit) {
        sheetAction.show()
        sheetAction.text = title
        sheetAction.setOnClickListener { listener.invoke() }
    }
}
