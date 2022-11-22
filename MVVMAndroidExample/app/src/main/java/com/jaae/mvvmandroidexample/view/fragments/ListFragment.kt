package com.jaae.mvvmandroidexample.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaae.mvvmandroidexample.R
import com.jaae.mvvmandroidexample.databinding.FragmentListBinding
import com.jaae.mvvmandroidexample.view.adapters.UserAdapter
import com.jaae.mvvmandroidexample.viewmodel.ListViewModel

class ListFragment : Fragment(R.layout.fragment_list) {
    private val userViewModel : ListViewModel by viewModels() //Obtiene el model que extienda de ListViewModel
    private lateinit var binding: FragmentListBinding
    private lateinit var userAdapter : UserAdapter

    //Este método se utiliza cuando se usa el binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        userAdapter = UserAdapter(arrayListOf()) //Se inicializa con un arreglo vacio

        binding.refresh.setOnRefreshListener { //Metodo para detectar el refresh de la lista
            userViewModel.getUserList()
            binding.refresh.isRefreshing = false
        }

        userAdapter.onItemClick = {
            val action = ListFragmentDirections.actionListFragmentToDetalleFragment(it)
            findNavController().navigate(action)

            //Se cara el nuevo fragment
            /*parentFragmentManager.beginTransaction()
                .replace(R.id.containerList, DetalleFragment.newInstance(it))
                .addToBackStack("DetalleFragment")
                .commit()

            parentFragmentManager.commit {
                replace(R.id.containerList,DetalleFragment.newInstance(it))
                addToBackStack("DetalleFragment")
            }*/

        }

        binding.userList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.userList.adapter = userAdapter

        //userViewModel.getUserList() //Se cargan los datos

        userViewModel.list.observe(viewLifecycleOwner, Observer{
            userAdapter.updateItems(it)
        })//Va a observar la lista para que en cuanto haya cambio se avise

        userViewModel.loader.observe(viewLifecycleOwner, Observer{
            binding.loader.visibility = if(it == true) View.VISIBLE else View.GONE //Muestra u oculta el progress bar
            binding.userList.visibility = if(it == true) View.GONE else View.VISIBLE //Muestra u oculta la lista de usuarios
        })
    }
}