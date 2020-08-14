package com.androidcleanarchdemo.core.usecase

import com.androidcleanarchdemo.core.repository.NoteRepository

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 13/08/20, 6:21 PM.
 */
class GetAllNotes(private val noteRepository: NoteRepository) {

    suspend  operator  fun invoke(){
        noteRepository.getAllNotes()
    }
}