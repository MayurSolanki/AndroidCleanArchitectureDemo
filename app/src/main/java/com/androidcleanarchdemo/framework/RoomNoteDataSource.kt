package com.androidcleanarchdemo.framework

import android.content.Context
import com.androidcleanarchdemo.core.data.Note
import com.androidcleanarchdemo.core.repository.NoteDataSource
import com.androidcleanarchdemo.framework.db.DatabaseService
import com.androidcleanarchdemo.framework.db.NoteEntity

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 14/08/20, 2:33 PM.
 */
class RoomNoteDataSource(context: Context) : NoteDataSource{
    val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) {
      noteDao.addNoteEntity(NoteEntity.fromNote(note))
    }

    override suspend fun get(id: Long): Note {
      return noteDao.getNoteEntity(id)?.toNote()!!
    }


    override suspend fun getAll(): List<Note> {
       return  noteDao.getAllNoteEntities().map {
           it.toNote()
       }
    }

    override suspend fun remove(note: Note) {
        noteDao.deleteNoteEntity(noteEntity = NoteEntity.fromNote(note))
    }
}