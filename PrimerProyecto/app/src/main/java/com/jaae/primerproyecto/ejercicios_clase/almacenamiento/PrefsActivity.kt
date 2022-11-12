package com.jaae.primerproyecto.ejercicios_clase.almacenamiento

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.jaae.primerproyecto.R

class PrefsActivity : AppCompatActivity() {

    private lateinit var tvData: TextView
    private lateinit var etData: EditText
    private lateinit var btSave: Button
    private lateinit var btDelete: Button

    private lateinit var prefs: SharedPreferences
    private val PREFS_NAME = "com.jaae.db_prefs"
    private val SHARE_DATA = "share_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefs)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        tvData = findViewById(R.id.txtPrefsData)
        etData = findViewById(R.id.etxtPrefsData)
        btSave = findViewById(R.id.btnPrefsSave)
        btDelete = findViewById(R.id.btnPrefsDelete)


        btSave.setOnClickListener {
            prefs.edit().putString(SHARE_DATA, etData.text.toString()).apply()
            configView()
        }

        btDelete.setOnClickListener {
            prefs.edit().remove(SHARE_DATA).apply()
            configView()
        }
        configView()

    }

    private fun askInfo(){
        tvData.visibility = View.INVISIBLE
        btDelete.visibility = View.INVISIBLE
        etData.visibility = View.VISIBLE
        btSave.visibility = View.VISIBLE
    }

    private fun showInfo(){
        tvData.visibility = View.VISIBLE
        btDelete.visibility = View.VISIBLE
        etData.visibility = View.INVISIBLE
        btSave.visibility = View.INVISIBLE

        tvData.text = "Hola ${prefs.getString(SHARE_DATA, "")}"
    }

    private fun isInfoSaved():Boolean{
        val myName = prefs.getString(SHARE_DATA,"")

        /*if(myName?.isNotEmpty() == true){
            return true
        }else{
            return false
        }*/

        return myName?.isNotEmpty() == true
    }

    private fun configView(){
        if(isInfoSaved()) showInfo() else askInfo()
    }
}