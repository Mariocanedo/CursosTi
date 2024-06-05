package com.aumentarte.cursosti.model.repomapper

import com.aumentarte.cursosti.model.local.entidades.DetalleCursosLocal
import com.aumentarte.cursosti.model.local.entidades.ListaCursosLocal
import com.aumentarte.cursosti.model.remoto.internet.DetalleCursosInternet
import com.aumentarte.cursosti.model.remoto.internet.ListaCursosInternet

fun fromInterLista (lista: List<ListaCursosInternet>): List<ListaCursosLocal> {
    return lista.map {
        ListaCursosLocal(

            id = it.id,
            title = it.title,
            description = it.description,
            image = it.image,
            weeks = it.weeks,
            start = it.start

        )
    }
}

fun fromInterDetalle(detail: DetalleCursosInternet): DetalleCursosLocal {
    return DetalleCursosLocal(
        id = detail.id,
        title = detail.title,
        description = detail.description,
        image = detail.image,
        weeks = detail.weeks,
        start = detail.start,
        tuition = detail.tuition,
        minimumSkill = detail.minimumSkill,
        scholarshipsAvailabl = detail.scholarshipsAvailabl,
        modality = detail.modality
    )
}