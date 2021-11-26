package com.senla.notebooksenla

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.senla.notebooksenla.databinding.RecyclerViewItemBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewAdapter(private val itemClickListener: (Int) -> Unit) :
    ListAdapter<File, RecyclerViewAdapter.ViewHolder>(DiffCallback()) {

    private val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    companion object {
        private const val DATE_FORMAT = "dd-MM-yyyy"
    }

    class DiffCallback : DiffUtil.ItemCallback<File>() {

        override fun areItemsTheSame(oldItem: File, newItem: File): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: File, newItem: File): Boolean =
            oldItem.readLines() == newItem.readLines()
    }

    class ViewHolder(private val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(file: File, dateFormatter: SimpleDateFormat) {
            binding.apply {
                noteTitle.text = file.name
                noteCreatingDate.text = dateFormatter.format(file.lastModified())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context)))
            .apply { itemView.setOnClickListener {
                itemClickListener(adapterPosition)
            } }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), dateFormatter)
    }
}