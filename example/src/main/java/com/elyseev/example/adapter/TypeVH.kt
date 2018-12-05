package com.elyseev.example.adapter

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elyseev.example.R
import com.elyseev.sheets.util.inflate
import org.jetbrains.anko.find
import java.nio.file.Files.find


class TypeVH(parent: ViewGroup?, private val listenerSelected: (Any) -> Unit) : RecyclerView.ViewHolder(parent?.inflate(R.layout.item_type, false)!!) {

    fun bind(type: Any, typeTitle: String) {
        itemView?.apply {
//            tvTitle.text = typeTitle
            find<TextView>(R.id.tvTitle).text = typeTitle
            setOnClickListener { listenerSelected.invoke(type) }
        }
    }

}
