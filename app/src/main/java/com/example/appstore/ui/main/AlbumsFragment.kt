package com.example.appstore.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appstore.databinding.FragmentAlbumsBinding
import com.example.appstore.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AlbumsFragment : BaseFragment<FragmentAlbumsBinding>() {

    private val viewModel by sharedViewModel<AlbumViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAlbumsBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
        initUi()
        initData()
    }

    private fun initEvent() {
        viewModel.albumList.observe(viewLifecycleOwner) {
            // show data
        }
    }

    private fun initUi() {
        // init recyclerview
    }


    private fun initData() {
        viewModel.getListFromAPI()
    }
}
