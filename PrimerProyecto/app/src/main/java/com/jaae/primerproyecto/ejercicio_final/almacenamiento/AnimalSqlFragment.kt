package com.jaae.primerproyecto.ejercicio_final.almacenamiento

import android.content.Intent
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

class AnimalSqlFragment : Fragment(), AnimalSqlListener{

    lateinit var animalSqlAdapter: AnimalSqlAdapter
    lateinit var animalSqlHelper: AnimalSqlHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_animal_sql, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animalSqlHelper = AnimalSqlHelper(view.context)
        val list = view.findViewById<RecyclerView>(R.id.rvAnimales)
        animalSqlAdapter = AnimalSqlAdapter(animalSqlHelper.getAllAnimals(), this)

        list.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        list.itemAnimator = DefaultItemAnimator()

        list.adapter = animalSqlAdapter

    }

    private fun getData(): ArrayList<AnimalSqlItem> {
        val animales = arrayListOf<AnimalSqlItem>()
        animales.add(AnimalSqlItem(1,"Ajolote", "https://cdn-icons-png.flaticon.com/512/6420/6420762.png"))
        animales.add(AnimalSqlItem(2,"Leon", "https://img.lovepik.com/element/45000/3742.png_860.png"))
        return animales
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AnimalSqlFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onItemSelected(animal: AnimalSqlItem) {
        (activity as AnimalesSqlActivity)
        //Toast.makeText(activity, "Seleccionado: ${animal.name}", Toast.LENGTH_SHORT).show()
        //Se envian los datos para editar o eliminar
        val intent = Intent(activity, DetalleAnimalSqlActivity::class.java).apply {
            //Toast.makeText(this, "Agregar", Toast.LENGTH_SHORT).show()
            putExtra("KEY_ACTION", "EDIR")
            putExtra("KEY_ANIMAL", animal)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        animalSqlAdapter.updateItems(animalSqlHelper.getAllAnimals())
    }
}