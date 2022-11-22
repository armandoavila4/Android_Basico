package com.jaae.primerproyecto.ejercicio_final.almacenamiento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.jaae.primerproyecto.R

class DetalleAnimalSqlActivity : AppCompatActivity() {

    private lateinit var animalSqlHelper: AnimalSqlHelper
    lateinit var animalSqlAdapter: AnimalSqlAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_animal_sql)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        animalSqlHelper = AnimalSqlHelper(this)

        //Declaro los elementos de la vista
        var txtName = this.findViewById<EditText>(R.id.etNameDetalleSql)
        var txtUrl = this.findViewById<EditText>(R.id.etUrlDetalleSql)
        var imgUrl = this.findViewById<ImageView>(R.id.ivImageDetalleSql)

        var btnAdd = this.findViewById<Button>(R.id.btAddAnimalSql)
        var btnEdit = this.findViewById<Button>(R.id.bteditAnimalSql)
        var btnDelete = this.findViewById<Button>(R.id.btDeleteAnimalSql)


        //Se lee la action a realizar
        intent.extras?.let { bundle ->
            if (bundle.containsKey("KEY_ACTION")) {
                val action = bundle.getString("KEY_ACTION")
                if (action != null) {
                    Log.e("ACTION", action)
                    if(action == "ADD"){
                        btnAdd.visibility = View.VISIBLE
                        btnEdit.visibility = View.GONE
                        btnDelete.visibility = View.GONE

                        //Agrego el evento guardar
                        btnAdd.setOnClickListener{
                            if(txtName.text.isEmpty()){
                                muestraVacio("Nombre")
                            }else if(txtUrl.text.isEmpty()){
                                muestraVacio("Url")
                            }else{
                                //Registro en le BD
                                val animal = AnimalSqlItem(name = txtName.text.toString(), url = txtUrl.text.toString())
                                var result = animalSqlHelper.insert(animal)
                                if(result > -1){
                                    this.finish()
                                }
                            }
                        }
                    }else{
                        if(bundle.containsKey("KEY_ANIMAL")){
                            var animal = bundle.getSerializable("KEY_ANIMAL") as AnimalSqlItem


                            //Se cargan los datos en la vissta
                            txtName.setText(animal.name)
                            txtUrl.setText(animal.url)

                            Glide.with(this).load(animal.url).placeholder(R.mipmap.ic_launcher_round).centerCrop().into(imgUrl)

                            //Agrego el evento eliminar
                            btnDelete.setOnClickListener {
                                var result = animalSqlHelper.deleteAnimal(animal.id)
                                if(result > -1){
                                    this.finish()
                                }else{
                                    Toast.makeText(this,"${animal.name} no eliminad@",Toast.LENGTH_SHORT).show()
                                }
                            }

                            btnEdit.setOnClickListener {
                                var result = animalSqlHelper.updateAnimal(AnimalSqlItem(animal.id,txtName.text.toString(),txtUrl.text.toString()))
                                if(result > -1){
                                    this.finish()
                                }else{
                                    Toast.makeText(this,"${animal.name} no actualizad@",Toast.LENGTH_SHORT).show()
                                }
                            }

                        }else{
                            showEmptyInfo()
                        }

                        btnAdd.visibility = View.GONE
                        btnEdit.visibility = View.VISIBLE
                        btnDelete.visibility = View.VISIBLE
                    }
                }

                //val persona: Persona = bundle.getSerializable("KEY_PERSONA") as Persona
                //txtEnfermedad.text = txtEnfermedad.text.toString()+bundle.getString("KEY_ENFERMEDAD")
            }
        } ?: showEmptyInfo()

    }

    private fun muestraVacio(elemento: String) {
        Toast.makeText(this,"${elemento} vacío",Toast.LENGTH_SHORT).show()
    }

    private fun showEmptyInfo() {
        Toast.makeText(this,"No hay información",Toast.LENGTH_SHORT).show()
    }
}