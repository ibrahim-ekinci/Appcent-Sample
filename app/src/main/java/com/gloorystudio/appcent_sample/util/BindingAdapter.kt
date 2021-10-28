package com.gloorystudio.appcent_sample.util

import android.graphics.Color
import android.widget.ImageView
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.gloorystudio.appcent_sample.R
import com.squareup.picasso.Picasso

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("urlImage")
    fun bindUrlImage(view: ImageView, imageUrl: String?) {
        if (imageUrl != null) {
            Picasso.get()
                .load(imageUrl)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.default_image)
                .into(view)
        } else {
            view.setImageBitmap(null)
        }
    }
}