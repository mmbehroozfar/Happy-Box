package com.mmb.happybox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mmb.happybox.common.ui.utils.contentView
import com.mmb.happybox.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by contentView<MainActivity, ActivityMainBinding>(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}