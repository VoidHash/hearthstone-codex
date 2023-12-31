package com.voidhash.hearthstonecodex.framework.local.dao

import androidx.room.*
import com.voidhash.hearthstonecodex.framework.model.CardBase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface CardDao {

    @Query("SELECT (SELECT COUNT(*) FROM card_table) == 0")
    fun isEmpty(): Single<Boolean>

    @Query("SELECT * FROM card_table")
    fun getAllCards(): Single<List<CardBase>>

    @Query("SELECT * FROM card_table WHERE cardSet LIKE :cardSet")
    fun getFromCardSet(cardSet: String): Single<List<CardBase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCard(cardList: List<CardBase>) : Completable

    @Query("DELETE FROM card_table")
    fun deleteAll() : Completable

    @Delete
    fun delete(cardEntity: CardBase) : Completable
}