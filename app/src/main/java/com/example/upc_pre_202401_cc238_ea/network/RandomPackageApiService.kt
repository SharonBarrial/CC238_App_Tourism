package com.example.upc_pre_202401_cc238_ea.network

import com.example.upc_pre_202401_cc238_ea.model.Package
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */
interface RandomPackageApiService {
    @GET("productossitiotipo.php")
    fun getPackages(
        @Query("sitio") sitio: String,
        @Query("tipo") tipo: String
    ): Call<List<Package>>
}