package com.voidhash.hearthstonecodex.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voidhash.hearthstonecodex.framework.local.dao.CardBackDao
import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.model.InfoModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class CardBackViewModel(
    private val cardBackDao: CardBackDao
) : ViewModel() {

    private val disposable = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>()
    val hasError = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val isDone = MutableLiveData<Boolean>()

    val backCardsCollection: MutableLiveData<List<CardBackModel>> by lazy {
        MutableLiveData<List<CardBackModel>>()
    }

    fun initApp() {
        isDone.value = false
        getCardsBack()
        //getStandCollectionDrawable()
    }

    private fun getCardsBack() {
        isLoading.value = true
        disposable.add(
            cardBackDao.getAllCards()
                //cria uma nova thread para rodar em background
                .subscribeOn(Schedulers.newThread())
                //o resultado dessa nova thread será mostrada na thread principal
                .observeOn(AndroidSchedulers.mainThread())
                //define o q vamos fazer com o resuldado dessa thread
                .subscribeWith(object: DisposableSingleObserver<List<CardBackModel>>(){
                    override fun onSuccess(value: List<CardBackModel>) {
                        hasError.value = false
                        isLoading.value = false
                        backCardsCollection.value = value
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