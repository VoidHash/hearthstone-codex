package com.voidhash.hearthstonecodex.framework.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CardBackDao {

    @Query("SELECT (SELECT COUNT(*) FROM card_back_table) == 0")
    fun isEmpty(): Single<Boolean>

    @Query("SELECT * FROM card_back_table")
    fun getAllCards(): Single<List<CardBackModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCard(cardList: List<CardBackModel>) : Completable

    @Query("DELETE FROM card_back_table")
    fun deleteAll() : Completable

    @Delete
    fun delete(cardEntity: CardBackModel) : Completable
}