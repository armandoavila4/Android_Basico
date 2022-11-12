package com.jaae.primerproyecto.fragments.bottomNavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jaae.primerproyecto.R

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadFragment(CameraFragment())
        val menubottom = findViewById<BottomNavigationView>(R.id.bottomNavBar)

        menubottom.setOnItemSelectedListener {
            when(it.itemId){
                R.id.option_camara->{
                    loadFragment(CameraFragment())
                    return@setOnItemSelectedListener true //Solo finaliza el when , no todo el onCreate
                }
                R.id.option_keyboard->{
                    loadFragment(KeyboardFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.option_kayaking->{
                    loadFragment(KayakingFragment())
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }
    }

    private fun loadFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorFragments, fragment)
            .commit()
    }
}