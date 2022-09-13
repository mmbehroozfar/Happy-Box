package com.mmb.happybox.cheerup.utils

import android.view.View
import android.widget.TextView
import androidx.core.view.isGone
import androidx.databinding.BindingAdapter
import com.ncorti.slidetoact.SlideToActView

@BindingAdapter("goneIfEmpty")
fun View.goneIfEmpty(value: String) {
    isGone = value.isEmpty()
}

@BindingAdapter("selected")
fun TextView.selected(value: Boolean) {
    isSelected = value
}

@BindingAdapter("lockIfEmpty")
fun SlideToActView.lockIfEmpty(value: String) {
    isLocked = value.isEmpty()
}