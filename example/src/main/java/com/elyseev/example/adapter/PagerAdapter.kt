package com.elyseev.example.adapter

import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.elyseev.example.SheetsTypeFragment
import com.elyseev.example.data.SheetType

/**
 * Created by Elyseev Vladimir on 31.05.18.
 */
class PagerAdapter(ctx: AppCompatActivity): FragmentStatePagerAdapter(ctx.supportFragmentManager) {

    override fun getCount() = SheetType.values().size

    override fun getItem(position: Int) = SheetsTypeFragment.newInstance(SheetType.values()[position])

    override fun getPageTitle(position: Int) = SheetType.values()[position].name
}