package com.chip.challenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.chip.challenge.data.BreedRepository
import com.chip.challenge.utilities.FakeBreedsRepository
import com.chip.challenge.utilities.getValue
import com.chip.challenge.utilities.testBreeds
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.mockito.Mockito

@HiltAndroidTest
class BreedsViewModelTest {

    private lateinit var viewModel: BreedsViewModel
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
        breedRepository = FakeBreedsRepository()
        viewModel = BreedsViewModel(breedRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun basic() {

        viewModel.breeds.observeForever {}

        Mockito.verify(breedRepository).getBreeds()
        MatcherAssert.assertThat(
            viewModel.breeds, CoreMatchers.not(CoreMatchers.nullValue())
        )
        assertEquals(getValue(viewModel.breeds), testBreeds)
    }
}
