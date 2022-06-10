package com.theworld.androidtemplatewithnavcomponents.data.remote


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trending")
data class TrendingItemRoom(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "added_stars")
    val addedStars: String,

    @ColumnInfo(name = "desc")
    val desc: String,

    @ColumnInfo(name = "forks")
    val forks: String,

    @ColumnInfo(name = "lang")
    val lang: String,

    @ColumnInfo(name = "repo_link")
    val repoLink: String,

    @ColumnInfo(name = "stars")
    val stars: String,

    @ColumnInfo(name = "repo")
    val repo: String,

    @ColumnInfo(name = "avatar")
    var avatar: String,
) {
    @Ignore
    var isSelected: Boolean = false
}