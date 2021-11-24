package com.senla.notebooksenla

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senla.notebooksenla.databinding.RecyclerViewItemBinding

class RecyclerViewAdapter(private val list: List<Pair<String, String>> = listOf(Pair("", "")),
    private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    companion object {
        const val ITEM_CLICKED_DEFAULT_VALUE = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context)), listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            noteTitle.text = list[position].first
            notesChangeDate.text = list[position].second
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(binding: RecyclerViewItemBinding, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        val noteTitle = binding.noteTitle
        val notesChangeDate = binding.noteCreatingDate

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int = ITEM_CLICKED_DEFAULT_VALUE)
    }
}