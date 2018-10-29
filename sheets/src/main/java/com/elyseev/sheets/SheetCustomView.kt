package com.elyseev.sheets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elyseev.sheets.adapter.SheetAdapter
import com.elyseev.sheets.adapter.SheetAdapter.SheetType.ACTIONS
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import com.elyseev.sheets.util.showed
import kotlinx.android.synthetic.main.sheet_view.view.*

internal class SheetCustomView @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr) {

    var title: String? = ""
        set(value) {
            sheetTitle.showed(value?.isNotEmpty() != false)
            sheetTitle.text = value
            field = value
        }

    var items: List<SheetItem> = emptyList()
        set(value) {
            field = value

            val viewItems = RecyclerView(context)
            viewItems.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            SheetAdapter(ACTIONS).apply {
                viewItems.adapter = this
                sheetItems = items
                onSelectedItem { onSelectedListener.invoke(it.id) }
            }

            group.addView(viewItems)
        }

    private var onSelectedListener: (Any?) -> Unit = { }

    init {
        inflate(R.layout.sheet_view, true)
    }

    fun setCustomView(view: View) {
        group.addView(view)
        group.requestLayout()
    }

    fun buttonOk(title: String, listener: () -> Unit) {
        sheetOk.show()
        sheetOk.text = title
        sheetOk.setOnClickListener { listener.invoke() }
    }

    fun buttonCancel(title: String, listener: () -> Unit) {
        if (title.isEmpty()) return

        sheetCancel.show()
        sheetCancel.text = title
        sheetCancel.setOnClickListener { listener.invoke() }
    }

}
