package com.jaae.primerproyecto.ejercicios_clase.layouts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaae.primerproyecto.R

class UserAdapter(private val items: ArrayList<UserItem>, private val listener: RecyclerItemListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name : TextView
        val image : ImageView

        init{
            name = view.findViewById(R.id.txtCardName)
            image = view.findViewById(R.id.ivLogo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.itemView.setOnClickListener{
            listener.onItemSelected(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}