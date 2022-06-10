package com.theworld.androidtemplatewithnavcomponents.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.theworld.androidtemplatewithnavcomponents.data.local.LastUpdate
import com.theworld.androidtemplatewithnavcomponents.data.remote.TrendingItemRoom


@Dao
interface TrendingDao {

    @Query("SELECT * FROM trending")
    fun getTrendingRepos(): LiveData<List<TrendingItemRoom>>

    @Insert
    suspend fun insertTrendingRepos(data: List<TrendingItemRoom>)

    @Query("DELETE FROM trending")
    suspend fun deleteTrendingRepos()

    @Insert(onConflict = REPLACE)
    suspend fun insertLastUpdate(data: LastUpdate)


    @Query("SELECT * FROM lastupdate")
    suspend fun getLastUpdate(): List<LastUpdate>

    @Query("DELETE FROM lastupdate")
    suspend fun deleteLastUpdate()
}