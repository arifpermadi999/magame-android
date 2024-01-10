package com.dicoding.core

import com.dicoding.core.data.source.local.entity.favorite.FavoriteDao
import com.dicoding.core.data.source.local.entity.game.GameDao
import com.dicoding.core.di.databaseModule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class KoinModuleTest : KoinTest {
    val gameDao by inject<GameDao>()
    val favoriteDao by inject<FavoriteDao>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(databaseModule)
    }
    @Test
    fun `database test`(){
        val gameDao
    }
}