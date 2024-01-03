package com.dicoding.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.GameUseCase

class FavoriteViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    fun favorite() = gameUseCase.getAllFavorite().asLiveData()
}