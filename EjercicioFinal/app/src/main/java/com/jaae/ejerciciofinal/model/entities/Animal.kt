package com.jaae.ejerciciofinal.model.entities
import java.io.Serializable
import java.time.LocalDate

data class Animal(
    val id: Int = 0,
    val name: String = "",
    val url: String = "",
    val weight: Double = 0.00,
    val description: String = "",
    val location: String = "",
    val sex: String = "",
    //val birth: LocalDate = LocalDate.now()

):Serializable