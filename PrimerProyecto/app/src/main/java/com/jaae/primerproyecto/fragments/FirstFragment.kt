package com.jaae.primerproyecto.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jaae.primerproyecto.R
import com.jaae.primerproyecto.fragments.FirstFragment.Companion.newInstance

class FirstFragment : Fragment() {

    var name : String? = null

    companion object{
        fun newInstance(name: String): FirstFragment{
            return FirstFragment().apply { //para agregar datos al fragment
                arguments = Bundle().apply {
                 putString("ARG_NAME", name)
                }
            }
        }
    }

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
        (activity as FragmentManagerActivity).name
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val txNext = view.findViewById<TextView>(R.id.tvNext)

        txNext.text = name
        txNext.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SecondFragment.newInstance(name+"dos" ?: "Default"))
                .addToBackStack("SecondFragment")
                .commit()
        }

        return view
    }

}