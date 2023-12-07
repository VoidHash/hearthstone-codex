package com.voidhash.hearthstonecodex.framework.remote.api

import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.model.CardModel
import com.voidhash.hearthstonecodex.framework.model.InfoModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface HearthstoneAPI {

    @GET("/cards")
    fun getCards(): Single<CardModel>

    @GET("/cardbacks")
    fun getCardsBack(): Single<List<CardBackModel>>

    @GET("/info")
    fun getInfo(): Single<InfoModel>
}