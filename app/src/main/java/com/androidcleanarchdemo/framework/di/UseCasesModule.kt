package com.androidcleanarchdemo.framework.di

import com.androidcleanarchdemo.core.repository.NoteRepository
import com.androidcleanarchdemo.core.usecase.AddNote
import com.androidcleanarchdemo.core.usecase.GetAllNotes
import com.androidcleanarchdemo.core.usecase.GetNote
import com.androidcleanarchdemo.core.usecase.RemoveNote
import com.androidcleanarchdemo.framework.UseCases
import dagger.Module
import dagger.Provides

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 17/08/20, 7:18 PM.
 */

@Module
class UseCasesModule {

   @Provides
   fun provideUseCases(noteRepository: NoteRepository):UseCases{
       return UseCases(
           AddNote(noteRepository),
           GetAllNotes(noteRepository),
           GetNote(noteRepository),
           RemoveNote(noteRepository)
       )
   }
}