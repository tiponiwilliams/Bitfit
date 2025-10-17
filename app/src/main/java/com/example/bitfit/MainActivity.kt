package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.ui.WaterAdapter
import com.example.bitfit.ui.WaterViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val vm: WaterViewModel by viewModels()
    private lateinit var adapter: WaterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // RecyclerView setup
        val rv = findViewById<RecyclerView>(R.id.rvEntries)
        adapter = WaterAdapter()
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        // Observe DB changes
        vm.entries.observe(this) { list ->
            adapter.submitList(list)
        }

        // FAB -> AddEntryActivity
        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, AddEntryActivity::class.java))
        }
    }
}
