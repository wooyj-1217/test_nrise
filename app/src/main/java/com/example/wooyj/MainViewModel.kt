package com.example.wooyj

import android.app.Application
import androidx.lifecycle.*
import com.example.wooyj.data.Resource
import com.example.wooyj.network.MyRanking
import com.example.wooyj.network.NetworkRepository
import com.example.wooyj.network.NetworkService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    val networkRepository: NetworkRepository,
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