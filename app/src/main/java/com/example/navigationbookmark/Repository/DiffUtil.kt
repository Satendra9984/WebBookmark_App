package com.example.navigationbookmark.Repository

import androidx.recyclerview.widget.DiffUtil
import com.example.navigationbookmark.Database.BookmarkEntity

class BookmarkDiffUtil (
    private val oldList: List<BookmarkEntity>,
    private val newList: List<BookmarkEntity>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].description == newList[newItemPosition].description
                && oldList[oldItemPosition].url == newList[newItemPosition].url
    }

}