package com.elyseev.sheets.adapter.holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elyseev.sheets.R
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import com.elyseev.sheets.util.showed
import kotlinx.android.synthetic.main.item_sheet_single.view.*

internal class SheetSingleVH(
        parent: ViewGroup,
        private val colorItem: Int,
        private val listenerSelected: ((SheetItem) -> Unit)?
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_sheet_single, false)!!) {

    fun setupUI(item: SheetItem) {
        itemView.apply {
            item.icon?.apply {
                icon.show()
                icon.setImageResource(this)
            }

            title.text = item.title

            check.setColorFilter(colorItem)
            check.showed(item.isSelected)

            setOnClickListener { listenerSelected?.invoke(item) }
        }
    }
}