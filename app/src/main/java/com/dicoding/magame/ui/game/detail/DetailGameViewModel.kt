package com.dicoding.magame.ui.game.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.core.domain.models.Favorite
import com.dicoding.core.domain.models.Game
import com.dicoding.core.domain.usecase.GameUseCase
import kotlinx.coroutines.launch

class DetailGameViewModel(private val gameUseCase: GameUseCase): ViewModel() {
    fun detail(id:String) = gameUseCase.getGameById(id).asLiveData()
    fun addFavorite(game: Game) = viewModelScope.launch {  gameUseCase.addFavorite(game) }
    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch {  gameUseCase.deleteFavorite(favorite) }
    fun getFavoriteByGame(game: Game) = gameUseCase.getFavoriteByGame(game).asLiveData()
}