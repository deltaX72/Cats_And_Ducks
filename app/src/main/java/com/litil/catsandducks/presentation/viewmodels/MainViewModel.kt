package com.litil.catsandducks.presentation.viewmodels

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.litil.catsandducks.data.repositories.CatsImagesRepository
import com.litil.catsandducks.domain.models.CatImageResponse
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

//    private val ldButtonShowCat = MutableLiveData<>()

    val ldImage = MutableLiveData<ImageView>()

    val ldJsonImage = MutableLiveData<CatImageResponse>()

    @Inject
    lateinit var catsImagesRepository: CatsImagesRepository

    fun downloadCatImage() {
        catsImagesRepository.downloadImage()
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("URLLLLLLLL", it.toString())
                ldJsonImage.postValue(it)
//                Picasso.get().load(it.url).into(ldImage.value)
            }, {})
            .untilDestroyed()
    }

    fun showDuck() {

    }

    fun downloadImageFromUrl(url: String, imageView: ImageView) {
        Picasso.get().load(url).into(imageView)
    }

    //=================================================================

    private val compositeDisposable = CompositeDisposable()
    private fun Disposable.untilDestroyed() = compositeDisposable.add(this)

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }
}