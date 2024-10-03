package com.example.upc_pre_202401_cc238_ea.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Aqui tiene que estar igual que en la API
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

@Entity
data class Package (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "idProducto")
    val idProducto: String?,

    @ColumnInfo(name = "nombre")
    val nombre: String?,

    @ColumnInfo(name = "descripcion")
    val descripcion: String?,

    @ColumnInfo(name = "ubicacin")
    val ubicacin: String?,

    @ColumnInfo(name = "imagen")
    val imagen: String?,
)