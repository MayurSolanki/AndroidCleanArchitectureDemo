package com.androidcleanarchdemo.framework.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 14/08/20, 1:29 PM.
 */

@Dao
interface NoteDao {

    @Insert(onConflict = REPLACE)
    suspend fun addNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteEntity(id:Long):NoteEntity?

    @Query("SELECT * FROM note")
    suspend fun getAllNoteEntities():List<NoteEntity>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)

}