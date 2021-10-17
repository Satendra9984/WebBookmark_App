package com.example.navigationbookmark.ViewModel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navigationbookmark.Database.BookmarkEntity
import com.example.navigationbookmark.MainNavigationFragments.MainFragmentDirections
import com.example.navigationbookmark.R
import java.lang.System.load


class BookmarkRecyclerViewAdapter(
    private val context: Context
) : RecyclerView.Adapter<BookmarkRecyclerViewAdapter.BookmarkViewHolder>() {

    private val allData = ArrayList<BookmarkEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_forrecycler, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.appTitle.text = allData[position].title
        Glide.with(context)
            .load("https://www.google.com/s2/favicons?sz=64&domain_url=${allData[position].url}")
            .error(allData[position].url + "/favicon.png")
            .placeholder(R.drawable.weblink)
            .into(holder.appIcon)

        Log.i("Database", "${itemCount}")

        holder.appTitle.setOnClickListener {
            val action = MainFragmentDirections.mainFragmentToUpdateFragment(allData[position])
            holder.itemView.findNavController().navigate(action)
        }
        holder.appIcon.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse(allData[position].url))
            openUrl.data = Uri.parse(allData[position].url)
            context.startActivity(openUrl)
        }

    }

    fun updateList(newlist: List<BookmarkEntity>) {
        allData.clear()
        allData.addAll(newlist)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return allData.size
    }

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appIcon: ImageView = itemView.findViewById(R.id.appicon)
        val appTitle: TextView = itemView.findViewById(R.id.apptitle)
    }
}
