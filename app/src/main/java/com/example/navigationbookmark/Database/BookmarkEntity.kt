package com.example.navigationbookmark.Database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "bookmark_table")
@Parcelize
data class BookmarkEntity(

    @ColumnInfo(name = "TITLES")
    val title:String,

    @ColumnInfo(name = "URL")
    val url:String,

    @ColumnInfo(name = "DESCRIPTION")
    var description:String = ""

): Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}

