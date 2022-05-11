package uz.gita.noteAppMobDev.presentation.view.adapter.main

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteAppMobDev.R
import uz.gita.noteAppMobDev.data.common.models.NoteData
import uz.gita.noteAppMobDev.data.sourse.local.entity.NoteEntity
import uz.gita.noteAppMobDev.databinding.NoteItemBinding

class AddNoteAdapter : ListAdapter<NoteEntity, AddNoteAdapter.ViewHolder>(NoteDataDiffUtils) {

    private var onItemClickListener: ((NoteEntity) -> Unit)? = null

    object NoteDataDiffUtils : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean =
            oldItem == newItem

    }

    inner class ViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClickListener!!.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind(): NoteEntity = with(binding) {
            getItem(absoluteAdapterPosition).apply {
                noteText.text = title
                noteTime.text = time.toString()
                descriptionNote.text = Html.fromHtml(description)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        NoteItemBinding.bind(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.note_item, parent, false)
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnItemClickListener(block: (NoteEntity) -> Unit) {
        onItemClickListener = block
    }
}