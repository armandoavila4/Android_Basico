package com.jaae.primerproyecto.ejercicio_final.almacenamiento

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AnimalSqlHelper(context : Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        private const val DB_NAME = "animales.db"
        private const val DB_VERSION = 1
        private const val TBL_ANIMAL = "tbl_animal"
        private const val ID = "id"
        private const val NAME = "name"
        private const val URL = "url"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlCreate = "CREATE TABLE $TBL_ANIMAL ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT, $URL TEXT)"
        db?.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val sqlUpdate = "DROP TABLE IF EXISTS $TBL_ANIMAL"
        db?.execSQL(sqlUpdate)
        //TODO obtener info
        onCreate(db)
    }

    fun insert(animal: AnimalSqlItem):Long{
        val db = writableDatabase
        val contentValues = ContentValues().apply{
            put(NAME, animal.name)
            put(URL, animal.url)
        }

        val result = db.insert(TBL_ANIMAL, null, contentValues)
        return result
    }

    fun getAllAnimals() : ArrayList<AnimalSqlItem>{
        var animals = ArrayList<AnimalSqlItem>()
        var db = readableDatabase
        var query = "SELECT * FROM $TBL_ANIMAL"
        var cursor: Cursor?

        try{
            cursor = db.rawQuery(query, null)
        }catch (e: Exception){
            e.printStackTrace()
            return animals
        }

        var id:Int
        var name: String
        var url: String

        with(cursor){
            while (moveToNext()){
                id = getInt(getColumnIndexOrThrow(ID))
                name = getString(getColumnIndexOrThrow(NAME))
                url = getString(getColumnIndexOrThrow(URL))

                val animal = AnimalSqlItem(id, name, url)
                animals.add(animal)

            }
        }

        cursor.close()
        return animals
    }

    fun updateAnimal(animal:AnimalSqlItem):Int{
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(ID,animal.id)
            put(NAME,animal.name)
            put(URL, animal.url)
        }

        val result = db.update(TBL_ANIMAL, contentValues, "$ID = ?", arrayOf("${animal.id}"))

        db.close()
        return result
    }

    fun deleteAnimal(id:Int):Int{
        var db = writableDatabase
        val result = db.delete(TBL_ANIMAL,"$ID = ?",arrayOf("$id"))
        db.close()
        return result
    }

}