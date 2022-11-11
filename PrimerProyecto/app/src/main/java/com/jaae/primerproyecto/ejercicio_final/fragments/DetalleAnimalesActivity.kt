package com.jaae.primerproyecto.ejercicio_final.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.jaae.primerproyecto.Persona
import com.jaae.primerproyecto.R

class DetalleAnimalesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_animales)

        val txtEnfermedad = findViewById<TextView>(R.id.txtEnfermedad)
        val txtSexoAnimal = findViewById<TextView>(R.id.txtSexoAnimal)
        val txtHabitat = findViewById<TextView>(R.id.txtHabitat)

        intent.extras?.let { bundle ->
            if (bundle.containsKey("KEY_ENFERMEDAD")) {
                //val persona: Persona = bundle.getSerializable("KEY_PERSONA") as Persona
                txtEnfermedad.text = txtEnfermedad.text.toString()+bundle.getString("KEY_ENFERMEDAD")
                txtSexoAnimal.text = txtSexoAnimal.text.toString()+bundle.getString("KEY_SEXO")
                txtHabitat.text = txtHabitat.text.toString()+bundle.getString("KEY_HABITAT")
            }
        } ?: showEmptyInfo()

    }

    private fun showEmptyInfo() {
        Toast.makeText(this, "No existen datos para mostrar", Toast.LENGTH_LONG).show()
    }
}