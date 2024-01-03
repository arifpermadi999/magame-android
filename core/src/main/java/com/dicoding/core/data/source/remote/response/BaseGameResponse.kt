package com.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseGameResponse(

    @field:SerializedName("next")
	val next: String? = null,

    @field:SerializedName("nofollow")
	val nofollow: Boolean? = null,

    @field:SerializedName("noindex")
	val noindex: Boolean? = null,

    @field:SerializedName("nofollow_collections")
	val nofollowCollections: List<String?>? = null,

    @field:SerializedName("previous")
	val previous: Any? = null,

    @field:SerializedName("count")
	val count: Int? = null,

    @field:SerializedName("description")
	val description: String? = null,

    @field:SerializedName("seo_h1")
	val seoH1: String? = null,

    @field:SerializedName("filters")
	val filters: Filters? = null,

    @field:SerializedName("seo_title")
	val seoTitle: String? = null,

    @field:SerializedName("seo_description")
	val seoDescription: String? = null,

    @field:SerializedName("results")
	val results: List<GameResponse>,

    @field:SerializedName("seo_keywords")
	val seoKeywords: String? = null
)

data class RequirementsRu(

	@field:SerializedName("minimum")
	val minimum: String? = null,

	@field:SerializedName("recommended")
	val recommended: String? = null
)

data class EsrbRating(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class ShortScreenshotsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Platform(

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("year_end")
	val yearEnd: Any? = null,

	@field:SerializedName("year_start")
	val yearStart: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class TagsItem(

	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class RequirementsEn(

	@field:SerializedName("minimum")
	val minimum: String? = null,

	@field:SerializedName("recommended")
	val recommended: String? = null
)

data class StoresItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("store")
	val store: Store? = null
)

data class PlatformsItem(

	@field:SerializedName("requirements_ru")
	val requirementsRu: Any? = null,

	@field:SerializedName("requirements_en")
	val requirementsEn: Any? = null,

	@field:SerializedName("released_at")
	val releasedAt: String? = null,

	@field:SerializedName("platform")
	val platform: Platform? = null
)

data class RatingsItem(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("percent")
	val percent: Any? = null
)

data class GenresItem(

	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class ParentPlatformsItem(

	@field:SerializedName("platform")
	val platform: Platform? = null
)


data class Store(

	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("domain")
	val domain: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class YearsItem(

    @field:SerializedName("filter")
	val filter: String? = null,

    @field:SerializedName("nofollow")
	val nofollow: Boolean? = null,

    @field:SerializedName("decade")
	val decade: Int? = null,

    @field:SerializedName("count")
	val count: Int? = null,

    @field:SerializedName("from")
	val from: Int? = null,

    @field:SerializedName("to")
	val to: Int? = null,

    @field:SerializedName("years")
	val years: List<YearsItem?>? = null,

    @field:SerializedName("year")
	val year: Int? = null
)

data class AddedByStatus(

	@field:SerializedName("owned")
	val owned: Int? = null,

	@field:SerializedName("beaten")
	val beaten: Int? = null,

	@field:SerializedName("dropped")
	val dropped: Int? = null,

	@field:SerializedName("yet")
	val yet: Int? = null,

	@field:SerializedName("playing")
	val playing: Int? = null,

	@field:SerializedName("toplay")
	val toplay: Int? = null
)

data class Filters(

	@field:SerializedName("years")
	val years: List<YearsItem?>? = null
)
