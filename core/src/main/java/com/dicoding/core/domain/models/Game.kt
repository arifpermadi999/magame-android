package com.dicoding.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game (
    val id: String,
    val name: String,
    val rating: Float,
    val ratingsCount: Int,
    val platform: String,
    val image: String,
):Parcelable
