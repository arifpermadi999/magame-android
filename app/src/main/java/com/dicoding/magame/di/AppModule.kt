package com.dicoding.magame.di

import com.dicoding.core.domain.usecase.GameInteractor
import com.dicoding.core.domain.usecase.GameUseCase
import com.dicoding.magame.ui.game.detail.DetailGameViewModel
import com.dicoding.magame.ui.game.list.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module{
    single<GameUseCase>{
        GameInteractor(
            get()
        )
    }
}
val viewModelModule = module{
    viewModel {
        GameViewModel(get())
    }
    viewModel {
        DetailGameViewModel(get())
    }





}
