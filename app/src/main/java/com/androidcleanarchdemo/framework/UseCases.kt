package com.androidcleanarchdemo.framework

import com.androidcleanarchdemo.core.usecase.AddNote
import com.androidcleanarchdemo.core.usecase.GetAllNotes
import com.androidcleanarchdemo.core.usecase.GetNote
import com.androidcleanarchdemo.core.usecase.RemoveNote

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 14/08/20, 6:37 PM.
 */


data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)