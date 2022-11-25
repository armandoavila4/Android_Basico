package com.jaae.ejerciciofinal.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.jaae.ejerciciofinal.R
import com.jaae.ejerciciofinal.databinding.ManagerAnimalesBinding
import com.jaae.ejerciciofinal.view.fragments.AnimalesFragment
import com.jaae.ejerciciofinal.view.fragments.AnimalesFragmentDirections
import com.jaae.ejerciciofinal.view.fragments.PlantasFragment

class ManagerActivity : AppCompatActivity() {

    private lateinit var binding: ManagerAnimalesBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ManagerAnimalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.managerFragments)
        setupActionBarWithNavController(navController, null)

        binding.bottomNavBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.option_animals -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.managerFragments, AnimalesFragment())
                        .commit()
                    //Toast.makeText(this,"Animales",Toast.LENGTH_SHORT).show()
                    return@setOnItemSelectedListener true
                }
                R.id.option_plants -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.managerFragments, PlantasFragment())
                        .commit()
                    //Toast.makeText(this,"Plantas",Toast.LENGTH_SHORT).show()
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null) //Regresa a la vista anterior

    }
}

