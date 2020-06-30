package com.example.simplenoteapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0L,
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "body")
    var body:String,
    @ColumnInfo(name = "time")
    var time:Long=0) {
}