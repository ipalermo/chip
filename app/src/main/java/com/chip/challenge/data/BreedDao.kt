package com.chip.challenge.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Breed class.
 */
@Dao
interface BreedDao {

    @Query("SELECT * from breeds ORDER BY name")
    fun getBreedsFlow(): Flow<List<Breed>>

    @Query("SELECT * from breeds WHERE name == :breedName")
    fun getBreed(breedName: String): Flow<Breed>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(breeds: List<Breed>)
}
