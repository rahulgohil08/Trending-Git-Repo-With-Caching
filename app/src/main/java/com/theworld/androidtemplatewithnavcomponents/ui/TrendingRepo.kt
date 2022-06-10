package com.theworld.androidtemplatewithnavcomponents.ui

import androidx.lifecycle.ViewModel
import com.theworld.androidtemplatewithnavcomponents.data.local.LastUpdate
import com.theworld.androidtemplatewithnavcomponents.data.room.dao.TrendingDao
import com.theworld.androidtemplatewithnavcomponents.network.Api
import com.theworld.androidtemplatewithnavcomponents.utils.Resource
import com.theworld.androidtemplatewithnavcomponents.utils.SafeApiCall
import javax.inject.Inject

class TrendingRepo @Inject constructor(
    private val api: Api,
    private val dao: TrendingDao,
) : ViewModel(), SafeApiCall {

    companion object {
        private const val TAG = "TrendingRepo"
    }


    suspend fun fetchTrendingRepos(isForcefully: Boolean) {

        when {
            !isForcefully && !isNewDataNeeded() -> {
                return
            }
        }


        val list = safeApiCall {
            api.fetchTrendingRepos()
        }

        when (list) {
            is Resource.Success -> {
                val data = list.value.items
                dao.apply {
                    deleteTrendingRepos()
                    insertTrendingRepos(data.map { it.toRoomData() })
                    deleteLastUpdate()
                }

                dao.insertLastUpdate(
                    LastUpdate(
                        last_update = (System.currentTimeMillis() + 2000 * 60 * 60).toString()    // 2 Hours added
//                        last_update = (System.currentTimeMillis()).toString()    // 2 Hours added
                    )
                )
            }
            is Resource.Failure -> {
//                handleApiError(resource)
            }

            else -> Unit
        }

    }


    private suspend fun isNewDataNeeded(): Boolean {

        val lastUpdate = dao.getLastUpdate().lastOrNull()?.last_update?.toLong() ?: System.currentTimeMillis()
        val currentTime = System.currentTimeMillis()

        if (currentTime >= lastUpdate) {
            return true
        }

        return false

    }


}
