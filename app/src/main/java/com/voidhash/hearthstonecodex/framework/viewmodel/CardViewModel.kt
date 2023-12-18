package com.voidhash.hearthstonecodex.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voidhash.hearthstonecodex.framework.local.dao.CardDao
import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.remote.service.HearthstoneService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class CardViewModel(
    private val hearthstoneService: HearthstoneService,
    private val cardDao: CardDao
) : ViewModel() {

    private val disposable = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>()
    val hasError = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    val cardsCollection: MutableLiveData<List<CardBase>> by lazy {
        MutableLiveData<List<CardBase>>()
    }

    fun fetchCards(collection: String) {
        getCardsCollection(collection)
    }

    fun fetchCardSearched(name: String) {
        getCardSearched(name)
    }

    private fun getCardsCollection(collection: String) {
        isLoading.value = true
        disposable.add(
            cardDao.getFromCardSet(collection)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CardBase>>(){
                    override fun onSuccess(value: List<CardBase>) {
                        hasError.value = false
                        isLoading.value = false
                        cardsCollection.value = value
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        hasError.value = true
                        isLoading.value = false
                        errorMessage.value = "Check your internet connection"
                    }

                })
        )
    }

    private fun getCardSearched(name: String) {
        isLoading.value = true
        disposable.add(
            hearthstoneService.getCardSearch(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CardBase>>() {
                    override fun onSuccess(value: List<CardBase>) {
                        hasError.value = false
                        isLoading.value = false
                        cardsCollection.value = value
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        hasError.value = true
                        isLoading.value = false
                        errorMessage.value = "Check your internet connection"
                    }

                })
        )
    }
}