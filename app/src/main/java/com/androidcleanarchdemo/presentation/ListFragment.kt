package com.androidcleanarchdemo.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidcleanarchdemo.R
import com.androidcleanarchdemo.framework.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() , ListAction{

    private val noteListAdapter = NoteListAdapter(arrayListOf(),this)
    private lateinit var  listViewModel: ListViewModel

    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }

        addNote.setOnClickListener(View.OnClickListener {
            goToNoteDetail()
        })

        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel(){
        listViewModel.notes.observe(this, Observer {
            progressBar.visibility = View.GONE
            recyclerView.visibility  = View.VISIBLE
            noteListAdapter.updateNotes(it.sortedByDescending {
                it.updatedTime
            })
        })
    }

    private fun goToNoteDetail(id:Long = 0L){
        val action =  ListFragmentDirections.actionListFragmentToNoteFragment(id)
        Navigation.findNavController(recyclerView).navigate(action)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        listViewModel.getNotes()
    }

    override fun onClick(id: Long) {
        goToNoteDetail(id)
    }
}