package com.aumentarte.cursosti.view.fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aumentarte.cursosti.R
import com.aumentarte.cursosti.databinding.FragmentFirstBinding
import com.aumentarte.cursosti.viewModel.CursosAdapter
import com.aumentarte.cursosti.viewModel.CursosViewModel


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val viewModel: CursosViewModel by activityViewModels()

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CursosAdapter()
        binding.recyclerV.adapter = adapter
        binding.recyclerV.layoutManager = LinearLayoutManager(context)

        viewModel.getListaCursos().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("Listado", it.toString())
                adapter.actualizar(it)
            }
        })


        adapter.selectedCard().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("seleccion", it.id)

            }

            val bundle = Bundle().apply {
                putString("idCurso", it.id)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
