package com.elyseev.sheets.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.elyseev.sheets.adapter.SheetAdapter.SheetType.*
import com.elyseev.sheets.adapter.holder.SheetActionVH
import com.elyseev.sheets.adapter.holder.SheetMultipleVH
import com.elyseev.sheets.adapter.holder.SheetSingleVH
import com.elyseev.sheets.model.SheetItem

internal class SheetAdapter(val type: SheetType, val colorItem: Int = Color.RED) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var sheetItems = listOf<SheetItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    private var listenerSelected: ((SheetItem) -> Unit)? = null

    override fun getItemCount() = sheetItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (type) {
        ACTIONS -> SheetActionVH(parent, colorItem, listenerSelected)
        SINGLE -> SheetSingleVH(parent, colorItem, listenerSelected)
        MULTIPLE -> SheetMultipleVH(parent, colorItem, listenerSelected)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        when (holder) {
            is SheetActionVH -> holder.setupUI(sheetItems[position])
            is SheetSingleVH -> holder.setupUI(sheetItems[position])
            is SheetMultipleVH -> holder.setupUI(sheetItems[position])
        }
    }

    fun onSelectedItem(listener: (SheetItem) -> Unit) {
        this.listenerSelected = listener
    }

    enum class SheetType {
        ACTIONS, SINGLE, MULTIPLE
    }
}
