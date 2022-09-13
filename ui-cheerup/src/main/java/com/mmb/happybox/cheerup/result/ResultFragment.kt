package com.mmb.happybox.cheerup.result

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.mmb.happybox.cheerup.R
import com.mmb.happybox.cheerup.databinding.FragmentResultBinding
import com.mmb.happybox.common.ui.extenstions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : DialogFragment(R.layout.fragment_result) {

    private val viewModel by viewModels<ResultViewModel>()
    private val binding by viewBinding(FragmentResultBinding::bind)

}