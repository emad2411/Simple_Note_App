package com.example.simplenoteapp.notedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplenoteapp.database.NoteDatabaseDao

class NoteDetailsViewModelFactory(
    val dataSource: NoteDatabaseDao,
    var id:Long):ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailsViewModel::class.java)) {
            return NoteDetailsViewModel(dataSource,id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}