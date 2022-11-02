package com.jaae.primerproyecto.ejercicios_clase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jaae.primerproyecto.R
import com.jaae.primerproyecto.Usuario

class ExplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        val btSend = findViewById<Button>(R.id.btSend)
        val etName = findViewById<EditText>(R.id.etName)

        btSend.setOnClickListener{
            val text = etName.text.toString()
            if(text.isEmpty()){
                Toast.makeText(this, "Nombre vacio",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, ExplicitDetailActivity::class.java).apply {
                    putExtra("KEY_NAME", "Armando")
                    putExtra("KEY_LASTNAME", "Avila")
                    putExtra("KEY_AGE", 34)

                    val user = Usuario("Pedro", "Fermnandez", 25)
                    putExtra("KEY_USER", user)

                    putExtra("KEY_EDITABLE", text)
                }
                startActivity(intent)
            }
        }
    }
}