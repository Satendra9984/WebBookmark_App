package com.example.navigationbookmark.Database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(Bookmark:BookmarkEntity)

    @Delete
    suspend fun delete(Bookmark: BookmarkEntity)

    @Update
    suspend fun update(Bookmark: BookmarkEntity)

    @Query("SELECT * FROM BOOKMARK_TABLE ORDER BY 'KEY' ASC")
    fun getAll():LiveData<List<BookmarkEntity>>

    @Query("SELECT * FROM BOOKMARK_TABLE WHERE TITLES LIKE :searchQuery")
    fun search(searchQuery:String):LiveData<List<BookmarkEntity>>

    @Query("DELETE FROM BOOKMARK_TABLE")
    suspend fun deleteAll()

    @Query("SELECT * FROM BOOKMARK_TABLE ORDER BY TITLES ASC")
    fun sortByAtoZ(): LiveData<List<BookmarkEntity>>

    @Query("SELECT * FROM BOOKMARK_TABLE ORDER BY TITLES DESC")
    fun sortByZtoA(): LiveData<List<BookmarkEntity>>
}