package com.example.navigationbookmark.MainNavigationFragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Delete
import com.example.navigationbookmark.Database.BookmarkEntity
import com.example.navigationbookmark.R
import com.example.navigationbookmark.ViewModel.BookmarkViewModel
import com.example.navigationbookmark.ViewModel.MainActivity


/**
 * A simple [Fragment] subclass.
 * Use the [SaveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SaveFragment : Fragment() {

//    private val args by navArgs<SaveFragmentArgs>()

    private lateinit var titleEdit:EditText
    private lateinit var urlEdit: EditText
    private lateinit var descripEdit: EditText
    private lateinit var saveBtn:Button
    //viewmodel
    private val viewModel:BookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_save, container, false)
        // Getting the text that is sent from the MainActivity
        val message = arguments?.getString("url")

        titleEdit = view.findViewById(R.id.bookmark_title)
        urlEdit = view.findViewById(R.id.bookmark_url)
        descripEdit = view.findViewById(R.id.description_EditText)!!
        saveBtn = view.findViewById(R.id.savebtn)

        // Using that text in our view
        if (message != null) {
                urlEdit.setText(message)
                var pre = message.removePrefix("https://www.")
                pre = pre.removeSuffix(".com/")
                titleEdit.setText(pre)
        }

        // Handling the save button
        saveBtn.setOnClickListener{
            val title = titleEdit.text.toString()
            val url = urlEdit.text.toString()
            val des = descripEdit.text.toString()

            if(title.isNotEmpty() && url.isNotEmpty())
            {
                viewModel.insert(BookmarkEntity(title,url,des))
            }
            else
            {
                Toast.makeText(requireContext(), "Fields Are Empty", Toast.LENGTH_SHORT).show()
            }
            view.findNavController().navigate(R.id.saveFragment_to_mainFragment)

        }


        return view
    }


}