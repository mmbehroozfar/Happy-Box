package com.mmb.happybox.cheerup.faq

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.mmb.happybox.cheerup.R
import com.mmb.happybox.cheerup.utils.setDialogBackground
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FaqFragment : DialogFragment(R.layout.fragment_faq) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogBackground()
    }

}