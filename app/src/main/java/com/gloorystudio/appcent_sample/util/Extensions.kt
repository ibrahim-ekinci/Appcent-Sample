package com.gloorystudio.appcent_sample.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun NavDirections.navigate(view: View) {
    view.let {
        val action = this
        it.findNavController().navigate(action)
    }
}