package com.voidhash.hearthstonecodex.framework.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.voidhash.hearthstonecodex.framework.model.InfoModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface InfoDao {

    @Query("SELECT (SELECT COUNT(*) FROM info_table) == 0")
    fun isEmpty(): LiveData<Boolean>

    @Query("SELECT * FROM info_table")
    fun getInfo(): LiveData<InfoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addInfo(infoEntity: InfoModel) : Completable

    @Query("DELETE FROM info_table")
    fun deleteAll() : Completable

    @Delete
    fun delete(infoEntity: InfoModel) : Completable
}