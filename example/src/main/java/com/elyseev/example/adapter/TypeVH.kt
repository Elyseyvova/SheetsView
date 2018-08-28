package com.elyseev.example.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.elyseev.example.R
import com.elyseev.sheets.util.inflate
import org.jetbrains.anko.find


class TypeVH(parent: ViewGroup?, private val listenerSelected: (Any) -> Unit) : RecyclerView.ViewHolder(parent?.inflate(R.layout.item_type, false)) {

    fun bind(type: Any, typeTitle: String) {
        itemView?.apply {
//            tvTitle.text = typeTitle
            find<TextView>(R.id.tvTitle).text = typeTitle
            setOnClickListener { listenerSelected.invoke(type) }
        }
    }

}
