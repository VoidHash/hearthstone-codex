package com.voidhash.hearthstonecodex.framework.local.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.voidhash.hearthstonecodex.framework.local.dao.CardBackDao
import com.voidhash.hearthstonecodex.framework.local.dao.CardDao
import com.voidhash.hearthstonecodex.framework.local.dao.InfoDao
import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.model.InfoModel
import com.voidhash.hearthstonecodex.framework.util.ListCardConverter

@Database(entities = [CardBase::class, CardBackModel::class, InfoModel::class], version = 1)
@TypeConverters(ListCardConverter::class)
abstract class CardDatabase: RoomDatabase() {
    abstract fun cardDao(): CardDao

    abstract fun cardBackDao(): CardBackDao

    abstract fun infoDao(): InfoDao
}