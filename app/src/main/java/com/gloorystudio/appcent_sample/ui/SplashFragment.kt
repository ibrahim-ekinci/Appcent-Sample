package com.gloorystudio.appcent_sample.ui

import android.os.Bundle
import android.view.View
import com.gloorystudio.appcent_sample.R
import com.gloorystudio.appcent_sample.base.BaseFragment
import com.gloorystudio.appcent_sample.databinding.FragmentSplashBinding
import com.gloorystudio.appcent_sample.util.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SplashFragmentDirections.actionSplashFragmentToGameListFragment().navigate(view)
    }
}