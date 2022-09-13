package com.mmb.happybox.cheerup.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mmb.happybox.cheerup.R
import com.mmb.happybox.cheerup.databinding.FragmentHomeBinding
import com.mmb.happybox.common.ui.extenstions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)

}