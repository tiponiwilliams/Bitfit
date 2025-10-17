package com.example.bitfit.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WaterDao {
    @Query("SELECT * FROM water_entries ORDER BY dateEpochMillis DESC")
    fun getAll(): LiveData<List<WaterEntry>>

    @Insert
    suspend fun insert(entry: WaterEntry)
}
