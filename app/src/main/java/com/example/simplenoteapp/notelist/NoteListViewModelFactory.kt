package com.example.simplenoteapp.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplenoteapp.database.NoteDatabaseDao

class NoteListViewModelFactory(val dataSource: NoteDatabaseDao):ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteListViewModel::class.java)) {
            return NoteListViewModel(dataSource) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }
}