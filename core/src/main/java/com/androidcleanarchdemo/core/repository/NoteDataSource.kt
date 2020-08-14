package com.androidcleanarchdemo.core.repository

import com.androidcleanarchdemo.core.data.Note

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 13/08/20, 6:06 PM.
 */
interface NoteDataSource {

    suspend fun add(note:Note)

    suspend fun get(id:Long):Note

    suspend fun getAll():List<Note>

    suspend fun remove(note: Note)
}