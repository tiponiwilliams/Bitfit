package com.example.bitfit.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_entries")
data class WaterEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dateEpochMillis: Long,   // store date/time
    val cups: Int,               // water amount (cups)
    val note: String? = null
)
