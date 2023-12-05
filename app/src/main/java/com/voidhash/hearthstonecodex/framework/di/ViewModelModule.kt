package com.voidhash.hearthstonecodex.framework.di

import com.voidhash.hearthstonecodex.framework.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {

    val mainViewModel = module {
        viewModel { MainViewModel(get(), get(), get(), get()) }

    }
}