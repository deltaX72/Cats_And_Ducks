package com.litil.catsandducks.presentation.viewmodels

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.litil.catsandducks.data.repositories.CatsImagesRepository
import com.litil.catsandducks.data.repositories.DucksImagesRepository
import com.litil.catsandducks.domain.models.CatImageResponse
import com.litil.catsandducks.domain.models.DuckImageResponse
import com.litil.catsandducks.domain.models.ModelResponse
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val catsImagesRepository: CatsImagesRepository,
    val ducksImagesRepository: DucksImagesRepository
) : ViewModel() {

//    val ldIsAnyButtonPressed = MutableLiveData(false)
    val ldImage = MutableLiveData<ModelResponse>()
    val ldLastClickTime = MutableLiveData(System.currentTimeMillis())
    val ldLastCountClicks = MutableLiveData(0)

//    @Inject
//    lateinit var catsImagesRepository: CatsImagesRepository
//
//    @Inject
//    lateinit var ducksImagesRepository: DucksImagesRepository

    fun downloadCatImage() {
        catsImagesRepository.downloadImage()
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                ldImage.postValue(it)
            }, {})
            .untilDestroyed()
    }

    fun downloadDuckImage() {
        ducksImagesRepository.downloadImage()
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                ldImage.postValue(it)
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
    }

//    fun setIsAnyButtonPressedValue(value: Boolean) {
//        ldIsAnyButtonPressed.value = value
//    }

    fun setLastClickTimeValue(value: Long) {
        ldLastClickTime.value = value
    }

    fun getLastClickTimeValue(): Long = ldLastClickTime.value ?: throw RuntimeException("LiveData is not init!")

    fun setLastCountClicksValue(value: Int) {
        ldLastCountClicks.value = value
    }
    fun getLastCountClicksValue(): Int = ldLastCountClicks.value ?: throw RuntimeException("LiveData is not init!")
    fun addLastCountClicksValue() {
        ldLastCountClicks.value = ldLastCountClicks.value?.plus(1)
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
        val catsImagesRepository: CatsImagesRepository,
        val ducksImagesRepository: DucksImagesRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(catsImagesRepository, ducksImagesRepository) as T
        }
    }
}