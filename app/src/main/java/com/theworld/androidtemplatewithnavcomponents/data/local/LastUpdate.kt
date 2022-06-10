package com.theworld.androidtemplatewithnavcomponents.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LastUpdate(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val last_update: String
)
