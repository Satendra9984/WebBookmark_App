package com.example.navigationbookmark.MainNavigationFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationbookmark.Database.BookmarkEntity
import com.example.navigationbookmark.R
import com.example.navigationbookmark.ViewModel.BookmarkViewModel
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val viewModel: BookmarkViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        // Initializing all our ViewElements
        view.bookmark_title.setText(args.updateBookmark.title)
        view.bookmark_url.setText(args.updateBookmark.url)
        view.description_EditText.setText(args.updateBookmark.description)
        args.updateBookmark.id

        // Click handler for update button
        view.updatebtn.setOnClickListener {
            val titleEdit = view.bookmark_title.text.toString()
            val urlEdit = view.bookmark_url.text.toString()
            val descEdit = view.description_EditText.text.toString()

            if (titleEdit.isNotEmpty() && urlEdit.isNotEmpty()) {
                val bookmark = BookmarkEntity(titleEdit, urlEdit, descEdit)
                bookmark.id = args.updateBookmark.id
                viewModel.update(bookmark)
                Toast.makeText(
                    requireContext(),
                    "${bookmark.title} Updated Successfully",
                    Toast.LENGTH_SHORT
                ).show()
                view.findNavController().navigate(R.id.updateFragment_to_mainFragment)
            } else {
                Toast.makeText(requireContext(), "TITLE OR URL IS EMPTY", Toast.LENGTH_SHORT).show()
            }
        }


        // Click handler for delete button
        view.delete_insavebtn.setOnClickListener {
            val titleEdit = view.bookmark_title.text.toString()
            val urlEdit = view.bookmark_url.text.toString()
            val descEdit = view.description_EditText.text.toString()

            val bookmark = BookmarkEntity(titleEdit, urlEdit, descEdit)
            bookmark.id = args.updateBookmark.id
            viewModel.delete(bookmark)
            Toast.makeText(
                requireContext(),
                "${bookmark.title} Deleted Successfully",
                Toast.LENGTH_SHORT
            ).show()
            view.findNavController().navigate(R.id.updateFragment_to_mainFragment)
        }

        return view
    }


}