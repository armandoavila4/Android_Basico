package com.jaae.primerproyecto.ejercicios_clase.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaae.primerproyecto.R

class RecyclerViewActivity : AppCompatActivity(), RecyclerItemListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val list = findViewById<RecyclerView>(R.id.list)
        val userAdapter = UserAdapter(getData(), this)

        list.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL, false)
        //list.layoutManager = GridLayoutManager(this, 2)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list.itemAnimator = DefaultItemAnimator()
        list.adapter = userAdapter
    }

    private fun getData() : ArrayList<UserItem>{
        val data = arrayListOf<UserItem>()
        data.add(UserItem("Armando", ""))
        data.add(UserItem("Pablito", ""))
        data.add(UserItem("Federico", ""))
        data.add(UserItem("José", ""))
        data.add(UserItem("Ramon", ""))
        data.add(UserItem("Ramon2", ""))
        data.add(UserItem("Ramon3", ""))
        data.add(UserItem("Ramon4", ""))
        data.add(UserItem("Ramon5", ""))

        return data
    }

    override fun onItemSelected(user: UserItem) {
        Toast.makeText(this, "Selected: ${user.name}", Toast.LENGTH_SHORT).show()
    }
}