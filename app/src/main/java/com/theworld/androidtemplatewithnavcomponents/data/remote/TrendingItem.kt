package com.theworld.androidtemplatewithnavcomponents.data.remote


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class TrendingItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @SerializedName("added_stars")
    val addedStars: String,

    @SerializedName("avatars")
    @Ignore
    val avatars: List<String>,

    @SerializedName("desc")
    val desc: String,

    @SerializedName("forks")
    val forks: String,

    @SerializedName("lang")
    val lang: String,

    @SerializedName("repo_link")
    val repoLink: String,

    @SerializedName("stars")
    val stars: String,

    @SerializedName("repo")
    val repo: String,

    var isSelected: Boolean = false
) {

    @ColumnInfo(name = "avatar")
    var avatar: String = avatars.firstOrNull().toString()


    fun toRoomData(): TrendingItemRoom {
        return TrendingItemRoom(
            addedStars = addedStars,
            desc = desc,
            forks = forks,
            lang = lang,
            repoLink = repoLink,
            stars = stars,
            repo = repo,
            avatar = avatars.firstOrNull() ?: "",
            id = 0L
        )
    }
}