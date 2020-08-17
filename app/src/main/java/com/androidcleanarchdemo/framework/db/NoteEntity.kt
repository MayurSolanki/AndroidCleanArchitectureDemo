package com.androidcleanarchdemo.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.androidcleanarchdemo.core.data.Note

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 14/08/20, 1:20 PM.
 */

@Entity(tableName = "note")
data class NoteEntity (
    val title: String,
    val content:String,

    @ColumnInfo(name = "creation_date")
    val creationTime:Long,

    @ColumnInfo(name = "update_time")
    val updatedTime: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
){
   companion object {
       fun fromNote(note:Note) : NoteEntity{    // From Note to the NoteEntity prepare
        return   NoteEntity(note.title,note.content,note.creationTime,note.updatedTime, note.id)
       }

   }

    // To Note
    fun toNote() : Note{
      return  Note(title = title,content = content,creationTime = creationTime,updatedTime = updatedTime,id = id)
    }

}
