package com.irfan.fourthchallenge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.irfan.fourthchallenge.databinding.ItemNoteBinding
import com.irfan.fourthchallenge.model.Note

class NoteAdapter(
    private var onEdit: (Note) -> Unit,
    private var onDelete: (Note) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            titleTv.text = item.title
            noteTv.text = item.note
            deleteButton.setOnClickListener {
                onDelete(item)
            }
            root.setOnClickListener {
                onEdit(item)
            }
        }
    }

    class NoteViewHolder(
        val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

class NoteDiffCallBack : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem == newItem
}