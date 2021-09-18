package com.litil.catsandducks.data.repositories

import com.litil.catsandducks.data.services.CatsImagesService
import com.litil.catsandducks.domain.models.CatImageResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface CatsImagesRepository {
    fun downloadImage(id: Int): Single<CatImageResponse>

    fun downloadImage(): Single<CatImageResponse>
}

class CatsImagesRepositoryImpl @Inject constructor(
    val service: CatsImagesService
): CatsImagesRepository {
    override fun downloadImage(id: Int): Single<CatImageResponse> {
        return service.download(id).subscribeOn(Schedulers.newThread())
    }

    override fun downloadImage(): Single<CatImageResponse> {
        return service.download().subscribeOn(Schedulers.newThread())
    }
}