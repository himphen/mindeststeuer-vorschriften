package com.example.appstore.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.appstore.databinding.FragmentBookmarkBinding
import com.example.appstore.model.Album
import com.example.appstore.ui.album.AlbumAdapter
import com.example.appstore.ui.base.BaseFragment
import com.example.appstore.ui.bookmark.viewmodel.BookmarkViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>() {

    private val viewModel by sharedViewModel<BookmarkViewModel>()

    private lateinit var adapter: AlbumAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentBookmarkBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
        initUi()
        initData()
    }

    private fun initEvent() {
        viewModel.bookmarkedAlbumListLiveData.observe(viewLifecycleOwner) {
            // show data
            adapter.submitList(it)
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
