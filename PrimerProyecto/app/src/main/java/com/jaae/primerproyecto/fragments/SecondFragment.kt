package com.jaae.primerproyecto.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jaae.primerproyecto.R

class SecondFragment : Fragment() {

    var name : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            name = it.getString("ARG_NAME")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val txNextDos = view.findViewById<TextView>(R.id.tvNextDos)

        txNextDos.text = name

        return view
    }

    companion object{
        fun newInstance(name: String): SecondFragment{
            return SecondFragment().apply { //para agregar datos al fragment
                arguments = Bundle().apply {
                    putString("ARG_NAME", name)
                }
            }
        }
    }

}