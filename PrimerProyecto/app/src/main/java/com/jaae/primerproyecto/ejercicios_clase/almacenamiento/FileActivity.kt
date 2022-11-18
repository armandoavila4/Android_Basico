package com.jaae.primerproyecto.ejercicios_clase.almacenamiento

import com.jaae.primerproyecto.R
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        val btSaveFile = findViewById<Button>(R.id.btSaveFile)
        val etInfo = findViewById<EditText>(R.id.etInfo)
        val ivFile = findViewById<ImageView>(R.id.ivFile)

        var urlImage:String = "https://cdn-icons-png.flaticon.com/512/6420/6420762.png"

        Glide.with(this).load(urlImage).centerCrop().into(ivFile)


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