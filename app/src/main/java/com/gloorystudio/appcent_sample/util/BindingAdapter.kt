package com.gloorystudio.appcent_sample.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapter {

    @BindingAdapter("urlImage")
    fun bindUrlImage(view: ImageView, imageUrl: String?) {
        if (imageUrl != null) {
            Picasso.get()
                .load(imageUrl)
                .fit()
                .centerCrop()
                .into(view)
        } else {
            view.setImageBitmap(null)
        }
    }
}