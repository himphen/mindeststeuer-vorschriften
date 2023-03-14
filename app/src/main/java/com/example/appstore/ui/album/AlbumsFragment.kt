package com.example.appstore.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.appstore.databinding.FragmentAlbumsBinding
import com.example.appstore.model.Album
import com.example.appstore.ui.album.viewmodel.AlbumViewModel
import com.example.appstore.ui.base.BaseFragment
import kotlinx.coroutines.launch
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bookmarkedAdded.collect {
                Toast.makeText(context, "Added Bookmark", Toast.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bookmarkedRemoved.collect {
                Toast.makeText(context, "Removed Bookmark", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun initUi() {
        // init recyclerview
        val viewBinding = viewBinding!!

        adapter = AlbumAdapter(object : AlbumAdapter.ItemClickListener {
            override fun onBookmarkItemClicked(item: Album) {
                if (item.isBookmarked) {
                    viewModel.removeBookmarked(item.collectionId)
                } else {
                    viewModel.addBookmarked(item.collectionId)
                }
            }
        })
        viewBinding.rvlist.adapter = adapter
        viewBinding.rvlist.itemAnimator = null
    }


    private fun initData() {
        viewModel.getListFromAPI()
    }
}
