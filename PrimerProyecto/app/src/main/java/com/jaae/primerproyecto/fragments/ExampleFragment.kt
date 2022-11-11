package com.jaae.primerproyecto.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jaae.primerproyecto.R

class ExampleFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("FragmentExample", "Estoy en onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example, container, false)
    }

    override fun onStart() {
        super.onStart()
        Log.e("FragmentExample", "Estoy en onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("FragmentExample", "Estoy en onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("FragmentExample", "Estoy en onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("FragmentExample", "Estoy en onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("FragmentExample", "Estoy en onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("FragmentExample", "Estoy en onDetach")
    }
}