package com.example.wooyj

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.wooyj.adapter.MyRankingAdapter
import com.example.wooyj.data.Status
import com.example.wooyj.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


/**
 *
 * 2022/09/02
 *
 * MainActivity
 *
 * @author wooyj
 *
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setMyToolbar()
        setContentView(binding.root)
    }

    /**
     *
     * toolbar를 설정하는 function
     *
     * @author wooyj
     *
     */
    private fun setMyToolbar() {
        var actionbar = binding.toolbar
        setSupportActionBar(actionbar)
    }

}