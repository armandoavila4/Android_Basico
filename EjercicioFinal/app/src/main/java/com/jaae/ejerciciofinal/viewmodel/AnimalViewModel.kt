package com.jaae.ejerciciofinal.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaae.ejerciciofinal.model.entities.Animal
import com.jaae.ejerciciofinal.model.repository.AnimalDB
import com.jaae.ejerciciofinal.view.fragments.AnimalesFragment

class AnimalViewModel: ViewModel() {
    var animales = MutableLiveData<ArrayList<Animal>?>() //Agrega observadores
    var animalDB: AnimalDB? = AnimalesFragment().context?.let { AnimalDB(it) }
    var loader = MutableLiveData<Boolean>()

    fun getAnimalesList(context: Context){
        val animalDB = AnimalDB(context)
        loader.postValue(true)
        Handler(Looper.getMainLooper()).postDelayed({
            val animals = animalDB.getAllAnimals()
            animales.postValue(animals)
            loader.postValue(false)
        },5000)
    }

    fun saveAnimal(animal:Animal, context: Context):Long{
        val animalDB = AnimalDB(context)
        val res = animalDB.insertAnimal(animal)
        return res
    }

    fun updateAnimal(animal: Animal, context: Context): Int {
        val animalDB = AnimalDB(context)
        val res = animalDB.updateAnimal(animal)
        return res
    }

    fun deleteAnimal(idAnimal: Int, context: Context): Int {
        val animalDB = AnimalDB(context)
        val res = animalDB.deleteAnimal(idAnimal)
        return res
    }
}