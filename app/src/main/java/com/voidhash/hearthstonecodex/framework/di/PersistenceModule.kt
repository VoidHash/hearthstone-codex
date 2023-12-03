package com.voidhash.hearthstonecodex.framework.di

import android.app.Application
import androidx.room.Room
import com.voidhash.hearthstonecodex.framework.local.dao.CardBackDao
import com.voidhash.hearthstonecodex.framework.local.dao.CardDao
import com.voidhash.hearthstonecodex.framework.local.dao.InfoDao
import com.voidhash.hearthstonecodex.framework.local.datasource.CardDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object PersistenceModule {

    val roomDatabase = module {

        fun provideDataBase(application: Application): CardDatabase {
            return Room.databaseBuilder(
                application,
                CardDatabase::class.java,
                "hearthstone-db"
            ).fallbackToDestructiveMigration().build()
        }

        fun provideInfoDao(database: CardDatabase): InfoDao {
            return database.infoDao()
        }

        fun provideCardDao(database: CardDatabase): CardDao {
            return database.cardDao()
        }

        fun provideCardBackDao(database: CardDatabase): CardBackDao {
            return database.cardBackDao()
        }

        single { provideDataBase(androidApplication()) }
        single { provideInfoDao(get()) }
        single { provideCardDao(get()) }
        single { provideCardBackDao(get()) }
    }
}