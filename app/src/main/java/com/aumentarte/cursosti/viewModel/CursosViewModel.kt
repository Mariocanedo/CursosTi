package com.aumentarte.cursosti.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aumentarte.cursosti.model.local.CursosBaseDatos
import com.aumentarte.cursosti.model.local.entidades.DetalleCursosLocal
import com.aumentarte.cursosti.model.local.entidades.ListaCursosLocal
import com.aumentarte.cursosti.model.repomapper.Repositorio
import kotlinx.coroutines.launch

class CursosViewModel(aplicacion: Application) : AndroidViewModel(aplicacion) {

    private val repotitory: Repositorio
    private val detalleLiveData = MutableLiveData<DetalleCursosLocal>()
    private var seleccionado: String = "-1"

    init {
        val bd = CursosBaseDatos.getDataBase(aplicacion)
        val dao = bd.getCursosDao()
        repotitory = Repositorio(dao)
        viewModelScope.launch {
            repotitory.fetchListCursos()
        }
    }

    fun getListaCursos(): LiveData<List<ListaCursosLocal>> = repotitory.cursosLiveData

    fun getDetalleCursos() :LiveData<DetalleCursosLocal> = detalleLiveData


    fun getDetalleInter(id: String) = viewModelScope.launch {
        val detalleCurso = repotitory.fetchDetalle(id)
        detalleCurso?.let {
            detalleLiveData.postValue(it)
        }
    }

}