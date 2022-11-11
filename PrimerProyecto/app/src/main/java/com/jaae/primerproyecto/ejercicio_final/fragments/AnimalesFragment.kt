package com.jaae.primerproyecto.ejercicio_final.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaae.primerproyecto.R

class AnimalesFragment : Fragment(), AnimalItemListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_animales, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as ListaAnimalesManagerActivity)
        val list = view.findViewById<RecyclerView>(R.id.list_animales)
        val animalAdapter = AnimalAdapter(getData(), this)

        list.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        list.itemAnimator = DefaultItemAnimator()
        list.adapter = animalAdapter

    }

    private fun getData() : ArrayList<AnimalItem>{
        val data = arrayListOf<AnimalItem>()
        data.add(AnimalItem("Leon", R.drawable.lion))
        data.add(AnimalItem("Tigre", R.drawable.tiger))
        data.add(AnimalItem("Pez", R.drawable.fish))
        data.add(AnimalItem("Zebra", R.drawable.zebra))
        data.add(AnimalItem("Aguila", R.drawable.eagle))
        data.add(AnimalItem("Araña", R.drawable.spider))
        data.add(AnimalItem("Oso Polar", R.drawable.bear))
        data.add(AnimalItem("Elefante", R.drawable.elephant))
        return data
    }

    companion object{
        fun newInstance(): AnimalesFragment {
            return AnimalesFragment().apply { //para agregar datos al fragment
                arguments = Bundle().apply {
                    //putString("ARG_NAME", name)
                }
            }
        }
    }

    override fun onItemSelected(animal: AnimalItem) {
        //(activity as ListaAnimalesManagerActivity)
        //Toast.makeText(activity, "Selected: ${animal.name}", Toast.LENGTH_SHORT).show()
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerAnimales, AnimalesDetalleFragment.newInstance(animal))
            .addToBackStack("AnimalesDetalleFragment")
            .commit()
    }

}