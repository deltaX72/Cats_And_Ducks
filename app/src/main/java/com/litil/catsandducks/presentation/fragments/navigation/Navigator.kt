package com.litil.catsandducks.presentation.fragments.navigation

import androidx.fragment.app.Fragment
import com.litil.catsandducks.presentation.utils.Options

interface Navigator {

    fun showImage(options: Options)

    fun showFavourites()
}

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}