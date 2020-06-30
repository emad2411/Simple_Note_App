package com.example.simplenoteapp.notedetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplenoteapp.database.Note
import com.example.simplenoteapp.database.NoteDatabaseDao
import kotlinx.coroutines.*

class NoteDetailsViewModel(
    val dataSource:NoteDatabaseDao,
    var id:Long?):ViewModel() {


    private val _note=MutableLiveData<Note>()
    val note:LiveData<Note> get() = _note

    private val isEdit=MutableLiveData<Boolean>()


   private val  viewModelJob=Job()
    private val uiScope= CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        checkIsEdit()
        initializeNote()
    }

    private fun checkIsEdit() {
        if (id==-1L){
            isEdit.value=false
            id=null
        }else{
            isEdit.value=true
        }
    }


    private fun initializeNote(){

        id?.let {
            uiScope.launch {
                Log.i("TAG", "not null ");
                _note.value=getThisNote(it)
            }
        }

    }

    private suspend fun getThisNote(id:Long):Note?{
        return withContext(Dispatchers.IO){
             dataSource.get(id)
        }
    }

    fun obtainNote(title:String,description:String){
        uiScope.launch {
            val note=Note(
                title = title,
                body = description,
                time = System.currentTimeMillis())
            saveNote(note)

        }
    }


    private suspend fun saveNote(note:Note){
        withContext(Dispatchers.IO){
            if (isEdit.value==true){
                dataSource.update(note)
            }else{
                dataSource.insert(note)
            }
        }

    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}