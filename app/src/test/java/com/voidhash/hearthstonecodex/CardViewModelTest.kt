package com.voidhash.hearthstonecodex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.voidhash.hearthstonecodex.framework.local.dao.CardDao
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.remote.service.HearthstoneService
import com.voidhash.hearthstonecodex.framework.viewmodel.CardViewModel
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
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var hearthstoneService: HearthstoneService
    @Mock
    private lateinit var cardDao: CardDao
    private var closeable: AutoCloseable? = null
    private lateinit var viewModel:CardViewModel

    @Before
    fun setup() {
        closeable = MockitoAnnotations.openMocks(this)
        viewModel = CardViewModel(hearthstoneService, cardDao)
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
    fun `Test search cards data by name loaded from SERVICE`() {
        val cardList: List<CardBase> = listOf(
            CardBase(dbfId = 68326, cardId = "VAN_CS2_029",name = "Fireball", img = "url://"),
            CardBase(dbfId = 56173, cardId = "DRG_270t9", name = "Malygos's Fireball", img = "url://")
        )
        val testSingle: Single<List<CardBase>> = Single.just(cardList)
        `when`(hearthstoneService.getCardSearch("fireball")).thenReturn(testSingle)
        viewModel.fetchCardSearched("fireball")

        Assert.assertEquals(2, viewModel.cardsCollection.value?.size)
        Assert.assertEquals(false, viewModel.isLoading.value)
        Assert.assertEquals(false, viewModel.hasError.value)
    }

    @Test
    fun `Test search cards data by card set loaded from DATABASE`() {
        val cardList: List<CardBase> = listOf(
            CardBase(dbfId = 1826, cardId = "NAX11_01", name = "Grobbulus", cardSet = "Naxxramas"),
            CardBase(dbfId = 1807, cardId = "FP1_023", name = "Dark Cultist", cardSet = "Naxxramas"),
            CardBase(dbfId = 96785, cardId = "BG_FP1_031", name = "Baron Rivendare", cardSet = "Naxxramas")
        )
        val testSingle: Single<List<CardBase>> = Single.just(cardList)
        `when`(cardDao.getFromCardSet("Naxxramas")).thenReturn(testSingle)
        viewModel.fetchCards("Naxxramas")

        Assert.assertEquals(3, viewModel.cardsCollection.value?.size)
        Assert.assertEquals(false, viewModel.isLoading.value)
        Assert.assertEquals(false, viewModel.hasError.value)
    }

    @Test
    fun `Test request ERROR`() {
        val testSingle: Single<List<CardBase>> = Single.error(Throwable())
        `when`(hearthstoneService.getCardSearch("frost")).thenReturn(testSingle)
        viewModel.fetchCardSearched("frost")

        Assert.assertEquals(false, viewModel.isLoading.value)
        Assert.assertEquals(true, viewModel.hasError.value)

        `when`(cardDao.getFromCardSet("Vanilla")).thenReturn(testSingle)
        viewModel.fetchCards("Vanilla")
        Assert.assertEquals(false, viewModel.isLoading.value)
        Assert.assertEquals(true, viewModel.hasError.value)
    }

    @After
    fun releaseMocks() {
        closeable?.close()
    }

}