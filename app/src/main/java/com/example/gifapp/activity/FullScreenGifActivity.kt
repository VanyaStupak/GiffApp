package com.example.gifapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.gifapp.databinding.ActivityFullScreenBinding

class FullScreenGifActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val gifUrl = intent.getStringExtra("gifUrl")

        Glide.with(this)
            .asGif()
            .load(gifUrl)
            .into(binding.fullScreenGifImageView)
    }
}
