package com.voidhash.hearthstonecodex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.voidhash.hearthstonecodex.framework.local.dao.CardDao
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.viewmodel.CollectionViewModel
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.internal.schedulers.ImmediateThinScheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectionViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cardDao: CardDao

    private var closeable: AutoCloseable? = null

    private lateinit var viewModel: CollectionViewModel

    @Before
    fun setup() {
        closeable = MockitoAnnotations.openMocks(this)
        viewModel = CollectionViewModel(cardDao)
    }

    @Before
    fun setUpRxSchedulers(){
        val immediate =  ImmediateThinScheduler.INSTANCE
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

    @Test
    fun `Test cards data set loaded from DATABASE`() {
        val cardList: List<CardBase> = listOf(
            CardBase(dbfId = 1826, cardId = "NAX11_01", name = "Grobbulus", cardSet = "Naxxramas"),
            CardBase(dbfId = 1807, cardId = "FP1_023", name = "Dark Cultist", cardSet = "Naxxramas"),
            CardBase(dbfId = 96785, cardId = "BG_FP1_031", name = "Baron Rivendare", cardSet = "Naxxramas")
        )
        val testSingle: Single<List<CardBase>> = Single.just(cardList)
        Mockito.`when`(cardDao.getFromCardSet("Naxxramas")).thenReturn(testSingle)
        viewModel.getCardsFromCollection("Naxxramas")

        Assert.assertEquals(3, viewModel.cardsCollection.value?.size)
        Assert.assertEquals(false, viewModel.isLoading.value)
        Assert.assertEquals(false, viewModel.hasError.value)
    }

    @Test
    fun `Test request ERROR`() {
        val testSingle: Single<List<CardBase>> = Single.error(Throwable())
        Mockito.`when`(cardDao.getFromCardSet("Naxxramas")).thenReturn(testSingle)
        viewModel.getCardsFromCollection("Naxxramas")
        Assert.assertEquals(false, viewModel.isLoading.value)
        Assert.assertEquals(true, viewModel.hasError.value)
    }

    @After
    fun releaseMocks() {
        closeable?.close()
    }
}