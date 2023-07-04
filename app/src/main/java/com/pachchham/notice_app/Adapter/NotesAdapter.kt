package com.pachchham.notice_app.Adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pachchham.notice_app.Entity.NoteEntity
import com.pachchham.notice_app.R
import com.pachchham.notice_app.databinding.NoteItemBinding
class NotesAdapter(updatePin: (NoteEntity) -> Unit) : Adapter<NotesAdapter.NotesHolder>() {

    var updatePin = updatePin
    var notes =  ArrayList<NoteEntity>()
    lateinit var context : Context

    class NotesHolder(itemView: NoteItemBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {

        context = parent.context

        var binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesHolder(binding)
    }
    override fun getItemCount(): Int {
        return notes.size
    }
    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.binding.apply {
            txtTitel.isSelected = true
            notes.get(position).apply {
                txtTitel.text = title
                txtText.text = text
                if (pin) {
                    imgPin.setImageResource(R.drawable.pin)
                } else {
                    imgPin.setImageResource(R.drawable.unpin)
                }
                imgPin.setOnClickListener {
                    updatePin.invoke(notes.get(position))
                }
            }
        }
    }

    fun update(notes: List<NoteEntity>) {
        this.notes = notes as ArrayList<NoteEntity>
        notifyDataSetChanged()
    }

    fun setNotes(notes: List<NoteEntity>) {
        this.notes = notes as ArrayList<NoteEntity>
    }
}