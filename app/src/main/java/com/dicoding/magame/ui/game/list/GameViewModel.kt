package com.dicoding.magame.ui.game.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.GameUseCase

class GameViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    fun games() = gameUseCase.getAllGame().asLiveData()
    fun gameSearch(search:String) = gameUseCase.getAllGameBySearch(search).asLiveData()

}