package com.jaae.primerproyecto.ejercicios_clase.almacenamiento

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jaae.primerproyecto.R

class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        val btSaveFile = findViewById<Button>(R.id.btSaveFile)
        val etInfo = findViewById<EditText>(R.id.etInfo)


        val filename = "test.txt"
        val body = "body example"

        resources.openRawResource(R.raw.ejemplo_raw).use {
            val text_row = it.bufferedReader().use{
                it.readText()
            }
            Toast.makeText(this, "Raw: $text_row", Toast.LENGTH_LONG).show()
        }

        btSaveFile.setOnClickListener {
            openFileOutput(filename, Context.MODE_PRIVATE).use { output ->
                //output.write(body.toByteArray())
                output.write(etInfo.text.toString().toByteArray())
            }

            openFileInput(filename).use {
                val text = it.bufferedReader().use {
                    it.readText()
                }
                Toast.makeText(this, "Guardado: $text", Toast.LENGTH_SHORT).show()
            }
        }
    }
}