package com.voidhash.hearthstonecodex.presentation

import android.app.Application
import com.voidhash.hearthstonecodex.framework.di.NetworkModule
import com.voidhash.hearthstonecodex.framework.di.PersistenceModule
import com.voidhash.hearthstonecodex.framework.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    NetworkModule.hearthstoneService,
                    PersistenceModule.roomDatabase,
                    ViewModelModule.mainViewModel,
                )
            )
        }
    }
}