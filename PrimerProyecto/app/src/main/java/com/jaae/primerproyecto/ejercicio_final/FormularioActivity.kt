package com.jaae.primerproyecto.ejercicio_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jaae.primerproyecto.Persona
import com.jaae.primerproyecto.R
import java.text.SimpleDateFormat

class FormularioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        val btSend = findViewById<Button>(R.id.btSend)
        val etName = findViewById<EditText>(R.id.etName)
        val etPaterno = findViewById<EditText>(R.id.etPaterno)
        val etMaterno = findViewById<EditText>(R.id.etMaterno)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        btSend.setOnClickListener{
            val name = etName.text.toString()
            val paterno = etPaterno.text.toString()
            val materno = etMaterno.text.toString()
            val age = etAge.text.toString()
            val phone = etPhone.text.toString()
            val password = etPassword.text.toString()

            if(name.isEmpty()){
                Toast.makeText(this, "Nombre vacio", Toast.LENGTH_SHORT).show()
            }else if(paterno.isEmpty()){
                Toast.makeText(this, "Apellido Paterno vacio", Toast.LENGTH_SHORT).show()
            }else if(materno.isEmpty()){
                Toast.makeText(this, "Apellido Materno vacio", Toast.LENGTH_SHORT).show()
            }else if(age.isEmpty()){
                Toast.makeText(this, "Edad vacio", Toast.LENGTH_SHORT).show()
            }else if(phone.isEmpty()){
                Toast.makeText(this, "Teléfono vacio", Toast.LENGTH_SHORT).show()
            }else if(password.isEmpty()){
                Toast.makeText(this, "Password vacio", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, MuestraDatosPersonalesActivity::class.java).apply {
                    val persona = Persona(name, paterno, materno, age.toInt(), phone.toLong(), password)
                    putExtra("KEY_PERSONA", persona)
                }
                startActivity(intent)
            }
        }

    }
}