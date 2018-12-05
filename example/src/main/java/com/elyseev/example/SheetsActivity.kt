package com.elyseev.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elyseev.example.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_sheets.*

class SheetsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheets)
        setupUI()
    }

    // ===============================================================================================================================================
    // Private
    // ===============================================================================================================================================

    private fun setupUI() {
        pager.adapter = PagerAdapter(this)
        tabs.setupWithViewPager(pager)
    }
}
