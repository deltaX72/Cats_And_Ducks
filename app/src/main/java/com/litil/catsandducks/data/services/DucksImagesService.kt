package com.litil.catsandducks.data.services

import com.litil.catsandducks.domain.models.DuckImageResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DucksImagesService {

    @GET("random")
    fun download(): Single<DuckImageResponse>
}