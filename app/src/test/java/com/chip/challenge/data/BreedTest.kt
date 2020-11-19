package com.chip.challenge.data

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BreedTest {

    private lateinit var breed: Breed

    @Before
    fun setUp() {
        breed = Breed("Terrier")
    }

    @Test
    fun test_toString() {
        assertEquals("Terrier", breed.toString())
    }
}
