package com.litil.catsandducks.presentation.viewmodels

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.litil.catsandducks.data.repositories.CatsImagesRepository
import com.litil.catsandducks.data.repositories.DucksImagesRepository
import com.litil.catsandducks.data.repositories.ImagesDatabaseRepository
import com.litil.catsandducks.domain.models.CatImageResponse
import com.litil.catsandducks.domain.models.DuckImageResponse
import com.litil.catsandducks.domain.models.ModelResponse
import com.litil.catsandducks.domain.models.db.ImageModel
import com.litil.catsandducks.presentation.utils.convertImageViewToByteArray
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val catsImagesRepository: CatsImagesRepository,
    private val ducksImagesRepository: DucksImagesRepository,
    private val imagesDatabaseRepository: ImagesDatabaseRepository
) : ViewModel() {

    val ldModelResponse = MutableLiveData<ModelResponse>()
    val ldImageView = MutableLiveData<ImageView>()
    val ldImageModelList = MutableLiveData<List<ImageModel>>()

    private val ldLastClickTime = MutableLiveData(System.currentTimeMillis())
    private val ldLastCountClicks = MutableLiveData(0)

    fun downloadCatImage() {
        catsImagesRepository.downloadImage()
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                ldModelResponse.postValue(it)
            }, {})
            .untilDestroyed()
    }

    fun downloadDuckImage() {
        ducksImagesRepository.downloadImage()
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                ldModelResponse.postValue(it)
            }, {})
            .untilDestroyed()
    }

    fun loadImageFromModel(modelResponse: ModelResponse, imageView: ImageView) {
        val url = when (modelResponse) {
            is CatImageResponse -> modelResponse.webpurl
            is DuckImageResponse -> modelResponse.url
            else -> throw RuntimeException("Model not found!")
        }
        Picasso.get().load(url).into(imageView)
        ldImageView.value = imageView
    }

    fun saveImage(imageView: ImageView) {
        val array = convertImageViewToByteArray(imageView)

        imagesDatabaseRepository.getAllImages()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                val id = try {
                    list.last().id + 1
                } catch (ex: NoSuchElementException) {
                    0
                }
                imagesDatabaseRepository.saveImage(
                    ImageModel(
                        id = id,
                        name = System.currentTimeMillis().toString(),
                        image = array
                    )
                )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, {
                        Log.e("LOG_TAG SAVE", it.stackTraceToString())
                    })
                    .untilDestroyed()
            }, {
                Log.e("LOG_TAG GET ALL", it.stackTraceToString())
            })
            .untilDestroyed()

    }

    fun getAllImagesFromDatabase() {
        imagesDatabaseRepository.getAllImages()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                ldImageModelList.value = it
            }, {
                Log.e("LOG_TAG GET ALL", it.stackTraceToString())
            })
            .untilDestroyed()
    }

    fun deleteImage(image: ByteArray) {
        imagesDatabaseRepository.deleteImage(image)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getAllImagesFromDatabase()
            }, {
                getAllImagesFromDatabase()
                Log.e("LOG_TAG DELETE", it.stackTraceToString())
            })
            .untilDestroyed()
    }

    fun setLastClickTimeValue(value: Long) {
        ldLastClickTime.value = value
    }

    fun getLastClickTimeValue(): Long {
        return ldLastClickTime.value ?: throw RuntimeException("LiveData is not init!")
    }

    fun setLastCountClicksValue(value: Int) {
        ldLastCountClicks.value = value
    }

    fun getLastCountClicksValue(): Int {
        return ldLastCountClicks.value ?: throw RuntimeException("LiveData is not init!")
    }

    //=================================================================

    private val compositeDisposable = CompositeDisposable()
    private fun Disposable.untilDestroyed() = compositeDisposable.add(this)

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    // Factory

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val catsImagesRepository: CatsImagesRepository,
        private val ducksImagesRepository: DucksImagesRepository,
        private val imagesDatabaseRepository: ImagesDatabaseRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(
                catsImagesRepository,
                ducksImagesRepository,
                imagesDatabaseRepository
            ) as T
        }
    }
}