package com.voidhash.hearthstonecodex.framework.di

import com.voidhash.hearthstonecodex.framework.viewmodel.CardBackViewModel
import com.voidhash.hearthstonecodex.framework.viewmodel.CardViewModel
import com.voidhash.hearthstonecodex.framework.viewmodel.CollectionViewModel
import com.voidhash.hearthstonecodex.framework.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {

    val mainViewModel = module {
        viewModel { MainViewModel(get(), get(), get(), get()) }
        viewModel { CardBackViewModel(get()) }
        viewModel { CollectionViewModel(get()) }
        viewModel { CardViewModel(get(), get()) }

    }
}