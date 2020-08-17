package com.androidcleanarchdemo.core.repository

import com.androidcleanarchdemo.core.data.Note

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 13/08/20, 6:09 PM.
 */
class NoteRepository(private val noteDataSource: NoteDataSource) {

    suspend fun addNote(note: Note) {
        noteDataSource.add(note)
    }

    suspend fun getNote(id: Long) : Note {
      return  noteDataSource.get(id)
    }

    suspend fun getAllNotes() : List<Note> {
      return  noteDataSource.getAll()
    }

    suspend fun removeNote(note: Note) {
        noteDataSource.remove(note)
    }
}