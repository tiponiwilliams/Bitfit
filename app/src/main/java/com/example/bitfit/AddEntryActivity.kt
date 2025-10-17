package com.example.bitfit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bitfit.ui.WaterViewModel

class AddEntryActivity : AppCompatActivity() {

    private val vm: WaterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_entry)

        val etCups: EditText = findViewById(R.id.etCups)
        val etNote: EditText = findViewById(R.id.etNote)
        val btnSave: Button = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val cupsText = etCups.text?.toString()?.trim()
            val cups = cupsText?.toIntOrNull()

            if (cups == null || cups <= 0) {
                Toast.makeText(this, "Please enter a valid number of cups.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val note = etNote.text?.toString()?.trim()
            vm.addEntry(cups, note)

            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
            finish() // go back to list
        }
    }
}
