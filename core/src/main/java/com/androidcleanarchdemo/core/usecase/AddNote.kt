package com.androidcleanarchdemo.core.usecase

import com.androidcleanarchdemo.core.data.Note
import com.androidcleanarchdemo.core.repository.NoteRepository

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 13/08/20, 6:19 PM.
 */
class AddNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note){
        noteRepository.addNote(note)
    }


}