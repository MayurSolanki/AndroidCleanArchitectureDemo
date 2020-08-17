package com.androidcleanarchdemo.framework.di

import android.app.Application
import com.androidcleanarchdemo.core.repository.NoteRepository
import com.androidcleanarchdemo.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 17/08/20, 7:16 PM.
 */

@Module
class RepositoryModule {

    @Provides
    fun provideRepo(application: Application):NoteRepository{
        return NoteRepository(RoomNoteDataSource(application))
    }
}