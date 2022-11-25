package com.jaae.ejerciciofinal.view.adapters

import android.app.ActivityManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaae.ejerciciofinal.R
import com.jaae.ejerciciofinal.databinding.AnimalItemViewBinding
import com.jaae.ejerciciofinal.databinding.ManagerAnimalesBinding
import com.jaae.ejerciciofinal.model.entities.Animal
import com.jaae.ejerciciofinal.view.fragments.AnimalesFragment

class AnimalAdapter(private val animales: ArrayList<Animal>): RecyclerView.Adapter<AnimalAdapter.animalViewHolder>() {

    var onItemClick: ((Animal) -> Unit)? = null

    class animalViewHolder(private val binding: AnimalItemViewBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(animal:Animal, onItemClick: ((Animal) -> Unit)?){
            binding.animal = animal

            //Se agrega la imagen
            animal?.url?.let{
                Glide.with(binding.root).load(animal.url).placeholder(R.drawable.ic_photo_base).centerCrop().into(binding.ivAnimal)
            }

            //Evento al tap de un animal
            binding.cardAnimal.setOnClickListener{
                //Toast.makeText(binding.cardAnimal.context,animal.name,Toast.LENGTH_SHORT).show()
                onItemClick?.invoke(animal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): animalViewHolder {
        val binding = AnimalItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return animalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: animalViewHolder, position: Int) {
        holder.bind(animales[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return animales.size
    }

    fun update(newAnimals : ArrayList<Animal>){ //funcion que agrega
        animales.clear()
        animales.addAll(newAnimals)
        notifyDataSetChanged() //Se ejecuta
    }

}