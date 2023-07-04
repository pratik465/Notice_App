package com.pachchham.notice_app

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pachchham.notice_app.Adapter.NotesAdapter
import com.pachchham.notice_app.Database.RoomDB
import com.pachchham.notice_app.Entity.NoteEntity
import com.pachchham.notice_app.databinding.ActivityMainBinding
import com.pachchham.notice_app.databinding.AddDialogBinding
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var db: RoomDB
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = RoomDB.init(this)
        initView()
    }

    private fun initView() {
        binding.add.setOnClickListener {
            addNoteDialog()
        }
        adapter = NotesAdapter {
            var isPin = false
            if (it.pin) {
                isPin = false
            } else {
                isPin = true
            }
            var data = NoteEntity(it.title, it.text, it.date, isPin)
            data.id = it.id
            db.note().updateNote(data)
            adapter.update(filternote(db.note().getNotes()))
        }
        adapter.setNotes(filternote(db.note().getNotes()))
        binding.notelist.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.notelist.adapter = adapter
    }

    fun filternote(list: List<NoteEntity>): ArrayList<NoteEntity> {
        var newList = ArrayList<NoteEntity>()
        for (l in list) {
            if (l.pin) {
                newList.add(l)
            }
        }
        for (l in list) {
            if (!l.pin) {
                newList.add(l)
            }
        }
        return newList
    }

    private fun addNoteDialog() {
        var dialog = Dialog(this)
        var bind = AddDialogBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)
        bind.btnSubmit.setOnClickListener {
            var titel = bind.edtTitel.text.toString()
            var text = bind.edtText.text.toString()
            var format = SimpleDateFormat("DD/MM/YYYY hh:mm:ss a")
            var current = format.format(Date())
            var data = NoteEntity(titel, text, current, false)
            db.note().addNote(data)

            bind.edtTitel.setText("")
            bind.edtText.setText("")

            adapter.update(filternote(db.note().getNotes()))
            dialog.dismiss()
        }

        dialog.show()
    }
}