package com.dicoding.magame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.GameUseCase

class MainViewModel(useCase: GameUseCase) : ViewModel() {
    val games = useCase.getAllGame().asLiveData()
}