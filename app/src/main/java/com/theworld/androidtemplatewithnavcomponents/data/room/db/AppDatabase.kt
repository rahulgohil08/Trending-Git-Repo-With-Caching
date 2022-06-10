package com.theworld.androidtemplatewithnavcomponents.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.theworld.androidtemplatewithnavcomponents.data.local.LastUpdate
import com.theworld.androidtemplatewithnavcomponents.data.remote.TrendingItem
import com.theworld.androidtemplatewithnavcomponents.data.remote.TrendingItemRoom
import com.theworld.androidtemplatewithnavcomponents.data.room.dao.TrendingDao


@Database(entities = [TrendingItemRoom::class, LastUpdate::class], version = 1)
abstract class TrendingDatabase : RoomDatabase() {

    abstract fun getTrendingDao(): TrendingDao

}
