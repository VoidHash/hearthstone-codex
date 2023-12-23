package com.voidhash.hearthstonecodex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.voidhash.hearthstonecodex.framework.local.dao.CardBackDao
import com.voidhash.hearthstonecodex.framework.local.dao.CardDao
import com.voidhash.hearthstonecodex.framework.local.dao.InfoDao
import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.model.CardModel
import com.voidhash.hearthstonecodex.framework.model.InfoModel
import com.voidhash.hearthstonecodex.framework.remote.service.HearthstoneService
import com.voidhash.hearthstonecodex.framework.viewmodel.MainViewModel
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Completable
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
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var hearthstoneService: HearthstoneService
    @Mock
    private lateinit var infoDao: InfoDao
    @Mock
    private lateinit var cardDao: CardDao
    @Mock
    private lateinit var cardBackDao: CardBackDao
    @Mock
    private lateinit var viewModel: MainViewModel

    private var closeable: AutoCloseable? = null

    @Before
    fun setup() {
        closeable = MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(hearthstoneService, cardDao, cardBackDao, infoDao)
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
    fun `Test if ViewModel is return right values`() {
        val cardModel = CardModel(
            naxxramas = listOf(
                CardBase(dbfId = 1826, cardId = "NAX11_01", name = "Grobbulus", cardSet = "Naxxramas"),
                CardBase(dbfId = 1807, cardId = "FP1_023", name = "Dark Cultist", cardSet = "Naxxramas"),
                CardBase(dbfId = 96785, cardId = "BG_FP1_031", name = "Baron Rivendare", cardSet = "Naxxramas")
            )
        )

        val cardBackList: List<CardBackModel> = listOf(
            CardBackModel(cardBackId = 375, name = "Harvest Wreath", img = "url://"),
            CardBackModel(cardBackId = 200, name = "Serpentine", img = "url://"),
            CardBackModel(cardBackId = 249, name = "Herbalist", img = "url://"),
        )

        val infoMock = InfoModel(patch = "28.2.1.190920")

        val testSingle01: Single<CardModel> = Single.just(cardModel)
        Mockito.`when`(cardDao.addCard(cardModel.getAllCards())).thenReturn(Completable.complete())
        Mockito.`when`(hearthstoneService.getCards()).thenReturn(testSingle01)

        val testSingle02: Single<List<CardBackModel>> = Single.just(cardBackList)
        Mockito.`when`(cardBackDao.getAllCards()).thenReturn(testSingle02)
        Mockito.`when`(cardBackDao.addCard(cardBackList)).thenReturn(Completable.complete())
        Mockito.`when`(hearthstoneService.getCardsBack()).thenReturn(testSingle02)

        val testSingle03: Single<InfoModel> = Single.just(infoMock)
        Mockito.`when`(infoDao.addInfo(infoMock)).thenReturn(Completable.complete())
        Mockito.`when`(infoDao.getInfo()).thenReturn(testSingle03)
        Mockito.`when`(hearthstoneService.getInfo()).thenReturn(testSingle03)

        viewModel.initApp()

        Assert.assertEquals("28.2.1.190920", viewModel.hearthstoneInfo.value?.patch)
        Assert.assertEquals(3, viewModel.backCardsCollection.value?.size)
        Assert.assertEquals(false, viewModel.isLoading.value)
        Assert.assertEquals(false, viewModel.hasError.value)
    }

    @Test
    fun `Test request ERROR`() {
        val testSingle01: Single<InfoModel> = Single.error(Throwable())
        Mockito.`when`(infoDao.getInfo()).thenReturn(testSingle01)
        Mockito.`when`(hearthstoneService.getInfo()).thenReturn(testSingle01)

        val testSingle02: Single<CardModel> = Single.error(Throwable())
        Mockito.`when`(hearthstoneService.getCards()).thenReturn(testSingle02)

        val testSingle03: Single<List<CardBackModel>> = Single.error(Throwable())
        Mockito.`when`(cardBackDao.getAllCards()).thenReturn(testSingle03)

        viewModel.initApp()
        Assert.assertEquals(false, viewModel.isLoading.value)
        Assert.assertEquals(true, viewModel.hasError.value)
    }


    @After
    fun releaseMocks() {
        closeable?.close()
    }
}