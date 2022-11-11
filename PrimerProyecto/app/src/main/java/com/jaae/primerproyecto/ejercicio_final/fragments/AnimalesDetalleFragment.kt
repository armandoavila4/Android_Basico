package com.jaae.primerproyecto.ejercicio_final.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.jaae.primerproyecto.R


class AnimalesDetalleFragment : Fragment() {
    var animal: AnimalItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            animal = it.getSerializable("KEY_ANIMAL") as AnimalItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as ListaAnimalesManagerActivity)
        val view = inflater.inflate(R.layout.fragment_animales_detalle, container, false)
        val txtDetAnimalName = view.findViewById<TextView>(R.id.txtDetAnimalName)
        val ivDetAnimalImage = view.findViewById<ImageView>(R.id.ivDetAnimalImage)
        txtDetAnimalName.text = animal?.name
        animal?.let { ivDetAnimalImage.setImageResource(it.image) }

        val chkEnfermedad = view.findViewById<CheckBox>(R.id.chkEnfermedad)
        val btSendAnimal = view.findViewById<Button>(R.id.btSendAnimal)
        val sexoAnimal = view.findViewById<RadioGroup>(R.id.rgSexAnimal)

        val spinnerHabitat = view.findViewById<Spinner>(R.id.spinnerHabitat)
        val habitatLista = arrayListOf("Agua", "Tierra", "Aire")
        val adaptador = ArrayAdapter(activity as ListaAnimalesManagerActivity, android.R.layout.simple_spinner_item, habitatLista)

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) //Asigna más espacio entre los alementos de la lista
        spinnerHabitat.adapter = adaptador

        btSendAnimal.setOnClickListener{
            var enfermedad: String? = null
            var habitat: String? = null

            if(chkEnfermedad.isChecked){
                enfermedad = "SI"
            }else{
                enfermedad = "NO"
            }

            var sexoAnimal = when(sexoAnimal.checkedRadioButtonId){
                R.id.radMacho -> "Macho"
                R.id.radHembra -> "Hambra"
                else -> "Opción no definida"
            }

            habitat = spinnerHabitat.getSelectedItem().toString()

            if(enfermedad.isEmpty()){
                this.avisaVacio("Enfermedad")
            }else if(sexoAnimal == "Opción no definida") {
                this.avisaVacio("Sexo")
            }else{
                val intent = Intent(activity, DetalleAnimalesActivity::class.java).apply {
                    //val animal = (name, paterno, materno, age.toInt(), phone.toLong(), password)
                    putExtra("KEY_ENFERMEDAD", enfermedad)
                    putExtra("KEY_SEXO", sexoAnimal)
                    putExtra("KEY_HABITAT", habitat)
                }
                startActivity(intent)
            }
        }


        return view
    }

    private fun avisaVacio(animal : String) {
        Toast.makeText(activity, "$animal vacio", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(animal: AnimalItem) =
            AnimalesDetalleFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("KEY_ANIMAL", animal)
                }
            }
    }
}