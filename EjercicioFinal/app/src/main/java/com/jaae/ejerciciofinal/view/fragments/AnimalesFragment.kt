package com.jaae.ejerciciofinal.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaae.ejerciciofinal.R
import com.jaae.ejerciciofinal.databinding.FragmentAnimalesBinding
import com.jaae.ejerciciofinal.model.repository.AnimalDB
import com.jaae.ejerciciofinal.view.adapters.AnimalAdapter
import com.jaae.ejerciciofinal.viewmodel.AnimalViewModel

class AnimalesFragment : Fragment(R.layout.fragment_animales) {
    private val animalViewModel : AnimalViewModel by viewModels() //Obtiene el model que extienda de ListViewModel
    private lateinit var binding: FragmentAnimalesBinding
    private lateinit var animalAdapter : AnimalAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAnimalesBinding.bind(view)
        animalAdapter = AnimalAdapter(arrayListOf())
        animalViewModel.getAnimalesList(view.context)

        binding.refresh.setOnRefreshListener { //Metodo para detectar el refresh de la lista
            animalViewModel.getAnimalesList(view.context)
            binding.refresh.isRefreshing = false
        }


        //Sea grega el evento de cada item
        animalAdapter.onItemClick = {
            val action = AnimalesFragmentDirections.actionAnimalesFragmentToDetalleAnimalFragment(it)
            findNavController().navigate(action)
        }

        binding.btnAddAnimal.setOnClickListener {
            val action = AnimalesFragmentDirections.actionAnimalesFragmentToDetalleAnimalFragment()
            findNavController().navigate(action)
        }

        binding.rvListaAnimales.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvListaAnimales.adapter = animalAdapter

        animalViewModel.animales.observe(viewLifecycleOwner, Observer{
            if (it != null) {
                animalAdapter.update(it)
            }
        })

        animalViewModel.loader.observe(viewLifecycleOwner, Observer{
            binding.loader.visibility = if(it == true) View.VISIBLE else View.GONE //Muestra u oculta el progress bar
            binding.rvListaAnimales .visibility = if(it == true) View.GONE else View.VISIBLE //Muestra u oculta la lista de usuarios
        })

    }
}