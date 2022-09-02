package com.example.wooyj.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.wooyj.adapter.MyRankingAdapter
import com.example.wooyj.data.Status
import com.example.wooyj.databinding.FragmentMyRankingListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 *
 * 2022/09/02.
 *
 * @author wooyj
 *
 */
@AndroidEntryPoint
class MyRankingListFragment : Fragment() {

    private var _binding: FragmentMyRankingListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MyRankingAdapter
    private val viewModel: MyRankingListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyRankingListBinding.inflate(inflater, container, false).apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        setRecyclerViewAdapter()
        observeViewModel()

        return binding.root
    }

    /**
     *
     * recyclerview adapter를 정의하고 recyclerview에 붙이는 function
     *
     * @author wooyj
     *
     *
     */
    private fun setRecyclerViewAdapter() {
        adapter = MyRankingAdapter().also {
            binding.rvList.adapter = it
        }
    }

    /**
     *
     * viewModel 내의 livedata 값의 변화를 observe하는 코드들을 모아놓는 function
     *
     * @author wooyj
     *
     */
    private fun observeViewModel() {
        viewModel.res.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Timber.d("$it")
                    binding.myInfo = it.data
                    adapter.submitList(it.data!!.rank_list)
                }
                Status.ERROR -> {
                    Timber.tag("${this@MyRankingListFragment.javaClass.simpleName}")
                        .e("${it.message}")
                }
                else -> {
                    //Loading 상황
                }
            }
        }
    }

}