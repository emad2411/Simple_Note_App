package com.example.simplenoteapp.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplenoteapp.database.Note
import com.example.simplenoteapp.database.NoteDatabaseDao
import kotlinx.coroutines.*

class NoteListViewModel(val dataSource:NoteDatabaseDao):ViewModel() {

    val viewModelJob= Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val allNotes=dataSource.getAllNotes()




    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}