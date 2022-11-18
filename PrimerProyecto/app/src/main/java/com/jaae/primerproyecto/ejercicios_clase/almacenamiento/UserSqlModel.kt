package com.jaae.primerproyecto.ejercicios_clase.almacenamiento

import java.io.Serializable

data class UserSqlModel (
    val id : Int = 0,
    val name : String = "",
    val description : String = ""
): Serializable