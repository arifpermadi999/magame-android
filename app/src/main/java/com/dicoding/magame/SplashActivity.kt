package com.dicoding.magame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dicoding.magame.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnGetStarted.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_get_started){
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }
    }
}