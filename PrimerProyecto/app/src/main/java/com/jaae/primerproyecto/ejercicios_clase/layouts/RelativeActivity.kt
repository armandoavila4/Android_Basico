package com.jaae.primerproyecto.ejercicios_clase.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import com.jaae.primerproyecto.R

class RelativeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative)

        val chkCreditCard = findViewById<CheckBox>(R.id.chkCredit)
        val img = findViewById<ImageView>(R.id.imgAndroid)
        val btSave = findViewById<Button>(R.id.btSend)
        val rgSex = findViewById<RadioGroup>(R.id.rgSex)

        //Checkbox
        chkCreditCard.isChecked = true

        chkCreditCard.setOnCheckedChangeListener { checkbox, isChecked -> //El primer parametro es el mismo elemento dentro del listener
            Toast.makeText(this, "isChecked: $isChecked", Toast.LENGTH_SHORT).show()

            if(checkbox.isChecked){
                img.visibility = View.VISIBLE
                btSave.isEnabled = true
            }else{
                img.visibility = View.INVISIBLE
                btSave.isEnabled = false
            }
        }

        //Radio Group
        rgSex.check(R.id.radFem)

        rgSex.setOnCheckedChangeListener { radioGroup, id ->
            val selectedRb = when(id){
                R.id.radMas -> "Masculino"
                R.id.radFem -> "Femenino"
                else -> "Opción no definida"
            }
            Toast.makeText(this, "radioSelected: $selectedRb", Toast.LENGTH_SHORT).show()
        }


        /* CON INTERFAZ
        rgSex.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(RadioGroup: RadioGroup?, id: Int) {
                TODO("Not yet implemented")
            }
        }) */

        //Spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        val datos = arrayListOf("Elemento1", "Elemento2", "Elemento3", "Elemento4", "Elemento5")

        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, datos)

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) //Asigna más espacio entre los alementos de la lista
        spinner.adapter = adaptador

        spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{ //Así se declara una interfaz
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //val itemSelected = parent?.getItemIdAtPosition(position)
                val itemSelected = datos[position]
                Toast.makeText(this@RelativeActivity, "spinnerSelected: $itemSelected", Toast.LENGTH_SHORT).show() //Se debe mandar el activity cuando estás en una interfaz
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        btSave.setOnClickListener {
            val isCheck = chkCreditCard.isChecked
            val itemSelected = spinner.selectedItem
            val itemSelectedPos = spinner.selectedItemPosition
            val spinnerItem = datos[itemSelectedPos]

            val selectedCheck = when(rgSex.checkedRadioButtonId){
                R.id.radMas -> "Masculino"
                R.id.radFem -> "Femenino"
                else -> "Opción no definida"
            }

            Toast.makeText(this, "isChecked: $isCheck, isSelected: $selectedCheck, itemSelected: $spinnerItem", Toast.LENGTH_SHORT).show()
        }
    }
}