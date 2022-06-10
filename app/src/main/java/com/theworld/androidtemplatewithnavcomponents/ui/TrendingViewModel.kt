package com.theworld.androidtemplatewithnavcomponents.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theworld.androidtemplatewithnavcomponents.data.remote.TrendingItemRoom
import com.theworld.androidtemplatewithnavcomponents.data.room.dao.TrendingDao
import com.theworld.androidtemplatewithnavcomponents.network.Api
import com.theworld.androidtemplatewithnavcomponents.utils.SafeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val api: Api,
    private val repo: TrendingRepo,
    private val dao: TrendingDao,
) : ViewModel(), SafeApiCall {

    companion object {
        private const val TAG = "TrendingViewModel"
    }


//    private var _trending =
//        MutableStateFlow<Resource<TrendingItem>>(Resource.Loading)
//    val trending = _trending.asStateFlow()

    private var _trending =
        MutableLiveData<List<TrendingItemRoom>>(emptyList())
    val trending = _trending

    init {
        forceUpdateRepo()
    }

    /*------------------------------------ Fetch Trending -----------------------------------------*/

    fun forceUpdateRepo(isForcefully:Boolean = false) = viewModelScope.launch {
        repo.fetchTrendingRepos(isForcefully)
    }

    fun fetchTrendingRepos() = dao.getTrendingRepos()


/*fun fetchTrendingRepos() = flow {
    val list = safeApiCall {
        api.fetchTrendingRepos()
    }
    emit(list)
}.flowOn(Dispatchers.IO)
    .conflate()*/


}
