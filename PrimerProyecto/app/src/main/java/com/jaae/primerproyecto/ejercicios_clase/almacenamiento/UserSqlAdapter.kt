package com.jaae.primerproyecto.ejercicios_clase.almacenamiento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaae.primerproyecto.R

class UserSqlAdapter(private val allUsers: ArrayList<UserSqlModel>): RecyclerView.Adapter<UserSqlAdapter.UserSqlViewHolder>(){

    class UserSqlViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name : TextView

        init {
            name = view.findViewById(R.id.txtSqlName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSqlViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_user_sql,parent, false)
        return UserSqlViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserSqlViewHolder, position: Int) {
        holder.name.text = allUsers[position].name
    }

    override fun getItemCount(): Int {
        return allUsers.size
    }

    fun updateItems(newItems : ArrayList<UserSqlModel>){
        allUsers.clear()
        allUsers.addAll(newItems)
        notifyDataSetChanged()
    }
}