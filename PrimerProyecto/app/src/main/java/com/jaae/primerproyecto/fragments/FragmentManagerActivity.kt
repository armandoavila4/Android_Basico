package com.jaae.primerproyecto.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaae.primerproyecto.R

class FragmentManagerActivity : AppCompatActivity() {
    val name = "Armando Avila"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_manager)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, FirstFragment.newInstance(name),"FirstFragment")
            //.addToBackStack("FirstFragment")
            .commit()

    }
}