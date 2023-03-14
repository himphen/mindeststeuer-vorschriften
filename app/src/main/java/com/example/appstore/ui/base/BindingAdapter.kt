package com.example.appstore.ui.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("setDrawableId")
fun setImageDrawableId(view: ImageView, resource: Int?) {
    if (resource == null) return
    view.load(resource) {
        crossfade(false)
    }
}

@BindingAdapter("setDrawable")
fun setImageDrawable(view: ImageView, resource: Drawable?) {
    if (resource == null) return
    view.load(resource) {
        crossfade(false)
    }
}

@BindingAdapter("setImageUrl")
fun setImageUrl(view: ImageView, resource: String?) {
    if (resource == null) return
    view.load(resource) {
        crossfade(false)
    }
}