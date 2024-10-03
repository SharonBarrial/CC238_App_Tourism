package com.example.upc_pre_202401_cc238_ea.communication

import com.example.upc_pre_202401_cc238_ea.model.Package

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */
class UserResponse (
    private var product: String,
    private var nombre: String,
    private var descripcion: String,
    private var ubicacin: String,
    private var picture: UserPictureApiResponse
) {
    fun toPackage(): Package {
        return Package(
            idProducto = product,
            nombre = nombre,
            descripcion = descripcion,
            ubicacin = ubicacin,
            imagen = picture.large
        )
    }
}

data class UserPictureApiResponse (
    var large: String,
    var medium: String,
    var thumbnail: String
)


