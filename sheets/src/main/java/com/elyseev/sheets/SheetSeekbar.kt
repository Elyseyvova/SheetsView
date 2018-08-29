package com.elyseev.sheets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import com.elyseev.sheets.model.SheetSeekUnit
import com.elyseev.sheets.util.hide
import com.elyseev.sheets.util.inflate
import com.elyseev.sheets.util.show
import kotlinx.android.synthetic.main.sheet_seekbar.view.*
import kotlinx.android.synthetic.main.sheet_view.view.*

internal class SheetSeekbar @JvmOverloads constructor(context: Context, val attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    var title: String = ""
        set(value) {
            if (value.isEmpty()) sheetTitle.hide() else sheetTitle.show()
            sheetTitle.text = value
            field = value
        }

    var currentSeekUnit: SheetSeekUnit = SheetSeekUnit("", 50, 220, 150)
        set(value) {
            field = value
            value.apply {
                labelCurrent.text = "$current $unit"
                labelMin.text = "$min $unit"
                labelMax.text = "$max $unit"

                seek.max = max - min
                seek.progress = current - min

                seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        labelCurrent.text = "${progress + min} $unit"
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {

                    }
                })
            }
        }

    private var onClickedOkListener: (Int) -> Unit = {}

    init {
        inflate(R.layout.sheet_view, true)
        group.addView(View.inflate(context, R.layout.sheet_seekbar, null))
    }

    fun buttonOk(title: String = "OK", handler: (Int) -> Unit) {
        onClickedOkListener = handler
        sheetOk.show()
        sheetOk.text = title
        sheetOk.setOnClickListener { onClickedOkListener.invoke(seek.progress + currentSeekUnit.min) }
    }
}
