package com.jaae.primerproyecto.ejercicios_clase.almacenamiento

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TBL_USER = "tbl_user"
        private const val ID = "id"
        private const val NAME = "name"
        private const val DESCRIPTION = "description"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlCreate = "CREATE TABLE $TBL_USER ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAME TEXT, $DESCRIPTION TEXT)"
        db?.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sqlUpdate = "DROP TABLE IF EXISTS $TBL_USER"
        db?.execSQL(sqlUpdate)
        //TODO obtener info
        onCreate(db)
    }

    fun insert(user:UserSqlModel):Long{
        val db = writableDatabase
        val contentValues = ContentValues().apply{
            put(NAME, user.name)
            put(DESCRIPTION, user.description)
        }

        val result = db.insert(TBL_USER, null, contentValues)
        return result
    }


    fun getAllUsers(): ArrayList<UserSqlModel>{
        val userList = arrayListOf<UserSqlModel>()
        val query = "SELECT * FROM $TBL_USER"
        val db = readableDatabase
        val cursor : Cursor?

        try {
            cursor = db.rawQuery(query, null)
        }catch (e:Exception){
            e.printStackTrace()
            return userList
        }


        var id : Int
        var name : String
        var descripcion: String

        with(cursor){
            while (moveToNext()){
                id = getInt(getColumnIndexOrThrow(ID))
                name = getString(getColumnIndexOrThrow(NAME))
                descripcion = getString(getColumnIndexOrThrow(DESCRIPTION))

                val user = UserSqlModel(id, name, descripcion)

                userList.add(user)
            }
        }

        /* //En caso de no utilizar with
          id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
          name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
          descripcion = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION))
          */

        cursor.close()

        return userList
    }

    fun updateUser(user : UserSqlModel): Int{
        val db = writableDatabase
        val contentValues = ContentValues().apply{
            put(ID, user.id)
            put(NAME, user.name)
            put(DESCRIPTION, user.description)
        }

        //val result = db.update(TBL_USER, contentValues, "$ID = ${user.id}",null)
        val result = db.update(TBL_USER, contentValues, "$ID = ?", arrayOf("${user.id}"))

        db.close()
       return result
    }


    fun deleteUser(userId:Int): Int{
        val db = writableDatabase
        val result = db.delete(TBL_USER, "id=?", arrayOf("$userId"))
        db.close()
        return result
    }

}