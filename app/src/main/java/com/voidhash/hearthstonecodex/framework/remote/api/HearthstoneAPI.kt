package com.voidhash.hearthstonecodex.framework.remote.api

import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.model.CardModel
import com.voidhash.hearthstonecodex.framework.model.InfoModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HearthstoneAPI {

    @GET("/cards")
    fun getCards(): Single<CardModel>

    @GET("/cardbacks")
    fun getCardsBack(): Single<List<CardBackModel>>

    @GET("/info")
    fun getInfo(): Single<InfoModel>

    @GET("cards/search/{name}")
    fun getCardSearch(@Path("name") name: String): Single<List<CardBase>>
}