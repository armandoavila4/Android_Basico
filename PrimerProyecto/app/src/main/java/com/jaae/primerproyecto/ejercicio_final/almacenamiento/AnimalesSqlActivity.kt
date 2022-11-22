package com.jaae.primerproyecto.ejercicio_final.almacenamiento

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jaae.primerproyecto.R

class AnimalesSqlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animales_sql)

        supportFragmentManager.beginTransaction()
            .add(R.id.containerAnimalesSql, AnimalSqlFragment.newInstance(), "AnimalSqlFragment")
            .commit()

        var btnAdd = this.findViewById<Button>(R.id.btnAddAnimal)
        btnAdd.setOnClickListener {
            //Mando a la actividad de agregar un nuevo animal
            val intent = Intent(this, DetalleAnimalSqlActivity::class.java).apply {
                //Toast.makeText(this, "Agregar", Toast.LENGTH_SHORT).show()
                putExtra("KEY_ACTION", "ADD")
            }
            startActivity(intent)
        }
    }
}