package com.jaae.ejerciciofinal.model.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.jaae.ejerciciofinal.model.entities.Animal

class AnimalDB(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    companion object{
        private const val DB_NAME = "animales.db"
        private const val DB_VERSION = 1
        private const val TBL_ANIMAL = "tbl_animal"
        private const val ID = "id"
        private const val NAME = "name"
        private const val URL = "url"
        private const val DESCRIPTION = "description"
        private const val WEIGHT = "weight"
        private const val LOCATION = "location"
        private const val SEX = "sex"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlCreate = "CREATE TABLE $TBL_ANIMAL ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT, $DESCRIPTION TEXT, $URL TEXT, $SEX TEXT, $LOCATION TEXT, $WEIGHT REAL)"
        Log.e("Create", sqlCreate)
        db?.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val sqlUpdate = "DROP TABLE IF EXISTS $TBL_ANIMAL"
        db?.execSQL(sqlUpdate)
        onCreate(db)
    }

    fun insertAnimal(animal: Animal):Long{
        val db = writableDatabase
        val contentValues = ContentValues().apply{
            put(NAME, animal.name)
            put(URL, animal.url)
            put(DESCRIPTION, animal.description)
            put(SEX, animal.sex)
            put(LOCATION, animal.location)
            put(WEIGHT, animal.weight)
        }

        val result = db.insert(TBL_ANIMAL, null, contentValues)
        return result
    }

    fun getAllAnimals() : ArrayList<Animal>{
        var animals = ArrayList<Animal>()
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
        var description : String
        var sex: String
        var location: String
        var weight: Double

        with(cursor){
            while (moveToNext()){
                id = getInt(getColumnIndexOrThrow(ID))
                name = getString(getColumnIndexOrThrow(NAME))
                url = getString(getColumnIndexOrThrow(URL))
                sex = getString(getColumnIndexOrThrow(SEX))
                description = getString(getColumnIndexOrThrow(DESCRIPTION))
                location = getString(getColumnIndexOrThrow(LOCATION))
                weight = getDouble(getColumnIndexOrThrow(WEIGHT))

                val animal = Animal(id, name, url, weight, description, location, sex)
                animals.add(animal)

            }
        }

        cursor.close()
        return animals
    }

    fun updateAnimal(animal:Animal):Int{
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(ID,animal.id)
            put(NAME,animal.name)
            put(URL, animal.url)
            put(DESCRIPTION, animal.description)
            put(SEX, animal.sex)
            put(LOCATION, animal.location)
            put(WEIGHT, animal.weight)
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

    /*companion object{ //Se envian los datos en duro pero se pueden obtener de API o BD
        fun getFakeAnimals():ArrayList<Animal>{
            var list = arrayListOf<Animal>()

            list.add(Animal(1,"Perro", "https://img.freepik.com/vector-gratis/lindo-perro-sacando-lengua-ilustracion-icono-dibujos-animados_138676-2709.jpg", 2.34, "Mi perro lanudo", "EUROPA", "MACHO"))
            list.add(Animal(2,"Gato", "url", 2.45, "Mi gato flojo", "ASIA", "HEMBRA"))

            return list
        }
    }*/
}