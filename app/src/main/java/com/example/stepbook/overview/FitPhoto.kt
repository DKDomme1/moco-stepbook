package com.example.stepbook.overview

import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes

data class FitPhoto (
    @IntegerRes val weightResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val dateResourceId: Int
)