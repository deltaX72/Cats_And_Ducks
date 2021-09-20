package com.litil.catsandducks.data.local

import android.graphics.Bitmap
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.litil.catsandducks.domain.models.db.ImageModel
import com.litil.catsandducks.domain.models.db.ImageModel.Companion.tableName
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ImagesDao {

    @Insert
    fun saveImage(imageModel: ImageModel): Completable

    @Query("SELECT * FROM $tableName")
    fun getAllImages(): Single<List<ImageModel>>

    @Query("SELECT * FROM $tableName WHERE :id = (SELECT MAX(`id`) FROM $tableName)")
    fun getLastImage(id: Long): Single<ImageModel>

    @Query("DELETE FROM $tableName")
    fun deleteAllImages(): Completable

    @Query("DELETE FROM $tableName WHERE :image = image")
    fun deleteImage(image: ByteArray): Completable

    @Query("DELETE FROM $tableName WHERE :id = id")
    fun deleteImage(id: Long): Completable
}