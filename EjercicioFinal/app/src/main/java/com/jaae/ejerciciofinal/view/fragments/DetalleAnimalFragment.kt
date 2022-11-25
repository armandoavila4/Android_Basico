package com.jaae.ejerciciofinal.view.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jaae.ejerciciofinal.R
import com.jaae.ejerciciofinal.databinding.FragmentDetalleAnimalBinding
import com.jaae.ejerciciofinal.model.entities.Animal
import com.jaae.ejerciciofinal.model.repository.AnimalDB
import com.jaae.ejerciciofinal.viewmodel.AnimalViewModel

class DetalleAnimalFragment : Fragment(R.layout.fragment_detalle_animal) {

    private lateinit var binding: FragmentDetalleAnimalBinding
    private var animal: Animal? = null
    private val args: DetalleAnimalFragmentArgs by navArgs()
    private val animalViewModel: AnimalViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetalleAnimalBinding.bind(view)
        animal = args.animal
        var idAnimal = 0

        //Seteo valores de spinner
        val locations = arrayListOf("", "AMERICA", "EUROPA", "ASIA", "AFRICA", "OCEANIA")
        val adaptadorLoc =
            ArrayAdapter(view.context, android.R.layout.simple_spinner_item, locations)

        adaptadorLoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) //Asigna más espacio entre los alementos de la lista
        binding.spinnerLocation.adapter = adaptadorLoc

        //Se verifica si existe el animal
        if (animal == null) {
            //Solo se muestra el boton de guardar
            binding.btAddAnimalDetalle.visibility = View.VISIBLE
            binding.btDeleteAnimal.visibility = View.GONE
            binding.btEditAnimal.visibility = View.GONE

        } else {
            //Editar
            binding.animal = animal
            idAnimal = animal!!.id
            //Set Image
            val image = animal?.url
            Glide.with(binding.root).load(image).placeholder(R.drawable.ic_photo_base).centerCrop()
                .into(binding.ivImageDetalle)

            //Set Location
            var location = locations.indexOf(animal!!.location)
            binding.spinnerLocation.setSelection(location)

            //Set Sex
            when (animal!!.sex) {
                "HEMBRA" -> {
                    binding.rgSex.check(R.id.radHembra)
                    true
                }
                "MACHO" -> {
                    binding.rgSex.check(R.id.radMacho)
                    true
                }
                else -> false
            }

            //Solo se muestra el boton de guardar
            binding.btAddAnimalDetalle.visibility = View.GONE
            binding.btEditAnimal.visibility = View.VISIBLE
            binding.btDeleteAnimal.visibility = View.VISIBLE
        }

        //Validaciones de campos
        binding.btAddAnimalDetalle.setOnClickListener {
            if (!validaCampos()) {
                val animalObj = creaAnimal()
                val res = animalViewModel.saveAnimal(animalObj, view.context)
                if (res != null) {
                    if (res > 0) {
                        Toast.makeText(this.context, "Información Guardada", Toast.LENGTH_LONG)
                            .show()
                        findNavController().navigate(DetalleAnimalFragmentDirections.actionDetalleAnimalFragmentToAnimalesFragment())
                    } else {
                        Toast.makeText(this.context, "Error al almacenar la información", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        binding.btEditAnimal.setOnClickListener {
            if (!validaCampos()) {
                //Se guarda en la BD
                val animalObj = creaAnimal(idAnimal)
                val res = animalViewModel.updateAnimal(animalObj, view.context)
                if (res != null) {
                    if (res > 0) {
                        Toast.makeText(this.context, "Información Actualizada", Toast.LENGTH_LONG)
                            .show()
                        findNavController().navigate(DetalleAnimalFragmentDirections.actionDetalleAnimalFragmentToAnimalesFragment())
                    } else {
                        Toast.makeText(this.context, "Error al actualizar la información", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        binding.btDeleteAnimal.setOnClickListener {
            val res = animalViewModel.deleteAnimal(idAnimal, view.context)
            if (res != null) {
                if (res > 0) {
                    Toast.makeText(this.context, "Animal eliminado", Toast.LENGTH_LONG)
                        .show()
                    findNavController().navigate(DetalleAnimalFragmentDirections.actionDetalleAnimalFragmentToAnimalesFragment())
                } else {
                    Toast.makeText(this.context, "Error al eliminar la información", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun creaAnimal(id:Int = 0):Animal {
        var selectedSex: String = when (binding.rgSex.checkedRadioButtonId) {
            binding.radMacho.id -> "MACHO"
            binding.radHembra.id -> "HEMBRA"
            else -> ""
        }

        var id = id
        var name = binding.etNameDetalle.text.toString()
        var description = binding.etDescriptionDetalle.text.toString()
        var url = binding.etUrlDetalle.text.toString()
        var location = binding.spinnerLocation.selectedItem.toString()
        var sex = selectedSex
        var weight = binding.etWeightDetalle.text.toString().toDouble()

        return Animal(id = id,
            name = name, url = url, description = description,
            sex = sex, weight = weight, location = location
        )
    }

    private fun alertEmpty(element: String, context: Context) {
        Toast.makeText(context, "${element} está vacío", Toast.LENGTH_SHORT).show()
    }

    fun validaCampos(): Boolean {
        var error = false
        if (binding.etNameDetalle.text.isEmpty()) {
            alertEmpty("Nombre", binding.root.context)
            error = true
        } else if (binding.etDescriptionDetalle.text.isEmpty()) {
            alertEmpty("Descripción", binding.root.context)
            error = true
        } else if (binding.etUrlDetalle.text.isEmpty()) {
            alertEmpty("URL", binding.root.context)
            error = true
        } else if (binding.etWeightDetalle.text?.toString()?.toDouble()!! <= 0) {
            alertEmpty("Peso", binding.root.context)
            error = true
        } else if (binding.spinnerLocation.selectedItemId <= 0) {
            alertEmpty("Continente", binding.root.context)
            error = true
        } else if (binding.rgSex.checkedRadioButtonId <= 0) {
            alertEmpty("Sexo", binding.root.context)
            error = true
        }
        return error
    }
}