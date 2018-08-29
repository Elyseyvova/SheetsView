package com.elyseev.example.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.elyseev.example.data.*
import com.elyseev.example.data.SheetType.*

class TypesAdapter(val type: SheetType, private var listenerSelected: (Any) -> Unit) : RecyclerView.Adapter<TypeVH>() {

    override fun getItemCount() = when (type) {
        ALERT -> AlertType.values().size
        ACTIONS -> ActionsType.values().size
        SINGLE -> SingleType.values().size
        MULTIPLE -> MultipleType.values().size
        SEEK -> SeekType.values().size
        EDIT -> EditType.values().size
        CUSTOM -> CustomType.values().size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TypeVH(parent, listenerSelected)

    override fun onBindViewHolder(holder: TypeVH, position: Int) {
        when (type) {
            ALERT -> holder.bind(AlertType.values()[position], AlertType.values()[position].title)
            ACTIONS -> holder.bind(ActionsType.values()[position], ActionsType.values()[position].title)
            SINGLE -> holder.bind(SingleType.values()[position], SingleType.values()[position].title)
            MULTIPLE -> holder.bind(MultipleType.values()[position], MultipleType.values()[position].title)
            SEEK -> holder.bind(SeekType.values()[position], SeekType.values()[position].title)
            EDIT -> holder.bind(EditType.values()[position], EditType.values()[position].title)
            CUSTOM -> holder.bind(CustomType.values()[position], CustomType.values()[position].title)
        }
    }
}
