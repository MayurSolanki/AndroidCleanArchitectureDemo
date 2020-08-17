package com.androidcleanarchdemo.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.androidcleanarchdemo.R
import com.androidcleanarchdemo.core.data.Note
import com.androidcleanarchdemo.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "note_id"


class NoteFragment : Fragment() {

    private var noteId: Long? = 0L

    private lateinit var noteViewModel: NoteViewModel
    private var currentNote = Note("","",0L,0L,0L);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        floatingActionButton.setOnClickListener {
            if(!et_title.text.toString().trim().isEmpty() || !et_content.text.toString().trim().isEmpty()){
                val time =  System.currentTimeMillis()
                currentNote.title = et_title.text.toString().trim()
                currentNote.content = et_content.text.toString().trim()
                currentNote.updatedTime = time

                if(currentNote.id == 0L){
                    currentNote.creationTime = time
                }
                noteViewModel.saveNote(currentNote)
            }else{
                Navigation.findNavController(it).popBackStack()

            }
        }

        if(noteId != 0L){
            noteViewModel.getNote(noteId)
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        noteViewModel.saved.observe(this, Observer {
            if(it){
                Toast.makeText(context,"Done",Toast.LENGTH_LONG).show()
                hideKeyboard()
                Navigation.findNavController(et_title).popBackStack()
            }else{
                Toast.makeText(context,"Something Went wrong, Please try again !!",Toast.LENGTH_LONG).show()

            }

        })

        noteViewModel.currentNote.observe(this, Observer {
            if(it != null){
                currentNote = it
                et_title.setText(currentNote.title,TextView.BufferType.EDITABLE)
                et_content.setText(currentNote.content,TextView.BufferType.EDITABLE)

            }
        })
    }

    private fun hideKeyboard(){
        val inn =context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inn.hideSoftInputFromWindow(et_title.windowToken,0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId ){
            R.id.delete_note->{
                if(context != null && noteId != 0L){
                    AlertDialog.Builder(context!!).setTitle("Delete Note")
                        .setMessage("Are you sure")
                        .setPositiveButton("yes"){
                            dialog, which -> noteViewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton("No"){
                            dialog, which ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }
        return true
    }
}