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
}