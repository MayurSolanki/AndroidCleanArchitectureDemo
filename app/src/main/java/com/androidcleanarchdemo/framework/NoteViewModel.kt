package com.androidcleanarchdemo.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.androidcleanarchdemo.core.data.Note
import com.androidcleanarchdemo.core.repository.NoteRepository
import com.androidcleanarchdemo.core.usecase.AddNote
import com.androidcleanarchdemo.core.usecase.GetAllNotes
import com.androidcleanarchdemo.core.usecase.GetNote
import com.androidcleanarchdemo.core.usecase.RemoveNote
import com.androidcleanarchdemo.framework.di.ApplicationModule
import com.androidcleanarchdemo.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 14/08/20, 6:38 PM.
 */
class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO);



    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder().applicationModule(ApplicationModule(getApplication())).build().inject(this)
    }




    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note:Note){
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)   // for background thread in live data use post value
        }
    }

    fun getNote(id: Long?){
        coroutineScope.launch {
            val note = id?.let { useCases.getNote(it) }
            currentNote.postValue(note)
        }
    }

    fun  deleteNote(note: Note){
        coroutineScope.launch {
            useCases.removeNote(note)
            saved.postValue(true)
        }
    }
}