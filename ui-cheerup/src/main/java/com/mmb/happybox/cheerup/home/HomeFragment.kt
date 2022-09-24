package com.mmb.happybox.cheerup.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mmb.happybox.android_test_shared.AppIdleResource
import com.mmb.happybox.cheerup.R
import com.mmb.happybox.cheerup.databinding.FragmentHomeBinding
import com.mmb.happybox.common.ui.extenstions.viewBinding
import com.ncorti.slidetoact.SlideToActView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        initializeListeners()
        initializeObservers()
    }

    private fun initializeListeners() = with(binding) {
        cheerupSlider.onSlideCompleteListener =
            object : SlideToActView.OnSlideCompleteListener {
                override fun onSlideComplete(view: SlideToActView) {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToResultFragment())
                    cheerupSlider.resetSlider()
                }
            }
        cheerupSlider.onSlideToActAnimationEventListener =
            object : SlideToActView.OnSlideToActAnimationEventListener {
                override fun onSlideCompleteAnimationEnded(view: SlideToActView) {
                    AppIdleResource.decrement()
                }

                override fun onSlideCompleteAnimationStarted(
                    view: SlideToActView,
                    threshold: Float
                ) {
                    AppIdleResource.increment()
                }

                override fun onSlideResetAnimationEnded(view: SlideToActView) = Unit

                override fun onSlideResetAnimationStarted(view: SlideToActView) = Unit

            }
    }

    private fun initializeObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigateToFaqScreen.collect {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFaqFragment())
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigateToHappyThingsListScreen.collect {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHappyThingsListFragment())
            }
        }
    }

}