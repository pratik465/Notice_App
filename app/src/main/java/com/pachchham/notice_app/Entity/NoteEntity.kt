package com.pachchham.notice_app.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @ColumnInfo(name = "titel") var title:String,
    @ColumnInfo(name = "text") var text:String,
    @ColumnInfo(name = "date") var date:String,
    @ColumnInfo(name = "pin") var pin:Boolean,
){
    @PrimaryKey(autoGenerate = true) var  id:Int =0
}
