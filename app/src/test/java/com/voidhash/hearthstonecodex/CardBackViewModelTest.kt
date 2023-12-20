package com.voidhash.hearthstonecodex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.voidhash.hearthstonecodex.framework.local.dao.CardBackDao
import com.voidhash.hearthstonecodex.framework.model.CardBackModel
import com.voidhash.hearthstonecodex.framework.model.CardBase
import com.voidhash.hearthstonecodex.framework.remote.service.HearthstoneService
import com.voidhash.hearthstonecodex.framework.viewmodel.CardBackViewModel
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
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CardBackViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var hearthstoneService: HearthstoneService
    @Mock
    private lateinit var cardBackDao: CardBackDao

    private var closeable: AutoCloseable? = null

    private lateinit var viewModel: CardBackViewModel

    @Before
    fun setup() {
        closeable = MockitoAnnotations.openMocks(this)
        viewModel = CardBackViewModel(cardBackDao)
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
    fun `Test cards back data loaded from DATABASE`() {
        val cardList: List<CardBackModel> = listOf(
            CardBackModel(cardBackId = 375, name = "Harvest Wreath", img = "url://"),
            CardBackModel(cardBackId = 200, name = "Serpentine", img = "url://"),
            CardBackModel(cardBackId = 249, name = "Herbalist", img = "url://"),
        )
        val testSingle: Single<List<CardBackModel>> = Single.just(cardList)
        Mockito.`when`(cardBackDao.getAllCards()).thenReturn(testSingle)
        viewModel.fetchCardsBack()

        Assert.assertEquals(3, viewModel.backCardsCollection.value?.size)
        Assert.assertEquals(false, viewModel.isLoading.value)
        Assert.assertEquals(false, viewModel.hasError.value)
    }

    @Test
    fun `Test request ERROR`() {
        val testSingle: Single<List<CardBackModel>>  = Single.error(Throwable())
        Mockito.`when`(cardBackDao.getAllCards()).thenReturn(testSingle)
        viewModel.fetchCardsBack()
        Assert.assertEquals(false, viewModel.isLoading.value)
        Assert.assertEquals(true, viewModel.hasError.value)
    }

    @After
    fun releaseMocks() {
        closeable?.close()
    }
}