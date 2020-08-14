package com.androidcleanarchdemo.core.usecase

import com.androidcleanarchdemo.core.data.Note
import com.androidcleanarchdemo.core.repository.NoteRepository

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 13/08/20, 6:22 PM.
 */
class RemoveNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note){
        noteRepository.removeNote(note)
    }
}