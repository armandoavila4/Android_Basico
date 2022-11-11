package com.jaae.primerproyecto.ejercicios_clase.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.jaae.primerproyecto.R

class TabsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        val tabs = findViewById<TabLayout>(R.id.tabs)

        tabs.addTab(tabs.newTab().setText("Soccer"))
        tabs.addTab(tabs.newTab().setText("Americano"))
        tabs.addTab(tabs.newTab().setIcon(android.R.drawable.ic_dialog_map))

        tabs.tabGravity = TabLayout.GRAVITY_FILL

    }
}