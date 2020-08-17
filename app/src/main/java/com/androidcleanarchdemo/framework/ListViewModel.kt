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
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 17/08/20, 4:54 PM.
 */
class ListViewModel(application: Application):AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val noteRepository =  NoteRepository(RoomNoteDataSource(application));

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder().applicationModule(ApplicationModule(getApplication())).build().inject(this)
    }




    val notes = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
          val noteList = useCases.getAllNotes()
          notes.postValue(noteList)
      }
    }



}
