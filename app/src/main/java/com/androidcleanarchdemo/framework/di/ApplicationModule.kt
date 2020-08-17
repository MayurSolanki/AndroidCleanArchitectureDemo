package com.androidcleanarchdemo.framework.di

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 17/08/20, 7:15 PM.
 */

@Module
class ApplicationModule(val application: Application) {

    @Provides
    fun provideApp():Application{
      return  application
    }
}