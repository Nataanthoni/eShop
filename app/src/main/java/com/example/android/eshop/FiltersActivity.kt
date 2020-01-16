package com.example.android.eshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class FiltersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.es_activity_filters)
        initToolbar()
    }

    private fun initToolbar() {
        val toolbar =
            findViewById<Toolbar>(R.id.AppBar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(getString(R.string.es_filter_page_title))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
