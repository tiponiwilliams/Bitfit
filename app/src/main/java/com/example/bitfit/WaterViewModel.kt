package com.example.bitfit.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bitfit.data.AppDatabase
import com.example.bitfit.data.WaterEntry
import kotlinx.coroutines.launch

class WaterViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = AppDatabase.getInstance(app).waterDao()

    val entries: LiveData<List<WaterEntry>> = dao.getAll()

    fun addEntry(cups: Int, note: String?) {
        viewModelScope.launch {
            val entry = WaterEntry(
                dateEpochMillis = System.currentTimeMillis(),
                cups = cups,
                note = note?.takeIf { it.isNotBlank() }
            )
            dao.insert(entry)
        }
    }
}
