package com.voidhash.hearthstonecodex.framework.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.voidhash.hearthstonecodex.R

object ImageUtil {

    fun loadRemoteImage(imageView: ImageView, url: String) {
        Glide
            .with(imageView.context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.icon_loading)
            .into(imageView);
    }
}