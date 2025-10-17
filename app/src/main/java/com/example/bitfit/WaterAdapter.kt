package com.example.bitfit.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.R
import com.example.bitfit.data.WaterEntry
import java.text.SimpleDateFormat
import java.util.*

class WaterAdapter : ListAdapter<WaterEntry, WaterAdapter.VH>(DIFF) {

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<WaterEntry>() {
            override fun areItemsTheSame(oldItem: WaterEntry, newItem: WaterEntry) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: WaterEntry, newItem: WaterEntry) =
                oldItem == newItem
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        private val tvCups: TextView = itemView.findViewById(R.id.tvCups)
        private val tvNote: TextView = itemView.findViewById(R.id.tvNote)

        private val dateFmt = SimpleDateFormat("MMM d, yyyy • h:mm a", Locale.getDefault())

        fun bind(item: WaterEntry) {
            tvDate.text = dateFmt.format(Date(item.dateEpochMillis))
            tvCups.text = "Cups: ${item.cups}"
            if (!item.note.isNullOrBlank()) {
                tvNote.visibility = View.VISIBLE
                tvNote.text = item.note
            } else {
                tvNote.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_water_entry, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}
