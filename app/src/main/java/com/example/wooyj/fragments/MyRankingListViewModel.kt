package com.example.wooyj.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.wooyj.data.MyRanking
import com.example.wooyj.data.Resource
import com.example.wooyj.network.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class MyRankingListViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    application: Application,
) : AndroidViewModel(application) {

    val res: LiveData<Resource<MyRanking>>
        get() = _res
    private val _res: LiveData<Resource<MyRanking>> = flow {
        emit(Resource.loading(null))
        emit(networkRepository.getList().let {
            if (it.isSuccessful) {
                Resource.success(it.body())
            } else {
                Resource.error(it.errorBody().toString(), null)
            }
        }
        )
    }.catch { error ->
        emit(Resource.error(error.localizedMessage, null))
    }.asLiveData()

}