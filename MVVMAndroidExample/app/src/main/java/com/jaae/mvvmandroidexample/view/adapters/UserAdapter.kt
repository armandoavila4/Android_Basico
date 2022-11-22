package com.jaae.mvvmandroidexample.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaae.mvvmandroidexample.R
import com.jaae.mvvmandroidexample.databinding.UserItemBinding
import com.jaae.mvvmandroidexample.model.entities.User

/*La lista de elementos se recibe como parametro */
class UserAdapter(private val items : ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var onItemClick: ((User) -> Unit)? = null

    class UserViewHolder(/*Se pasa el binding*/ private val binding : UserItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: User, onItemClick: ((User) -> Unit)?){
            binding.user = user //Se setea el usuario a la variable del xml

//            binding.tvName.text = "Name: ${user.name}"
//            binding.tvLastname.text = "Lastname: ${user.lastname}"
//            binding.tvAge.text ="edad: ${user.age.toString()}"

            user?.image?.let{
                Glide.with(binding.root).load(user.image).placeholder(R.mipmap.ic_launcher_round).centerCrop().into(binding.ivUser)
            }

            binding.userCard.setOnClickListener{
                onItemClick?.invoke(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context),parent, false) //Conexion con la interfaz gráfica
        return UserViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       holder.bind(items[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems : ArrayList<User>){ //funcion que agrega
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged() //Se ejecuta
    }
}