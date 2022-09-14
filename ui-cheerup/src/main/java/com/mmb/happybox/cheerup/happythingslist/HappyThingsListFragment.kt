package com.mmb.happybox.cheerup.happythingslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mmb.happybox.cheerup.R
import com.mmb.happybox.cheerup.databinding.FragmentHappyThingsListBinding
import com.mmb.happybox.common.ui.extenstions.viewBinding
import com.mmb.happybox.common.ui.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HappyThingsListFragment : Fragment(R.layout.fragment_happy_things_list) {

    private val viewModel by viewModels<HappyThingsListViewModel>()
    private val binding by viewBinding(FragmentHappyThingsListBinding::bind)
    private var adapter by autoCleared<HappyThingsPagingAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }

        adapter = HappyThingsPagingAdapter(
            onEditClicked = {
                findNavController().navigate(HappyThingsListFragmentDirections.actionHappyThingsListFragmentToHappyThingFragment())
            },
            onDeleteClicked = {
                findNavController().navigate(
                    HappyThingsListFragmentDirections.actionHappyThingsListFragmentToDeleteItemFragment(
                        it
                    )
                )
            }
        )
        binding.itemsRv.adapter = adapter

        lifecycleScope.launch {
            viewModel.navigateToHappyThingScreen.collect {
                findNavController().navigate(HappyThingsListFragmentDirections.actionHappyThingsListFragmentToHappyThingFragment())
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.happyThings.collect {
                adapter.submitData(it)
            }
        }
    }
}