package com.mmb.happybox.cheerup.faq

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.mmb.happybox.cheerup.R
import com.mmb.happybox.cheerup.databinding.FragmentFaqBinding
import com.mmb.happybox.common.ui.extenstions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FaqFragment : DialogFragment(R.layout.fragment_faq) {

    private val viewModel by viewModels<FaqViewModel>()
    private val binding by viewBinding(FragmentFaqBinding::bind)

}