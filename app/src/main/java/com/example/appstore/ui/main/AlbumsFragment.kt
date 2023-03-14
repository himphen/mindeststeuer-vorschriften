package com.example.appstore.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appstore.databinding.FragmentAlbumsBinding
import com.example.appstore.model.Album
import com.example.appstore.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AlbumsFragment : BaseFragment<FragmentAlbumsBinding>() {

    private val viewModel by sharedViewModel<AlbumViewModel>()

    private lateinit var adapter: AlbumAdapter

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
        viewModel.albumListWithBookmarkedData.observe(viewLifecycleOwner) {
            // show data
            adapter.submitList(it)
        }
    }

    private fun initUi() {
        // init recyclerview

        adapter = AlbumAdapter(object : AlbumAdapter.ItemClickListener {
            override fun onBookmarkItemClicked(gridItem: Album) {
                // TODO
            }
        })
        viewBinding!!.rvlist.adapter = adapter
        viewBinding!!.rvlist.layoutManager = LinearLayoutManager(activity)

    }


    private fun initData() {
        viewModel.getListFromAPI()
    }
}
