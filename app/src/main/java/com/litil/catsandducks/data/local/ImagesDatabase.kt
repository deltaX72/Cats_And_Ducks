package com.litil.catsandducks.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.litil.catsandducks.domain.models.db.ImageModel
import javax.inject.Inject

@Database(entities = [ImageModel::class], version = 5, exportSchema = false)
abstract class ImagesDatabase : RoomDatabase() {
    abstract val imagesDao: ImagesDao

    companion object {
        const val DATABASE_NAME = "ImagesDatabase.db"

        @Volatile
        var INSTANCE: ImagesDatabase? = null

        fun getInstance(context: Context): ImagesDatabase {
            val temp = INSTANCE
            if (temp != null)
                return temp
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImagesDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}