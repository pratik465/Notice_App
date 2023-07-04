package com.pachchham.notice_app.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pachchham.notice_app.Entity.NoteEntity

@Dao
interface NoteDao {

    @Insert
    fun addNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes")
    fun getNotes() : List<NoteEntity>

    @Update
    fun updateNote(noteEntity: NoteEntity)
    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Query("Delete FROM notes")
    fun allDelete()
}