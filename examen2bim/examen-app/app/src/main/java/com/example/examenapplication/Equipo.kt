package com.example.examenapplication

import android.os.Parcel
import android.os.Parcelable

class Equipo(var id:Int,
             var nombre: String,
             var liga: String,
             var fechaCreacion: String,
             var numeroCopasInternacionales: Int,
             var campeonActual: Boolean): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(liga)
        parcel.writeString(fechaCreacion)
        parcel.writeInt(numeroCopasInternacionales)
        parcel.writeByte(if (campeonActual) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Equipo> {
        override fun createFromParcel(parcel: Parcel): Equipo {
            return Equipo(parcel)
        }

        override fun newArray(size: Int): Array<Equipo?> {
            return arrayOfNulls(size)
        }
    }

}

