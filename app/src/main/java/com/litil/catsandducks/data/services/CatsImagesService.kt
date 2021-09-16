package com.litil.catsandducks.data.services

import com.litil.catsandducks.domain.models.CatImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CatsImagesService {

    @GET("{id}")
    fun download(@Path("id") id: Int): Single<CatImageResponse>

    @GET(".")
    fun download(): Single<CatImageResponse>
}