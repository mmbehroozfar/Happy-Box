package com.mmb.happybox.cheerup.happythingslist

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mmb.happybox.cheerup.R
import com.mmb.happybox.cheerup.databinding.FragmentHappyThingsListBinding
import com.mmb.happybox.common.ui.extenstions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappyThingsListFragment : Fragment(R.layout.fragment_happy_things_list) {

    private val viewModel by viewModels<HappyThingsListViewModel>()
    private val binding by viewBinding(FragmentHappyThingsListBinding::bind)

}