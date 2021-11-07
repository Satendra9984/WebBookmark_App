package com.example.navigationbookmark.MainNavigationFragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationbookmark.R
import com.example.navigationbookmark.ViewModel.BookmarkRecyclerViewAdapter
import com.example.navigationbookmark.ViewModel.BookmarkViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: BookmarkViewModel by activityViewModels()
    private lateinit var adapter: BookmarkRecyclerViewAdapter
    private lateinit var bookmarkRecycler: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val message = arguments?.getString("url")
        Log.i("MainFragment", "$message received")

        // setting menu
        setHasOptionsMenu(true)

        // Getting recyclerView and fab for mainFragment
        bookmarkRecycler = view.findViewById(R.id.bookmark_list)
        fab = view.findViewById(R.id.fabbtn)

        // Creating a adapter
        adapter = BookmarkRecyclerViewAdapter(requireContext())
        bookmarkRecycler.adapter = adapter
        bookmarkRecycler.layoutManager = GridLayoutManager(requireActivity(), 3)

        // Observing the viewModel's allBookmarks
        viewModel.allBookmark.observe(viewLifecycleOwner, { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
        // For adding the new Bookmark
        fab.setOnClickListener {
            view.findNavController().navigate(R.id.mainFragment_to_saveFragment)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_fragment_menu, menu)

        val search:MenuItem = menu.findItem(R.id.search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchDataBase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchDataBase(newText)
        }
        return true
    }

    private fun searchDataBase(query: String) {
        val searchQuery: String = "%$query%"
        viewModel.search(searchQuery).observe(this, { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
    }


}