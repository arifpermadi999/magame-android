package com.dicoding.favorite.di

import com.dicoding.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteViewModelModule = module{
    viewModel {
        FavoriteViewModel(get())
    }
}