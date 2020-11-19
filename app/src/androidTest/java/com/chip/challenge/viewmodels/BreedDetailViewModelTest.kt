package com.chip.challenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.chip.challenge.data.AppDatabase
import com.chip.challenge.data.BreedRepository
import com.chip.challenge.utilities.FakeBreedsRepository
import com.chip.challenge.utilities.getValue
import com.chip.challenge.utilities.testBreed
import com.chip.challenge.utilities.testBreeds
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

@HiltAndroidTest
class BreedDetailViewModelTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: BreedDetailViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)

    lateinit var breedRepository: BreedRepository

    @Before
    fun setUp() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        breedRepository = FakeBreedsRepository()
        viewModel = BreedDetailViewModel(breedRepository, testBreed.name)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testBasic() {
        val pictures = getValue(viewModel.searchPictures().asLiveData())
        assertThat(
            pictures, CoreMatchers.not(CoreMatchers.nullValue())
        )
        assertEquals(pictures, testBreeds)
    }
}
