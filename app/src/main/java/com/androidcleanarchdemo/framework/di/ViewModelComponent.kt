package com.androidcleanarchdemo.framework.di

import com.androidcleanarchdemo.framework.ListViewModel
import com.androidcleanarchdemo.framework.NoteViewModel
import dagger.Component

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 17/08/20, 7:20 PM.
 */

@Component(modules = [ApplicationModule::class,RepositoryModule::class,UseCasesModule::class])
interface ViewModelComponent {

    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}