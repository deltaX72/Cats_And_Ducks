package com.litil.catsandducks.domain.di.modules

import com.litil.catsandducks.data.CATS_IMAGES_BASE_URL
import com.litil.catsandducks.data.DUCKS_IMAGES_BASE_URL
import com.litil.catsandducks.data.services.CatsImagesService
import com.litil.catsandducks.data.services.DucksImagesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {
    @Provides
    fun provideCatsImagesService(): CatsImagesService {
        val retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }).build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CATS_IMAGES_BASE_URL)
            .build()
        return retrofit.create(CatsImagesService::class.java)
    }

    @Provides
    fun provideDucksImagesService(): DucksImagesService {
        val retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }).build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DUCKS_IMAGES_BASE_URL)
            .build()
        return retrofit.create(DucksImagesService::class.java)
    }
}