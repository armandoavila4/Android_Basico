package com.jaae.primerproyecto.ejercicio_final.almacenamiento

import java.io.Serializable

data class AnimalSqlItem(
    val id : Int = 0,
    val name : String = "",
    val url : String = ""
): Serializable
