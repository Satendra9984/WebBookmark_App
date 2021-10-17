package com.example.navigationbookmark.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [BookmarkEntity::class], version = 1, exportSchema = false)
abstract class BookmarkDatabase : RoomDatabase() {

    abstract fun getDao(): DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE : BookmarkDatabase? = null
        fun getInstance(context: Context): BookmarkDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookmarkDatabase::class.java,
                        "BookmarkDatabase"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}