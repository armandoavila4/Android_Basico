package com.jaae.mvvmandroidexample.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jaae.mvvmandroidexample.R
import com.jaae.mvvmandroidexample.databinding.FragmentDetalleBinding
import com.jaae.mvvmandroidexample.databinding.UserItemBinding
import com.jaae.mvvmandroidexample.model.entities.User

class DetalleFragment : Fragment(R.layout.fragment_detalle) {

    private lateinit var binding: FragmentDetalleBinding
    private var user: User? = null
    private val args:DetalleFragmentArgs by navArgs()

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            user = it.getSerializable(KEY_USER) as User?
        }
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetalleBinding.bind(view)
        user = args.user
        binding.user = user

//        val name = requireActivity().getString(R.string.detail_name, user?.name)
//        val lastname = requireActivity().getString(R.string.detail_lastname, user?.lastname)
//        val age = requireActivity().getString(R.string.detail_age, user?.age.toString())
        val image = user?.image
//
//        binding.tvName.text = name
//        binding.tvLastname.text = lastname
//        binding.tvAge.text = age

        Glide.with(binding.root).load(image).placeholder(R.mipmap.ic_launcher_round).centerCrop().into(binding.ivUserDetalle)


    }

   /* companion object{
        private const val KEY_USER = "KEY_USER"

        fun newInstance(user:User) : DetalleFragment{
            return DetalleFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("KEY_USER", user)
                }
            }
        }
    }*/
}