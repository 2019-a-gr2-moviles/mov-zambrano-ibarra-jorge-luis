package com.example.jorge.examen

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import android.provider.Settings.System.getString
import android.support.v4.content.res.TypedArrayUtils.getText



class EquipoFutbol(

    var id:Int?,
    var nombre:String,
    var liga:String,
    var fechaCreacion:String,
    var numeroCopasInternacionales:Int, var campeonActual: Byte
) :Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte()
    ) {
    }


    override fun toString(): String {
        return "${nombre}";
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombre)
        parcel.writeString(liga)
        parcel.writeString(fechaCreacion)
        parcel.writeInt(numeroCopasInternacionales)
        parcel.writeByte(campeonActual)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EquipoFutbol> {
        override fun createFromParcel(parcel: Parcel): EquipoFutbol {
            return EquipoFutbol(parcel)
        }

        override fun newArray(size: Int): Array<EquipoFutbol?> {
            return arrayOfNulls(size)
        }
    }
}