package com.example.navigationbookmark.Repository

import androidx.lifecycle.LiveData
import com.example.navigationbookmark.Database.BookmarkEntity
import com.example.navigationbookmark.Database.DatabaseDao


class Repository(private val dao:DatabaseDao) {

      val allBookmark:LiveData<List<BookmarkEntity>> = dao.getAll()
      val allBookmarkAtoZ:LiveData<List<BookmarkEntity>> = dao.sortByAtoZ()
      val allBookmarkZtoA:LiveData<List<BookmarkEntity>> = dao.sortByZtoA()

      suspend fun insert(bookmarkEntity: BookmarkEntity){
          dao.insert(bookmarkEntity)
      }

      suspend fun update(bookmarkEntity: BookmarkEntity){
          dao.update(bookmarkEntity)
      }

      suspend fun delete(bookmarkEntity: BookmarkEntity){
          dao.delete(bookmarkEntity)
      }

      fun search(searchQuery: String):LiveData<List<BookmarkEntity>>{
          return dao.search(searchQuery)
      }

      suspend fun deleteAll(){
          dao.deleteAll()
      }

//     fun sortByAtoZ():LiveData<List<BookmarkEntity>>{
//         return dao.sortByAtoZ()
//     }
//
//    fun sortByZtoA():LiveData<List<BookmarkEntity>>{
//        return dao.sortByZtoA()
//    }
}