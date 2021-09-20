package com.litil.catsandducks.domain.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.litil.catsandducks.data.local.ImagesDao
import com.litil.catsandducks.data.local.ImagesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    fun provideImagesDatabase(context: Context): ImagesDatabase {
        return ImagesDatabase.getInstance(context)
    }

    @Provides
    fun provideImagesDao(database: ImagesDatabase): ImagesDao {
        return database.imagesDao
    }
}