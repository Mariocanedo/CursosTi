package com.aumentarte.cursosti.view.fragmentos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.aumentarte.cursosti.R
import com.aumentarte.cursosti.databinding.FragmentSecondBinding
import com.aumentarte.cursosti.viewModel.CursosViewModel
import com.bumptech.glide.Glide


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CursosViewModel by activityViewModels()
    private var cursoId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            cursoId = bundle.getString("idCurso")
            Log.d("SELECCION", cursoId.toString())

        }

        cursoId.let { id ->
            viewModel.getDetalleInter(id.toString())
        }

        viewModel.getDetalleCursos().observe(viewLifecycleOwner, Observer {

            var curso = it.title
            var descrip = it.description

            Glide.with(binding.ivLogo).load(it.image).into(binding.ivLogo)
            binding.tvTitle.text = "Nombre: ${it.title}"
            binding.tvDescription.text = "Descripcion: ${it.description}"
            binding.tvMinimumSkill.text = "Conocimiento minimo : ${it.minimumSkill}"
            binding.tvModality.text = "Modalidad : ${it.modality}"
            binding.tvTuition.text = "Valor del curso : ${it.tuition}"
            binding.tvDuration.text = "Duracion del curso : ${it.weeks} "
            binding.tvStart.text ="Inicio de clases : ${it.start}"

            binding.buttonSecond.setOnClickListener {

                val intent = Intent(Intent.ACTION_SEND)

                intent.data = Uri.parse("mailto")
                intent.type = "text/plain"

                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("admisión@centrofuturo.cl"))
                intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por curso" + curso
                )
                intent.putExtra(
                    Intent.EXTRA_TEXT, "Hola Vi el producto " + descrip + " y me " +
                            "gustaría que me contactaran con ustedes, Quedo atento."
                )

                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                }

            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}