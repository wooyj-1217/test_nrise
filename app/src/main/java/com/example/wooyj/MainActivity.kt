package com.example.wooyj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.wooyj.data.Status
import com.example.wooyj.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        var actionbar = binding.toolbar
        setSupportActionBar(actionbar)
        setContentView(binding.root)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.res.observe(this) {
            when(it.status){
                Status.SUCCESS->{
                    //TODO("받아온 데이터 바인딩 처리할 것.")
                }
                Status.ERROR->{
                    Timber.d("$it")
                }
                else -> {
                    //Loading 상황
                }
            }
        }
    }

}