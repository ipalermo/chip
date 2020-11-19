package com.chip.challenge.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")
data class Breed(
    @PrimaryKey
    val name: String
)
