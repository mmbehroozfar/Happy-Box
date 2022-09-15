package com.mmb.happybox.cheerup.utils

import androidx.fragment.app.DialogFragment
import com.mmb.happybox.cheerup.R

fun DialogFragment.setDialogBackground() {
    dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
}