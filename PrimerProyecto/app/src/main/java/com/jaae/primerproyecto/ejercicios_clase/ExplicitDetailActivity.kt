package com.jaae.primerproyecto.ejercicios_clase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.jaae.primerproyecto.R
import com.jaae.primerproyecto.Usuario

class ExplicitDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_detail)

        val txtViewName = findViewById<TextView>(R.id.txtViewName)
        val txtViewLastName = findViewById<TextView>(R.id.txtViewLastName)
        val txtViewAge = findViewById<TextView>(R.id.txtViewAge)

        val txtViewNameUser = findViewById<TextView>(R.id.txtViewNameUser)
        val txtViewLastNameUser = findViewById<TextView>(R.id.txtViewLastNameUser)
        val txtViewAgeUser = findViewById<TextView>(R.id.txtViewAgeUser)

        val txtEditable = findViewById<TextView>(R.id.txtViewNameEdit)

        intent.extras?.let { bundle ->
            if (bundle.containsKey("KEY_NAME")) {
                val name = bundle.getString("KEY_NAME", "")
                txtViewName.text = name
            }
            if (bundle.containsKey("KEY_LASTNAME")) {
                val lastname = bundle.getString("KEY_LASTNAME", "")
                txtViewLastName.text = lastname
            }
            if (bundle.containsKey("KEY_AGE")) {
                val age = bundle.getInt("KEY_AGE", 0)
                txtViewAge.text = age.toString()
            }
            if (bundle.containsKey("KEY_USER")) {
                val user: Usuario = bundle.getSerializable("KEY_USER") as Usuario

                txtViewNameUser.text = user.name
                txtViewLastNameUser.text = user.lastName
                txtViewAgeUser.text = user.age.toString()
            }
            if (bundle.containsKey("KEY_EDITABLE")) {
                val nameEditable = bundle.getString("KEY_EDITABLE", "")
                txtEditable.text = nameEditable
            }


        } ?: showEmptyInfo()
    }

    private fun showEmptyInfo() {
        Toast.makeText(this, "Extras vacios", Toast.LENGTH_SHORT).show()
    }
}