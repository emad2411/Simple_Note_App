package com.example.simplenoteapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDatabaseDao {
    @Insert
    fun insert(note:Note)
    @Update
    fun update(note:Note)
    @Query("SELECT * FROM notes_table WHERE id=:id")
    fun get(id:Long):Note
    @Query("SELECT * FROM notes_table ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Note>>
    @Query("DELETE FROM notes_table WHERE id=:id")
    fun delete(id:Long)
    @Query("DELETE FROM notes_table")
    fun clear()


}