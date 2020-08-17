package com.androidcleanarchdemo.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 14/08/20, 2:27 PM.
 */
@Database(entities = [NoteEntity::class],version = 1)
abstract class DatabaseService: RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "note.db"

        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService{
          return   Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }



        fun getInstance(context: Context): DatabaseService {
           return (instance ?: create(context)).also { instance = it }
        }
    }

    abstract fun noteDao() : NoteDao
}