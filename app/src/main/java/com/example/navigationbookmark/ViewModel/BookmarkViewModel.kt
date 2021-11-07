package com.example.navigationbookmark.ViewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import com.example.navigationbookmark.Database.BookmarkDatabase.Companion.getInstance
import com.example.navigationbookmark.Database.BookmarkEntity
import com.example.navigationbookmark.Database.DatabaseDao
import com.example.navigationbookmark.Repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application) : AndroidViewModel(application) {

    private val getDao: DatabaseDao = getInstance(application).getDao()
    private val repository: Repository = Repository(getDao)
    val allBookmark: LiveData<List<BookmarkEntity>> = repository.allBookmark

    // Always make sure inside the coroutine if it is not copied from other dao function like insert is
    // not in all update,delete functions
    fun insert(bookmarkEntity: BookmarkEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(bookmarkEntity)
        }
    }
    fun update(bookmarkEntity: BookmarkEntity){
        Log.i("BookmarkViewModel","${bookmarkEntity.id} Updated successfully in BookmarkViewModel")

        viewModelScope.launch(Dispatchers.IO) {
            repository.update(bookmarkEntity)
        }
    }
    fun delete(bookmarkEntity: BookmarkEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(bookmarkEntity)
        }
    }

    fun search(searchQuery: String):LiveData<List<BookmarkEntity>>{
        return repository.search(searchQuery)
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }

}