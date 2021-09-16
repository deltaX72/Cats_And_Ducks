package com.litil.catsandducks.data.repositories

import com.litil.catsandducks.data.services.DucksImagesService
import com.litil.catsandducks.domain.models.DuckImageResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface DucksImagesRepository {
    fun downloadImage(): Single<DuckImageResponse>
}

class DuckImagesRepositoryImpl @Inject constructor(
    val service: DucksImagesService
): DucksImagesRepository {
    override fun downloadImage(): Single<DuckImageResponse> {
        return service.download().subscribeOn(Schedulers.newThread())
    }
}