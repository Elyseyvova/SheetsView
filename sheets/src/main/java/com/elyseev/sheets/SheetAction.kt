package com.elyseev.sheets

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import com.elyseev.sheets.adapter.SheetAdapter
import com.elyseev.sheets.adapter.SheetAdapter.SheetType.ACTIONS
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.util.SheetsView
import com.elyseev.sheets.util.SheetsView.Companion.ACTION_TEXT_COLOR
import com.elyseev.sheets.util.hide
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import kotlinx.android.synthetic.main.sheet_view.view.*

internal class SheetAction @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
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

            SheetAdapter(ACTIONS, params?.getInt(ACTION_TEXT_COLOR, resources.getColor(R.color.action_text_color)) ?: Color.BLACK).apply {
                viewItems.adapter = this
                sheetItems = items
                onSelectedItem { onSelectedListener.invoke(it.id) }
            }

            group.addView(viewItems)
        }

    var params: Bundle? = null
        set(value) {
            field = value
            value?.apply {
                root.background.setColorFilter(
                    getInt(SheetsView.BACKGROUND_COLOR, resources.getColor(R.color.background_color)),
                    PorterDuff.Mode.SRC_IN
                )
                sheetTitle.setTextColor(getInt(SheetsView.TITLE_COLOR, resources.getColor(R.color.title_color)))
            }
        }


    private var onSelectedListener: (Int) -> Unit = { }

    init {
        inflate(R.layout.sheet_view, true)
    }

    fun onSelectedItem(handler: (Int) -> Unit) {
        onSelectedListener = handler
    }
}
