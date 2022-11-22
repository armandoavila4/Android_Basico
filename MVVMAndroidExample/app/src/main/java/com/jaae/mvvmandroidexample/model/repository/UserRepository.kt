package com.jaae.mvvmandroidexample.model.repository

import com.jaae.mvvmandroidexample.model.entities.User

class UserRepository {
    companion object{ //Se envian los datos en duro pero se pueden obtener de API o BD
        fun getFakeUsers():ArrayList<User>{
            var list = arrayListOf<User>()

            list.add(User("Armando", "Avila", 34, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRViCck0-66zUhbxsjl0iMB_LS6YiH31_PyWA&usqp=CAU"))
            list.add(User("Maria", "Perez", 34, "https://static2.elcorreo.com/www/multimedia/202110/20/media/cortadas/aguila-krhD-U150893779467aMB-624x521@RC.JPG"))
            list.add(User("Pedro", "Torres", 34, "https://es.wikipedia.org/wiki/%C3%81guila#/media/Archivo:Steinadler_Aquila_chrysaetos_closeup1_Richard_Bartz.jpg"))
            list.add(User("Juana", "Hernandez", 34, "https://es.wikipedia.org/wiki/%C3%81guila#/media/Archivo:Steinadler_Aquila_chrysaetos_closeup1_Richard_Bartz.jpg"))
            list.add(User("Fabián", "Capa", 34, ""))

            return list
        }
    }
}