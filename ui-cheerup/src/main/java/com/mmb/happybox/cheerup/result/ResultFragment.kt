package com.mmb.happybox.cheerup.result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mmb.happybox.cheerup.R
import com.mmb.happybox.cheerup.databinding.FragmentResultBinding
import com.mmb.happybox.cheerup.utils.setDialogBackground
import com.mmb.happybox.common.ui.extenstions.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultFragment : DialogFragment(R.layout.fragment_result) {

    private val viewModel by viewModels<ResultViewModel>()
    private val binding by viewBinding(FragmentResultBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogBackground()
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onCloseDialog.collect {
                findNavController().navigateUp()
            }
        }
    }

}