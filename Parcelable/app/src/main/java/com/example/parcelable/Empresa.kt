package com.example.parcelable

import java.util.*
import kotlin.collections.ArrayList

class Empresa( var id: Int,
               var nombre:String,
               var createdAt: Long,
               var updatedAt: Long,
               var usuariosDeEmpresas: ArrayList<UsuarioEmpresa>) {
    var fechaCreacion: Date
    var fechaActualizacion: Date
    init{
        fechaCreacion=Date(createdAt)
        fechaActualizacion=Date(updatedAt)
    }
}