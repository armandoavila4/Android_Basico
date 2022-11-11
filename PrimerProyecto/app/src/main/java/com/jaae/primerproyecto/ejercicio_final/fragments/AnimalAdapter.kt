package com.jaae.primerproyecto.ejercicio_final.fragments

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaae.primerproyecto.R


class AnimalAdapter(private val items: ArrayList<AnimalItem>, private val listener: AnimalItemListener) : RecyclerView.Adapter<AnimalAdapter.UserViewHolder>() {
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name : TextView
        val image : ImageView

        init{
            name = view.findViewById(R.id.txtCardNameAnimal)
            image = view.findViewById(R.id.ivAnimalPicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_animal, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.image.setImageResource(items[position].image)
        holder.itemView.setOnClickListener{
            listener.onItemSelected(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}