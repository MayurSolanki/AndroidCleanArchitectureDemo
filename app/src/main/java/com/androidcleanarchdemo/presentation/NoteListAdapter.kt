package com.androidcleanarchdemo.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidcleanarchdemo.R
import com.androidcleanarchdemo.core.data.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Mayur Solanki (mayursolanki120@gmail.com) on 17/08/20, 2:20 PM.
 */
class NoteListAdapter(var notes:ArrayList<Note>, val action: ListAction) :RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {




    fun updateNotes(newNotes: List<Note>){
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }


    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val layout = view.note_row
        private val noteTitle = view.tv_title
        private val noteContent = view.tv_content
        private val noteDate = view.tv_date

        fun bind(note:Note){
            noteTitle.text = note.title
            noteContent.text = note.content

            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate =  sdf.format(Date(note.updatedTime))
            noteDate.text = "Last Updated: ${resultDate}"

            layout.setOnClickListener {
                action.onClick(note.id)
            }

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val item : View? =   LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        val noteViewHolder =  NoteViewHolder(item!!)
        return noteViewHolder;
    }

    override fun getItemCount(): Int {
      return  notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(note = notes[position])
    }
}