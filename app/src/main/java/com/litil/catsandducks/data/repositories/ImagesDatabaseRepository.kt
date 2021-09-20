package com.litil.catsandducks.data.repositories

import com.litil.catsandducks.data.local.ImagesDao
import com.litil.catsandducks.domain.models.db.ImageModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface ImagesDatabaseRepository {

    fun saveImage(image: ImageModel): Completable

    fun getAllImages(): Single<List<ImageModel>>

    fun deleteAllImages(): Completable

    fun getLastImage(id: Long): Single<ImageModel>

    fun deleteImage(image: ByteArray): Completable

    fun deleteImage(id: Long): Completable
}

class ImagesDatabaseRepositoryImpl @Inject constructor(
    private val dao: ImagesDao
): ImagesDatabaseRepository {
    override fun saveImage(image: ImageModel): Completable {
        return dao.saveImage(image).subscribeOn(Schedulers.io())
    }

    override fun getAllImages(): Single<List<ImageModel>> {
        return dao.getAllImages().subscribeOn(Schedulers.io())
    }

    override fun deleteAllImages(): Completable {
        return dao.deleteAllImages().subscribeOn(Schedulers.io())
    }

    override fun getLastImage(id: Long): Single<ImageModel> {
        return dao.getLastImage(id).subscribeOn(Schedulers.io())
    }

    override fun deleteImage(image: ByteArray): Completable {
        return dao.deleteImage(image).subscribeOn(Schedulers.io())
    }

    override fun deleteImage(id: Long): Completable {
        return dao.deleteImage(id).subscribeOn(Schedulers.io())
    }

}