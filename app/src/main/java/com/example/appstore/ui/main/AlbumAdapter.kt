package com.example.appstore.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appstore.databinding.ItemAlbumBinding
import com.example.appstore.model.Album

class AlbumAdapter(
    private val itemClickListener: ItemClickListener
) : ListAdapter<Album, RecyclerView.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }
    }

    interface ItemClickListener {
        fun onBookmarkItemClicked(gridItem: Album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemAlbumViewHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemAlbumViewHolder -> {
                val itemBinding = holder.viewBinding
                val item = getItem(position)
                itemBinding.coverIv.load(item.imageUrl)
                itemBinding.titleTv.text = item.collectionName
                itemBinding.contentTv.text = "$" + item.collectionPrice.toString()
                itemBinding.bookmarkIv.setOnClickListener {
                    itemClickListener.onBookmarkItemClicked(item)
                }
            }
        }
    }

    internal class ItemAlbumViewHolder(val viewBinding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}
