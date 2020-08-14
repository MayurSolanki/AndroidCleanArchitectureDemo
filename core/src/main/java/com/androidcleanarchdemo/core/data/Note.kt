package com.androidcleanarchdemo.core.data

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 13/08/20, 6:02 PM.
 */
data class Note(
  var title: String,
  var content:String,
  var creationTime:Long,
  var updatedTime: Long,
  var id: Long
)