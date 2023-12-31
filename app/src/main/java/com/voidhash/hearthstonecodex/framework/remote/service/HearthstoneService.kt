package com.voidhash.hearthstonecodex.framework.remote.service

import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.model.CardModel
import com.voidhash.hearthstonecodex.framework.model.InfoModel
import com.voidhash.hearthstonecodex.framework.remote.api.HearthstoneAPI
import io.reactivex.rxjava3.core.Single

class HearthstoneService(private val api: HearthstoneAPI) {

    fun getCards(): Single<CardModel> {
        return api.getCards()
    }

    fun getInfo(): Single<InfoModel> {
        return api.getInfo()
    }

    fun getCardsBack(): Single<List<CardBackModel>> {
        return  api.getCardsBack()
    }

    fun getCardSearch(name: String): Single<List<CardBase>> {
        return api.getCardSearch(name)
    }
}