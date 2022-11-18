package com.jaae.primerproyecto.ejercicios_clase.almacenamiento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaae.primerproyecto.R

class SqliteActivity : AppCompatActivity() {

    private lateinit var sqlHelper: SqlHelper
    lateinit var userSqlAdapter: UserSqlAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        sqlHelper = SqlHelper(this)

        val etSqlName = findViewById<EditText>(R.id.etSqlName)
        val etDescription = findViewById<EditText>(R.id.etSqlDescription)
        val btAdd = findViewById<Button>(R.id.btnSqlAdd)
        val btView = findViewById<Button>(R.id.btnSqlView)
        val btUpdate = findViewById<Button>(R.id.btnSqlUpdate)
        val btDelete = findViewById<Button>(R.id.btnSqlDelete)

        //Se agrega recyclerView a la activiy
        val list = this.findViewById<RecyclerView>(R.id.list_sql)
        userSqlAdapter = UserSqlAdapter(sqlHelper.getAllUsers())

        list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list.itemAnimator = DefaultItemAnimator()
        list.adapter = userSqlAdapter

        btAdd.setOnClickListener {
            if(etSqlName.text.toString().isEmpty() || etDescription.text.toString().isEmpty()){
                Toast.makeText(this, "Ingrese Informacion", Toast.LENGTH_LONG).show()
            }else{
                val user = UserSqlModel(name = etSqlName.text.toString(), description = etDescription.text.toString()) //No se coloca el ID en el insert porque es auto increment
                val result = sqlHelper.insert(user)
                if(result>-1){
                    Toast.makeText(this, "Información guardada", Toast.LENGTH_LONG).show()
                    userSqlAdapter.updateItems(sqlHelper.getAllUsers())
                }else{
                    Toast.makeText(this, "Información no guardada", Toast.LENGTH_LONG).show()
                }
            }
        }

        btView.setOnClickListener {
            val list = sqlHelper.getAllUsers()
            Log.e("Lista:", list.toString())
        }

        btUpdate.setOnClickListener {
            val userUpdated = UserSqlModel(id = 3, name= "Pablito", description = "Nueva Descripcion") //Datos del nuevo usuario
            val result = sqlHelper.updateUser(userUpdated)
            if(result>-1){
                Toast.makeText(this, "Información actualizada", Toast.LENGTH_LONG).show()
                userSqlAdapter.updateItems(sqlHelper.getAllUsers())
            }else{
                Toast.makeText(this, "Información no actualizada", Toast.LENGTH_LONG).show()
            }
        }

        btDelete.setOnClickListener {
            val result = sqlHelper.deleteUser(2)
            if(result>0){
                Toast.makeText(this, "Información eliminada", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Información no eliminada", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        userSqlAdapter.updateItems(sqlHelper.getAllUsers())
    }
}