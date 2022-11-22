package com.jaae.primerproyecto.ejercicio_final.almacenamiento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaae.primerproyecto.R

class AnimalSqlAdapter(private val animales : ArrayList<AnimalSqlItem>, private val listener : AnimalSqlListener):RecyclerView.Adapter<AnimalSqlAdapter.AnimalSqlViewHolder>(){

    class AnimalSqlViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val id: TextView
        val name: TextView
        val image: ImageView
        val url: TextView

        init {
            name = view.findViewById(R.id.txtCardNameAnimal)
            id = view.findViewById(R.id.txtIdAnimal)
            url = view.findViewById(R.id.txtUrlImage)
            image = view.findViewById(R.id.ivAnimalPictureSql)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalSqlViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_animal_sql, parent, false)
        return AnimalSqlViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalSqlViewHolder, position: Int) {
        holder.name.text = animales[position].name
        holder.id.text = animales[position].id.toString()
        holder.url.text = animales[position].url

        Glide.with(holder.image.context).load(animales[position].url).placeholder(R.mipmap.ic_launcher_round).centerCrop().into(holder.image)

        holder.itemView.setOnClickListener{
            listener.onItemSelected(animales[position])
        }
    }

    override fun getItemCount(): Int {
        return animales.size
    }

    fun updateItems(newAnimales:ArrayList<AnimalSqlItem>){
        animales.clear()
        animales.addAll(newAnimales)
        notifyDataSetChanged()
    }
}