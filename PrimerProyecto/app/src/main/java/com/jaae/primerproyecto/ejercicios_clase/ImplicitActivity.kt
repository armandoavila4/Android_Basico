package com.jaae.primerproyecto.ejercicios_clase

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jaae.primerproyecto.R

class ImplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_implicit)

        val boton = findViewById<Button>(R.id.button)
        boton.setOnClickListener {
            val email = Intent(Intent.ACTION_SENDTO).apply {
                data= Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayListOf("armandito.avila@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Avio urgente")
                putExtra(Intent.EXTRA_TEXT, "Ejemplo de correo enviado con app de Android")
            }
            startActivity(Intent.createChooser(email, "No se puede"))
        }
    }
}