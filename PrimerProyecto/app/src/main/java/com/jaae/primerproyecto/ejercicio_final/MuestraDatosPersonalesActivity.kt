package com.jaae.primerproyecto.ejercicio_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.jaae.primerproyecto.Persona
import com.jaae.primerproyecto.R

class MuestraDatosPersonalesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_muestra_datos_personales)

        val txtName = findViewById<TextView>(R.id.txtName)
        val txtPaterno = findViewById<TextView>(R.id.txtPaterno)
        val txtMaterno = findViewById<TextView>(R.id.txtMaterno)
        val txtAge = findViewById<TextView>(R.id.txtAge)
        val txtPhone = findViewById<TextView>(R.id.txtPhone)
        val txtPassword = findViewById<TextView>(R.id.txtPassword)

        intent.extras?.let { bundle ->
            if (bundle.containsKey("KEY_PERSONA")) {
                val persona: Persona = bundle.getSerializable("KEY_PERSONA") as Persona
                txtName.text = txtName.text.toString()+persona.name
                txtPaterno.text = txtPaterno.text.toString()+persona.paterno
                txtMaterno.text = txtMaterno.text.toString()+persona.materno
                txtAge.text = txtAge.text.toString()+persona.age.toString()
                txtPhone.text = txtPhone.text.toString()+persona.phone.toString()
                txtPassword.text = txtPassword.text.toString()+persona.password.toString()
            }
        } ?: showEmptyInfo()

    }

    private fun showEmptyInfo() {
        Toast.makeText(this, "No existen datos para mostrar", Toast.LENGTH_LONG).show()
    }
}