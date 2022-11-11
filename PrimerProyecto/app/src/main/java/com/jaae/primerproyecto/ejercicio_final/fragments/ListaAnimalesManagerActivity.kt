package com.jaae.primerproyecto.ejercicio_final.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaae.primerproyecto.R

class ListaAnimalesManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_animales)

        supportFragmentManager.beginTransaction()
            .add(R.id.containerAnimales, AnimalesFragment.newInstance(),"AnimalesFragment")
            .commit()
    }
}