package com.example.simplenoteapp.notelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenoteapp.database.Note
import com.example.simplenoteapp.databinding.NoteListItemBinding
import com.example.simplenoteapp.notelist.NoteListAdapter.NoteViewHolder.Companion.from

class NoteListAdapter(val clickListener:NoteItemClickListener) :
    ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return from(parent)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!,clickListener)

    }


    class NoteViewHolder(val binding: NoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Note?,
            clickListener: NoteItemClickListener
        ) {
            binding.note = item
            binding.clickListener=clickListener
            binding.executePendingBindings()


        }

        companion object {
            fun from(parent: ViewGroup): NoteViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = NoteListItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
                return NoteViewHolder(binding)
            }
        }

    }

    class NoteDiffCallBack : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

}

class NoteItemClickListener(val clickListener:(id:Long)->Unit){
    fun onClick(note:Note)=clickListener(note.id)
}