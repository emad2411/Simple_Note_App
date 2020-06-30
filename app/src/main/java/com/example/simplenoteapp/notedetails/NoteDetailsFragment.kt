package com.example.simplenoteapp.notedetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.simplenoteapp.R
import com.example.simplenoteapp.database.NoteDatabase
import com.example.simplenoteapp.databinding.FragmentNoteDetailsBinding
import javax.security.auth.login.LoginException


/**
 * A simple [Fragment] subclass.
 */
class NoteDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentNoteDetailsBinding=
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_note_details,
                container,
                false)

        val argument=NoteDetailsFragmentArgs.fromBundle(requireArguments())
        Log.i("TAG", "NoteDetailsFragment: ${argument.noteId}");

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDao

        val viewModelFactory=NoteDetailsViewModelFactory(dataSource,argument.noteId)
        val viewModel=
            ViewModelProvider(
                this,
                viewModelFactory).get(NoteDetailsViewModel::class.java)

        viewModel.note.observe(viewLifecycleOwner, Observer {
            note->
            note?.let{
                Log.i("TAG", "NoteDetailsFragment: ${note}")
                binding.noteTitleTxt.setText(note.title)
                binding.noteTxt.setText(note.body)
            }
        })



        binding.saveBtn.setOnClickListener(){
            viewModel.obtainNote(
                binding.noteTitleTxt.text.toString(),
                binding.noteTxt.text.toString())
            it.findNavController().navigate(
                NoteDetailsFragmentDirections.actionNoteDetailsFragmentToNoteListFragment()
            )
        }
        binding.viewModle=viewModel
        binding.setLifecycleOwner (this)

        return binding.root
    }

}
