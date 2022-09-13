package com.mmb.happybox.cheerup.happything

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mmb.happybox.cheerup.R
import com.mmb.happybox.cheerup.databinding.FragmentHappyThingBinding
import com.mmb.happybox.common.ui.extenstions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappyThingFragment : BottomSheetDialogFragment() {

    private val viewModel by viewModels<HappyThingViewModel>()
    private val binding by viewBinding(FragmentHappyThingBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_happy_thing, container, false)
    }

}