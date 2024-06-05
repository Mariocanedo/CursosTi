package com.aumentarte.cursosti.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.aumentarte.cursosti.databinding.DisenoCardBinding
import com.aumentarte.cursosti.model.local.entidades.ListaCursosLocal
import com.bumptech.glide.Glide


class CursosAdapter : RecyclerView.Adapter<CursosAdapter.CardViewHolder>() {

    private var listaAdapter = listOf<ListaCursosLocal>()
    private val selectList = MutableLiveData<ListaCursosLocal>()

    fun actualizar(list: List<ListaCursosLocal>) {
        listaAdapter = list
        notifyDataSetChanged()
    }


    fun selectedCard(): LiveData<ListaCursosLocal> = selectList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(DisenoCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listaAdapter.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val lista = listaAdapter[position]
        holder.bin(lista)
    }


    inner class CardViewHolder(private val binding: DisenoCardBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bin(listaCuros: ListaCursosLocal) {

            Glide.with(binding.Imagencard).load(listaCuros.image).centerCrop().into(binding.Imagencard)
            binding.tvtitle.text = listaCuros.title
            binding.tvdescripcion.text = listaCuros.description
            binding.tvweeks.text = listaCuros.weeks.toString()
            binding.tvstart.text = listaCuros.start
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectList.value = listaAdapter[bindingAdapterPosition] //Recycler

        }
    }
}