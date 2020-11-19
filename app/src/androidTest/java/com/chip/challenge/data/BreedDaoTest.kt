package com.chip.challenge.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.chip.challenge.utilities.getValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BreedDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var breedDao: BreedDao
    private val breedA = Breed("A")
    private val breedB = Breed("B")
    private val breedC = Breed("C")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        breedDao = database.breedDao()

        // Insert breeds in non-alphabetical order to test that results are sorted by name
        breedDao.insertAll(listOf(breedB, breedC, breedA))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetBreeds() {
        val breedList = getValue(breedDao.getBreedsFlow().asLiveData())
        assertThat(breedList.size, equalTo(3))

        // Ensure breed list is sorted by name
        assertThat(breedList[0], equalTo(breedA))
        assertThat(breedList[1], equalTo(breedB))
        assertThat(breedList[2], equalTo(breedC))
    }

    @Test
    fun testGetBreed() {
        assertThat(getValue(breedDao.getBreed(breedA.name).asLiveData()), equalTo(breedA.name))
    }
}
