package com.example.simplenoteapp.notelist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.simplenoteapp.R
import com.example.simplenoteapp.database.NoteDatabase
import com.example.simplenoteapp.databinding.FragmentNoteListBinding


class NoteListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentNoteListBinding=DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_note_list,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDao

        val factory=NoteListViewModelFactory(dataSource)
        val viewModel= ViewModelProvider(this,factory).get(NoteListViewModel::class.java)
        val adapter=NoteListAdapter(NoteItemClickListener {
            id->
            Log.i("TAG", "Clicked ${id.toString()}")
            this.findNavController().navigate(
                NoteListFragmentDirections.actionNoteListFragmentToNoteDetailsFragment(id))
        })
        binding.noteListRv.adapter=adapter

        viewModel.allNotes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })



        binding.addBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_noteListFragment_to_noteDetailsFragment
            ))

        binding.viewModel=viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }



}
