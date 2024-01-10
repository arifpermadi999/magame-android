package com.dicoding.core.data.source.mapper

import com.dicoding.core.data.source.local.entity.favorite.FavoriteEntity
import com.dicoding.core.data.source.local.entity.game.GameEntity
import com.dicoding.core.data.source.remote.response.GameResponse
import com.dicoding.core.domain.models.Favorite
import com.dicoding.core.domain.models.Game

object GameMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val listGameEntity = ArrayList<GameEntity>()
        input.map {gameResponse ->
            val game = GameEntity(
                gameId = gameResponse.id.toString(),
                name = gameResponse.name ?: "",
                rating = (gameResponse.rating ?: 0 ).toFloat(),
                platform = gameResponse.platforms?.joinToString(",") {   it?.platform?.name.toString() } ?: "" ,
                image = gameResponse.backgroundImage ?: "",
                ratingsCount = gameResponse.ratingsCount ?: 0
            )
            listGameEntity.add(game)
        }
        return listGameEntity
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> = input.map {
        Game(
            name = it.name,
            rating = it.rating,
            platform = it.platform,
            ratingsCount = it.ratingsCount,
            image = it.image,
            id = it.gameId,
        )
    }
    fun gameDomainToFavEntity(it: Game) : FavoriteEntity = FavoriteEntity(
        name = it.name ?: "",
        rating = (it.rating ?: 0).toFloat(),
        platform = it.platform ?: "",
        ratingsCount = it.ratingsCount ?: 0,
        image = it.image ?: "",
        gameId = it.id ?: "",
    )
    fun favEntityToFavDomain(it: FavoriteEntity) : Favorite = Favorite(
        id = it.id,
        name = it.name,
        rating = it.rating,
        platform = it.platform,
        ratingsCount = it.ratingsCount,
        image = it.image,
        gameId = it.gameId,
    )
    fun favDomainToFavEntity(it: Favorite) : FavoriteEntity = FavoriteEntity(
        id = it.id!!,
        name = it.name!!,
        rating = it.rating!!,
        platform = it.platform!!,
        ratingsCount = it.ratingsCount!!,
        image = it.image!!,
        gameId = it.gameId!!,
    )

    fun favDomainToGame(it: Favorite) : Game = Game(
        id = it.gameId!!,
        name = it.name!!,
        rating = it.rating!!,
        platform = it.platform!!,
        ratingsCount = it.ratingsCount!!,
        image = it.image!!,
    )




}