package com.voidhash.hearthstonecodex.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voidhash.hearthstonecodex.framework.local.dao.CardBackDao
import com.voidhash.hearthstonecodex.framework.local.dao.CardDao
import com.voidhash.hearthstonecodex.framework.local.dao.InfoDao
import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.model.CardModel
import com.voidhash.hearthstonecodex.framework.model.InfoModel
import com.voidhash.hearthstonecodex.framework.remote.service.HearthstoneService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    private val hearthstoneService: HearthstoneService,
    private val cardDao: CardDao,
    private val cardBackDao: CardBackDao,
    private val infoDao: InfoDao
): ViewModel()  {

    private val disposable = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>()
    val hasError = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val isDone = MutableLiveData<Boolean>()

    val hearthstoneInfo: MutableLiveData<InfoModel> by lazy {
        MutableLiveData<InfoModel>()
    }

    val backCardsCollection: MutableLiveData<List<CardBackModel>> by lazy {
        MutableLiveData<List<CardBackModel>>()
    }

    fun initApp() {
        isDone.value = false
        getInfo()
        getCards()
        getCardsBack()
        //getStandCollectionDrawable()
    }

    private fun getInfo() {
        isLoading.value = true
        disposable.add(
            hearthstoneService.getInfo()
                //cria uma nova thread para rodar em background
                .subscribeOn(Schedulers.newThread())
                //o resultado dessa nova thread será mostrada na thread principal
                .observeOn(AndroidSchedulers.mainThread())
                //define o q vamos fazer com o resuldado dessa thread
                .subscribeWith(object: DisposableSingleObserver<InfoModel>(){
                    override fun onSuccess(infoModel: InfoModel) {
                        hasError.value = false
                        isLoading.value = false
                        hearthstoneInfo.value = infoModel
                        saveInfoIntoDB(infoModel)
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

    private fun saveInfoIntoDB(infoModel: InfoModel) {
        infoDao.addInfo(infoModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    hasError.value = false
                    isLoading.value = false
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    hasError.value = true
                    isLoading.value = false
                    errorMessage.value = "Something is wrong, reload the app"
                }
            })
    }

    private fun getCards() {
        isLoading.value = true
        disposable.add(
            hearthstoneService.getCards()
                //cria uma nova thread para rodar em background
                .subscribeOn(Schedulers.newThread())
                //o resultado dessa nova thread será mostrada na thread principal
                .observeOn(AndroidSchedulers.mainThread())
                //define o q vamos fazer com o resuldado dessa thread
                .subscribeWith(object: DisposableSingleObserver<CardModel>(){
                    override fun onSuccess(value: CardModel) {
                        isLoading.value = false
                        saveCardsIntoDB(value)
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                        hasError.value = true
                        errorMessage.value = "Check your internet connection"
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun saveCardsIntoDB(value: CardModel) {
        cardDao.addCard(value.getAllCards())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    hasError.value = false
                    isLoading.value = false
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    hasError.value = true
                    isLoading.value = false
                    errorMessage.value = "Something is wrong, reload the app"
                }
            })
    }

    private fun getCardsBack() {
        isLoading.value = true
        disposable.add(
            hearthstoneService.getCardsBack()
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
                        saveCardsBackIntoDB(value)
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

    private fun saveCardsBackIntoDB(value: List<CardBackModel>) {
        cardBackDao.addCard(value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    hasError.value = false
                    isLoading.value = false
                    isDone.value = true
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    hasError.value = true
                    isLoading.value = false
                    errorMessage.value = "Something is wrong, reload the app"
                }
            })
    }

//    @SuppressLint("CheckResult")
//    fun getStandCollectionDrawable(): List<String> {
//        infoDao.getInfo()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    it.standard
//                }
//    }
}