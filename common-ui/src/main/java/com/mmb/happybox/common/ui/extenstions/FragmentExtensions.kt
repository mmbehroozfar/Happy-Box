package com.mmb.happybox.common.ui.extenstions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.mmb.happybox.common.ui.utils.ViewBindingProperty

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T,
    beforeDestroyCallback: (T.() -> Unit)? = null,
) = ViewBindingProperty(this, viewBindingFactory, beforeDestroyCallback)