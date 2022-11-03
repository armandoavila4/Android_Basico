package com.jaae.primerproyecto

import java.io.Serializable
import java.util.Date

data class Persona (
    val name : String,
    val paterno :String,
    val materno :String,
    val age :Int,
    val phone :Number,
    val password :String
):Serializable
