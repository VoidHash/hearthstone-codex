package com.voidhash.hearthstonecodex.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voidhash.hearthstonecodex.framework.local.dao.CardDao
import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.model.CardModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class CollectionViewModel(private val cardDao: CardDao): ViewModel() {

    private val disposable = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>()
    val hasError = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    val cardsCollection: MutableLiveData<List<CardBase>> by lazy {
        MutableLiveData<List<CardBase>>()
    }

    fun getCardsFromCollection(collection: String) {
        isLoading.value = true
        disposable.add(
            cardDao.getFromCardSet(collection)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<CardBase>>() {
                    override fun onSuccess(cardList: List<CardBase>) {
                        hasError.value = false
                        isLoading.value = false
                        cardsCollection.value = cardList
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        errorMessage.value = e.message
                        hasError.value = true
                        isLoading.value = false
                    }
                })
        )
    }
}