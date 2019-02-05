package com.elyseev.example

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.elyseev.example.R.id.rv
import com.elyseev.example.adapter.TypesAdapter
import com.elyseev.example.data.*
import com.elyseev.sheets.model.SheetItem
import com.elyseev.sheets.model.SheetSeekUnit
import com.elyseev.sheets.util.*
import kotlinx.android.synthetic.main.fragment_sheets_list.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import java.util.*

class SheetsTypeFragment : Fragment() {

    companion object {
        fun newInstance(type: SheetType) = SheetsTypeFragment().also { it.arguments = bundleOf("type" to type) }
    }

    private val icons = arrayListOf(
        R.drawable.ic_headset,
        R.drawable.ic_room,
        R.drawable.ic_sentiment,
        R.drawable.ic_sentiment_dissatisfied,
        R.drawable.ic_sentiment_very_satisfied,
        R.drawable.ic_start,
        R.drawable.ic_text
    )

    private val randomIcon: Int
        get() = icons[Random().nextInt(icons.size)]


    // ===============================================================================================================================================
    // Android
    // ===============================================================================================================================================

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_sheets_list, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }


    // ===============================================================================================================================================
    // Private
    // ===============================================================================================================================================

    private fun setupUI() {
        val type = arguments?.getSerializable("type") as SheetType

        rv.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        rv.adapter = TypesAdapter(type) { type ->
            when (type) {
                AlertType.ALERT -> showAlert()
                AlertType.DIALOG -> showDialog()
                AlertType.CUSTOM_COLOR -> showAlertCustomColors()
                ActionsType.ACTIONS -> showActionsList()
                ActionsType.ACTIONS_WITH_ICON -> showActionsWithIconList()
                ActionsType.ACTIONS_CUSTOM_COLORS -> showActionsCustomsColors()
                SingleType.SINGLE -> showSingleList()
                SingleType.SINGLE_WITH_ICON -> showSingleWithIconList()
                MultipleType.MULTIPLE -> showMultipleList()
                MultipleType.MULTIPLE_WITH_ICON -> showMultipleWithIconList()
                SeekType.SEEK -> showSeek()
                EditType.EDIT -> showEdit()
                CustomType.CUSTOM -> showCustom()
            }
        }
    }

    private fun showActionsList() {
        val items = (0..50).map { SheetItem(it, null, "Action $it") }
//        ctx.showSheetAction("Action list", items) { toast(items[it].title) }
    }

    private fun showActionsWithIconList() {
        val items = (0..50).map { SheetItem(it, randomIcon, "Action with icon $it") }
//        ctx.showSheetAction("Action with icon list", items) { toast(items[it].title) }
    }

    private fun showActionsCustomsColors() {
        val params = bundleOf(SheetsView.TITLE_COLOR to Color.RED, SheetsView.ACTION_TEXT_COLOR to Color.GREEN)
        val items = (0..50).map { SheetItem(it, null, "Action $it") }
//        ctx.showSheetAction("Action list", items, params) { toast(items[it].title) }
    }

    private fun showSingleList() {
        val items = (0..50).map { SheetItem(it, null, "Single $it") }
        items[2].isSelected = true

//        ctx.showSheetSingle("Single list", items) { toast(items[it].title) }
    }

    private fun showSingleWithIconList() {
        val items = (0..50).map { SheetItem(it, randomIcon, "Single with icon $it") }
        items[2].isSelected = true

//        ctx.showSheetSingle("Single with icon list", items) { toast(items[it].title) }
    }

    private fun showMultipleList() {
        val items = (0..50).map { SheetItem(it, null, "Multiple $it") }
        items[2].isSelected = true
        items[5].isSelected = true
        items[7].isSelected = true
        items[8].isSelected = true

        ctx.showSheetMultiple("Multiple list", "OK", items) { toast("Selected ${it.filter { it.isSelected }.size} items") }
    }

    private fun showMultipleWithIconList() {
        val items = (0..50).map { SheetItem(it, randomIcon, "Multiple with icon $it") }
        items[2].isSelected = true
        items[5].isSelected = true
        items[7].isSelected = true
        items[8].isSelected = true

//        ctx.showSheetMultiple("Multiple with icon list", "OK", items) { toast("Selected ${it.filter { it.isSelected }.size} items") }
    }

    private fun showSeek() {
        val unit = SheetSeekUnit("qwe", 100, 400, 140)
        ctx.showSheetSeek("Seek title", "OK", unit) { toast("Current unit: $it") }
    }

    private fun showEdit() {
        ctx.showSheetEdit("Edit title", "OK", null, "Current text", "Your hint...") { toast("Current text: $it") }
    }

    private fun showAlert() {
        ctx.showSheetAlert("Title", "OK", "Current long long long  long long long  long long long  long long long message") { }
    }

    private fun showDialog() {
        ctx.showSheetDialog(
            title = "Warning",
            titleOk = "OK",
            titleCancel = "CANCEL",
            message = "Current long long long  long long long  long long long  long long long message",
            listenerOk = { toast("Clicked OK") },
            listenerCancel = { toast("Clicked CANCEL") })
    }

    private fun showAlertCustomColors() {
        val params = bundleOf(SheetsView.TITLE_COLOR to Color.RED, SheetsView.OK_TEXT_COLOR to Color.GREEN)
        ctx.showSheetAlert("Custom colors", "OK", "Current long long long  long long long  long long long  long long long message", params) { }
    }

    private fun showCustom() {
        val view = View.inflate(ctx, R.layout.view_custom, null)
//        ctx.showSheetCustom("Custom view", "OK", "Cancel", false, view)
    }

}
