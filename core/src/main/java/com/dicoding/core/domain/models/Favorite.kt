package com.dicoding.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Favorite (
    val id: Int? = null,
    val gameId: String? = null,
    val name: String? = null,
    val rating: Float? = null,
    val ratingsCount: Int? = null,
    val platform: String? = null,
    val image: String? = null,
): Parcelable
