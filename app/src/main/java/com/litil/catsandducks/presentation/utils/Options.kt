package com.litil.catsandducks.presentation.utils

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Options(
    val clickedButton: ClickedButton
): Parcelable {

    companion object {
        @JvmStatic
        val DEFAULT = ClickedButton.NONE
    }
}

enum class ClickedButton {
    SHOW_CAT,
    SHOW_DUCK,
    NONE
}