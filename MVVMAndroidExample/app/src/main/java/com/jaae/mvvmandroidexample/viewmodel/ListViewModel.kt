package com.jaae.mvvmandroidexample.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaae.mvvmandroidexample.model.entities.User
import com.jaae.mvvmandroidexample.model.repository.UserRepository

class ListViewModel : ViewModel() {
    //val listP = LiveData<ArrayList<User>>()
    var list = MutableLiveData<ArrayList<User>>() //Agrega observadores
    var loader = MutableLiveData<Boolean>()

    init {  //Se ejecuta cuando se crea el fragment o sea ina vez nada mas
        getUserList() //Se cargan los datos
    }

    fun getUserList(){
        loader.postValue(true)
        Handler(Looper.getMainLooper()).postDelayed({
            val users = UserRepository.getFakeUsers()
            list.postValue(users)
            loader.postValue(false)
        },5000)
    }
}